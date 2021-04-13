package org.springblade.modules.group.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.group.entity.Achievement;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.vo.AchievementVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
public interface AchievementService extends IService<Achievement> {
	/**
	 * 获取单位下的所有科研成果
	 * @param cid
	 * @return
	 */
	List<AchievementVO> getAllAchievements(Integer cid);

	/**
	 *研究成果详细信息
	 * @param id
	 * @return
	 */
	List<AchievementVO> selectById(Integer id);

	/**
	 * 更新
	 * @param achievement
	 * @return
	 */
	boolean updateAchieveById(Achievement achievement);
	/**
	 * 增加成果
	 * @param achievement
	 * @return
	 */
	boolean insert(Achievement achievement);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean deleteById( Integer id);
}
