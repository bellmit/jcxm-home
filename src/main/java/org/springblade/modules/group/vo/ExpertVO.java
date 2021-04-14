package org.springblade.modules.group.vo;

import lombok.Data;
import org.springblade.modules.group.entity.Achievement;
import org.springblade.modules.group.entity.Expert;
import org.springframework.beans.BeanUtils;

/**
 * @author zjm
 * @version 1.0
 * @date 2021/4/6 14:20
 * @description expert视图类
 */
@Data
public class ExpertVO extends Expert {

	private String eimgurl;

	public static ExpertVO exp(Expert expert) {
		ExpertVO vo = new ExpertVO();
		BeanUtils.copyProperties(expert, vo);
		return vo;
	}
}

