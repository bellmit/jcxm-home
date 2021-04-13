package org.springblade.modules.group.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Group;
import org.springblade.modules.group.entity.GroupCompany;
import org.springblade.modules.group.mapper.CompanyMapper;
import org.springblade.modules.group.mapper.GroupCompanyMapper;
import org.springblade.modules.group.mapper.GroupMapper;
import org.springblade.modules.group.service.ICompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.group.service.IUploadimgService;
import org.springblade.modules.group.vo.CompanyVo;
import org.springblade.modules.notice.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {
	@Resource
	private GroupMapper groupMapper;
	@Resource
	private CompanyMapper companyMapper;
	@Resource
	private GroupCompanyMapper groupCompanyMapper;
	@Autowired
	private IUploadimgService uploadimgService;
	/**
	 * 根据分组获取分组下的单位
	 * @param group
	 * @return
	 */
	public List<Company> getListTbCompanyForgroup(Group group){
		String level=group.getLevel()+"."+group.getId();
		// 获取所有下级分组的单位
		List<Company> listA = companyMapper.getListTbCompanyForlevel(level);
		// 获取当前分组的单位
		List<Company> listB =companyMapper.getListTbCompanyForname(group.getGname());
		// 去重
		List<Company> collect = Stream.of(listA, listB)
			.flatMap(Collection::stream)
			.distinct()
			.collect(Collectors.toList());
		return collect;
	}
	public IPage<Company> selectCompanyPage(IPage<Company> page, Group group) {
		String level=group.getLevel()+"."+group.getId();
		return page.setRecords(companyMapper.selectCompanyPage(page,level));
	}
	/**
	 * 获取某个分组下单位的详细信息
	 * @param group
	 * @return
	 */
	public List<CompanyVo> getListTbCompanyVoForgroup(Group group){
		String gname= group.getGname();
		List<Company> companyList = companyMapper.getListTbCompanyForname(gname);
		//使用工具，完成泛型简便操作，形成带类型的空集合，用于存储需要使用的数据
		List<CompanyVo> VoList = Lists.newArrayList();
		//遍历所有的集合列表
		for (Company company : companyList) {
			//取出分组集合中的所有分组信息，封装进刚才准备好的dto集合，用于进行分组层级数据处理
			CompanyVo vo = CompanyVo.adapt(company);
			if(uploadimgService.findByclogid(company.getId())!=null){
				vo.setClogurl(uploadimgService.findByclogid(company.getId()).getUrl());
			}

			VoList.add(vo);
		}
		return VoList;
	}
	/**
	 * 获取某个分组下的所有单位
	 */
	public List<Company> getListTbCompanyForgroupbyname(Group group){
		String gname= group.getGname();
		List<Company> companyList = companyMapper.getListTbCompanyForname(gname);
		return companyList;
	}

	/**
	 * 在分组下添加单位
	 * @param company
	 * @param groupId
	 * @return
	 */
	public boolean saveCompany(Company company, Integer groupId) {
		// 将单位和分组创建关联关系
		GroupCompany groupCompany = new GroupCompany();
		// 将当前单位存入单位表
		Integer ocid = companyMapper.getCompanyId(company.getCname());
		// 单位不存在就添加新单位并创建关联关系，单位存在就直接创建分组与单位的关联关系
		if (ocid == null) {
			int num = companyMapper.insertCompany(company);
			// 通过单位名称获取单位id
			if (num > 0) {
				int cid = companyMapper.getCompanyId(company.getCname());
				groupCompany.setId(-1);
				groupCompany.setCid(cid);
				groupCompany.setGid(groupId);
				int a = groupCompanyMapper.insertGroupCompany(groupCompany);
				return a > 0;
			}

		}else{
			// TODO 判断关联表是否有关联数据，防止重复存储。
			groupCompany.setId(-1);
			groupCompany.setCid(ocid);
			groupCompany.setGid(groupId);
			int a = groupCompanyMapper.insertGroupCompany(groupCompany);
			return a > 0;
		}

		return false;
	}

	/**
	 * 从分组下删除单位
	 * @param cid
	 * @param gid
	 * @return
	 */
	public boolean deleteCompany(Integer cid,Integer gid){
		return groupCompanyMapper.deleteGroupCompany(cid,gid)>0;
	}

	/**
	 * 修改单位内容
	 * @param company
	 * @return
	 */
	public boolean updateCompany(Company company){
		return companyMapper.updateCompany(company)>0;
	}

	/**
	 * 逻辑删除单位
	 */
	public boolean delCompany(Company company){
		int count=companyMapper.getCountbyId(company.getId());
		if(count>0){
			throw new ServiceException("其他分组下有此单位不能删除");
		}
		company.setIsdeleted(1);
		return companyMapper.updateCompany(company)>0;
	}
}
