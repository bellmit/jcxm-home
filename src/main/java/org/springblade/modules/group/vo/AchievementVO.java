package org.springblade.modules.group.vo;

import lombok.Data;
import org.springblade.modules.group.entity.Achievement;
import org.springblade.modules.group.entity.Company;
import org.springframework.beans.BeanUtils;

/**
 * @author zjm
 * @version 1.0
 * @date 2021/4/1 11:01
 * @description 视图实体类
 */
@Data
public class AchievementVO extends Achievement {
	private static final long serialVersionUID = 1L;
	/**
	 * 成果封面url
	 */
	private String abgurl;
	/**
	 * 成果详情封面
	 */
	private String aimgurl;
	public static AchievementVO ach(Achievement achievement) {
		AchievementVO vo = new AchievementVO();
		BeanUtils.copyProperties(achievement, vo);
		return vo;
	}
}
