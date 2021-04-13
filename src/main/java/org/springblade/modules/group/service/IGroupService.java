package org.springblade.modules.group.service;

import org.springblade.modules.group.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.group.vo.GroupVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
public interface IGroupService extends IService<Group> {

	/**
	 * 判断是否是最底级
	 * @param parentId
	 * @return
	 */
	public boolean iscompany(Integer parentId);

	/**
	 * 同一个组下面不能存在两个同名组
	 * @param parentId
	 * @param groupName
	 * @param groupId
	 * @return
	 */
	public boolean checkExist(Integer parentId, String groupName, Integer groupId);

	/**
	 * 创建一个新的分组
	 * @param group
	 * @return
	 */
	public boolean save(Group group);

	/**
	 * 通过分组id获取分组层级
	 * @param groupId
	 * @return
	 */
	public String getLevel(Integer groupId);

	/**
	 * 修改分组内容
	 * @param group
	 * @return
	 */
	public boolean updateGroup(Group group);

	/**
	 * 获取所有分组的详细信息
	 * @return
	 */
	public List<GroupVo> getListGroupvo();

	/**
	 * 删除分组
	 * @param group
	 * @return
	 */
	public boolean deleteGroup(Group group);

	/**
	 * 获取所有分组的详细信息
	 * @return
	 */
	public List<Group> getListGroup();
}
