package org.springblade.modules.group.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.group.entity.Achievement;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.mapper.AchievementMapper;
import org.springblade.modules.group.service.AchievementService;
import org.springblade.modules.group.vo.AchievementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AchievementServiceImpl extends ServiceImpl<AchievementMapper, Achievement> implements AchievementService {

	@Autowired
	private AchievementMapper achievementMapper;

	@Override
	public List<AchievementVO> getAllAchievements(Integer cid) {
		return achievementMapper.selectAllAchievements(cid);
	}


	@Override
	public List<AchievementVO> selectById(Integer id) {
		return achievementMapper.selectById(id);
	}

	@Override
	public boolean updateAchieveById(Achievement achievement) {
		return achievementMapper.update(achievement);
	}

	/**
	 * 增加
	 * @param achievement
	 * @return
	 */
	@Override
	public boolean insert(Achievement achievement) {
		return achievementMapper.insertAchievement(achievement);
	}

	@Override
	public boolean deleteById(Integer id) {
		return achievementMapper.deleteById(id);
	}
}

