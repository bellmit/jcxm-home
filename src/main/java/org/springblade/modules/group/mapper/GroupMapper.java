package org.springblade.modules.group.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Group;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.group.vo.GroupVo;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
public interface GroupMapper extends BaseMapper<Group> {
	/**
	 * 查询未删除的所有组
	 * @return
	 */
	List<Group> getAllGroup();
	Group selectByPrimaryKey(@Param("id")Integer id);
	int insertSelective(Group record);
	int iscompany(@Param("parentId") Integer parentId);
	int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Integer id);
	int updateGroup (Group group);
	List<Group> getGroupBylevel(@Param("level") String level);
	void batchUpdateLevel(@Param("group") Group group);

}
