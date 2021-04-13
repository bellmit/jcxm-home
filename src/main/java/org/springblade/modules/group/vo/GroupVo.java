package org.springblade.modules.group.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springblade.modules.group.entity.Group;
import org.springframework.beans.BeanUtils;
@Data
@Getter
@Setter
public class GroupVo extends Group {
	/**
	 * 分组的封面图片url
	 */
	private String gimgurl;
	/**
	 * 分组的背景图片url
	 */
	private String gbgurl;

	//多封装静态方法，便于方法重载和使用
	public static GroupVo adapt(Group group) {
		GroupVo vo = new GroupVo();
		BeanUtils.copyProperties(group, vo);
		return vo;
	}

}
