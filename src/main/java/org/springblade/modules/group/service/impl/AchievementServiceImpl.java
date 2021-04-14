package org.springblade.modules.group.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.group.entity.Achievement;
import org.springblade.modules.group.mapper.AchievementMapper;
import org.springblade.modules.group.mapper.UploadimgMapper;
import org.springblade.modules.group.service.AchievementService;
import org.springblade.modules.group.vo.AchievementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	@Autowired
	private UploadimgMapper uploadimgMapper;

	@Override
	public List<AchievementVO> getAllAchievements(Integer cid) {
		//拿到研究成果列表
		List<Achievement> achievementList=achievementMapper.selectAllAchievements(cid);
		List<AchievementVO> achievementVOList=new ArrayList<>();
		for (Achievement achievement:achievementList){
			AchievementVO achievementVO=AchievementVO.ach(achievement);

			if (uploadimgMapper.findByabgid(achievement.getId())!=null){
				//拿到成果的封面
				achievementVO.setAbgurl(uploadimgMapper.findByabgid(achievement.getId()).getUrl());
			}
			if(uploadimgMapper.findByaimgid(achievement.getId())!=null){
				//拿到成果详情图
				achievementVO.setAimgurl(uploadimgMapper.findByaimgid(achievement.getId()).getUrl());
			}
			achievementVOList.add(achievementVO);
		}
		return achievementVOList;
	}


	@Override
	public AchievementVO selectById(Integer id) {
		//拿到研究成果
		Achievement achievement=achievementMapper.selectById(id);
		//研究成果VO
		AchievementVO achievementVO=AchievementVO.ach(achievement);
			if (uploadimgMapper.findByabgid(achievement.getId())!=null){
				//拿到成果的封面
				achievementVO.setAbgurl(uploadimgMapper.findByabgid(achievement.getId()).getUrl());
			}
			if(uploadimgMapper.findByaimgid(achievement.getId())!=null){
				//拿到成果详情图
				achievementVO.setAimgurl(uploadimgMapper.findByaimgid(achievement.getId()).getUrl());
			}
			//返回VO
			return achievementVO;
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

