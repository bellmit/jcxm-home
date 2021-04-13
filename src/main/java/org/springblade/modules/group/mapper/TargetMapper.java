package org.springblade.modules.group.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Target;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
public interface TargetMapper extends BaseMapper<Target> {
	/**
	 * 查询所有
	 * @param company
	 * @return
	 */
	List<Target> selectAll(@Param("c")Company company);

	/**
	 * 目标详情
	 * @param id
	 * @return
	 */
	Target selectById(@Param("id")Integer id);

	/**
	 * 更新
	 * @param target
	 * @return
	 */
	boolean update(Target target);

	/**
	 * 新增
	 * @param target
	 * @return
	 */
	boolean insertTarget(Target target);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean deleteById(@Param("id") Integer id);

}
