package org.springblade.modules.group.service.impl;

import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Group;
import org.springblade.modules.group.mapper.CompanyMapper;
import org.springblade.modules.group.mapper.GroupMapper;
import org.springblade.modules.group.service.IGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.group.service.IUploadimgService;
import org.springblade.modules.group.utils.LevelUtil;
import org.springblade.modules.group.vo.GroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {


	@Resource
	private GroupMapper groupMapper;
	@Autowired
	private IUploadimgService uploadimgService;
	@Resource
	private CompanyMapper companyMapper;

	/**
	 * 判断是否是最底级
	 * @param parentId
	 * @return
	 */
	public boolean iscompany(Integer parentId){
		return groupMapper.iscompany(parentId)>0;
	};

	/**
	 * 同一个组下面不能存在两个同名组
	 * @param parentId
	 * @param groupName
	 * @param groupId
	 * @return
	 */
	public boolean checkExist(Integer parentId, String groupName, Integer groupId) {
		return groupMapper.countByNameAndParentId(parentId, groupName, groupId) > 0;
	}

	/**
	 * 创建一个新的分组
	 * @param group
	 * @return
	 */
	public boolean save(Group group) {
		//如果分组数据为空，则无法通过校验
		//BeanValidator.check(param);
		//如果分组已经重复，则无法进行分组创建
		if(checkExist(group.getParentId(), group.getGname(), group.getId())) {
			throw new ServiceException("同一层级下不能存在相同名称的分组");
		}
		/*if(iscompany(tbGroup.getParentId())) {
			throw new ServiceException("有单位的分组不能再创建分组");
		}*/
		group.setId(-1);
		if(group.getParentId()==null){
			group.setLevel("0");
		}else {
			// 拼接新增分组的层级
			group.setLevel(LevelUtil.calculateLevel(getLevel(group.getParentId()), group.getParentId()));
		}
		int a = groupMapper.insertSelective(group);
		return a > 0;
	}

	/**
	 * 通过分组id获取分组层级
	 * @param groupId
	 * @return
	 */
	public String getLevel(Integer groupId) {
		Group group = groupMapper.selectByPrimaryKey(groupId);
		if (group == null) {
			return null;
		}
		return group.getLevel();
	}


	/**
	 * 获取所有分组的详细信息
	 * @return
	 */
	public List<GroupVo> getListGroupvo(){
		List<Group> groupList= groupMapper.getAllGroup();
		//使用工具，完成泛型简便操作，形成带类型的空集合，用于存储需要使用的数据
		List<GroupVo> VoList = Lists.newArrayList();
		//遍历所有的集合列表
		for (Group group : groupList) {
			//取出分组集合中的所有分组信息，封装进刚才准备好的dto集合，用于进行分组层级数据处理
			GroupVo vo = GroupVo.adapt(group);
			if(uploadimgService.findBygbgid(group.getId())!=null){
				vo.setGbgurl(uploadimgService.findBygbgid(group.getId()).getUrl());
			}
			if(uploadimgService.findBygimgid(group.getId())!=null) {
				vo.setGimgurl(uploadimgService.findBygimgid(group.getId()).getUrl());
			}
			VoList.add(vo);
		}
		return VoList;
	}

	/**
	 * 获取所有分组的详细信息
	 * @return
	 */
	public List<Group> getListGroup(){
		List<Group> groupList= groupMapper.getAllGroup();
		return groupList;
	}

	/**
	 * 删除分组
	 * @param group
	 * @return
	 */
	@Override
	public boolean deleteGroup(Group group) {
		String level=group.getLevel()+"."+group.getId();
		// 查询当前分组下的分组
		List<Group> groupList=groupMapper.getGroupBylevel(level);
		// 查询当前分组下的单位
		String gname= group.getGname();
		List<Company> companyList = companyMapper.getListTbCompanyForname(gname);
		if(groupList.size()<1 && companyList.size()<1){
			group.setIsdeleted(1);
			return groupMapper.updateGroup(group)>0;

		}
		throw new ServiceException("有单位的分组与存在下级分组的组不能删除");
	}

	/**
	 * 修改分组
	 */
	public boolean updateGroup(Group group) {
		// 判断分组前后有没有改变层级
		//根据id取出分组数据，准备修改
		Group before = groupMapper.selectByPrimaryKey(group.getId());
		if(group.getParentId()==before.getParentId()){
			return groupMapper.updateGroup(group)>0;
		}else {
			//分组去重
			if (checkExist(group.getParentId(), group.getGname(), group.getId())) {
				throw new ServiceException("同一层级下存在相同名称的分组");
			} else {

				//检查待修改分组是否为空值
				Preconditions.checkNotNull(before, "待更新的分组不存在");
                // 获取到改变后的层级
				String afterLevel=LevelUtil.calculateLevel(getLevel(group.getParentId()), group.getParentId());
				// 获取到改变前的层级的下级
				String beforLevel=before.getLevel()+"."+group.getId();
				if(afterLevel.contains(beforLevel)){
					throw new ServiceException("上级分组不能放入下级分组中");
				}

				//根据修改字段修改分组信息
				Group after = Group.builder().id(group.getId()).gname(group.getGname()).parentId(group.getParentId())
					.seq(group.getSeq()).gename(group.getGename()).about(group.getAbout()).content(group.getContent()).isdeleted(group.getIsdeleted()).isgroup(group.getIsgroup()).build();
				//设置分组层级
				after.setLevel(afterLevel);
				//更改分组自己分组
				return updateWithChild(before, after);
			}
		}
	}
	@Transactional
	private boolean updateWithChild(Group before, Group after) {
		//更新之后的分组层级
		String newLevelPrefix = after.getLevel();
		//更新之前的分组层级
		String oldLevelPrefix = before.getLevel();
		//如果层级不同则进行分组操作
		if (!after.getLevel().equals(before.getLevel())) {
			//根据层级把分组的子集全部取出来放入一个list集合
			String levels=before.getLevel()+"."+before.getId();
			List<Group> groupList = groupMapper.getGroupBylevel(levels);
			//如果子分组不为空集
			if (CollectionUtils.isNotEmpty(groupList)) {
				for (Group group : groupList) {
					//拿到每个分组的层级
					String level = group.getLevel();
					//如果前缀是以
					if (level.indexOf(oldLevelPrefix) == 0) {
						//TODO //分组层级算法简单实现
						level = newLevelPrefix + level.substring(oldLevelPrefix.length());
						group.setLevel(level);

					}
					groupMapper.batchUpdateLevel(group);
				}

			}
		}
		return groupMapper.updateGroup(after)>0;
	}



}
