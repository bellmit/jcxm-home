package org.springblade.modules.group.vo;

import lombok.Data;
import org.springblade.modules.group.entity.Achievement;

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
	 * 图片url
	 */
	private String url;
}
