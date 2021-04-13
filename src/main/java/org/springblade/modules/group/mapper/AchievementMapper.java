package org.springblade.modules.group.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.group.entity.Achievement;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.vo.AchievementVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
public interface AchievementMapper extends BaseMapper<Achievement> {
	/**
	 * 获取单位下的所有成果
	 * @param cid
	 * @return
	 */
	List<AchievementVO> selectAllAchievements(@Param("cid") Integer cid);

	/**
	 * 查询某一成果详情
	 * @param id
	 * @return
	 */
	List<AchievementVO> selectById(@Param("id")Integer id);

	/**
	 * 更新
	 * @param achievement
	 * @return
	 */
	boolean update(Achievement achievement);


	/**
	 * 增加成果
	 * @param achievement
	 * @return
	 */
	boolean insertAchievement(Achievement achievement);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean deleteById(@Param("id") Integer id);



}
