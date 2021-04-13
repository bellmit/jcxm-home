package org.springblade.modules.group.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Expert;
import org.springblade.modules.group.vo.ExpertVO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
public interface ExpertMapper extends BaseMapper<Expert> {

	List<ExpertVO> selectAll(@Param("cid") Integer cid);

	List<ExpertVO> selectById(@Param("id")Integer id);

	boolean update(Expert expert);

	boolean insertExpert(Expert expert);

	boolean deleteById(@Param("id") Integer id);

}
