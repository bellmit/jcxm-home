package org.springblade.modules.group.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springblade.modules.group.entity.Company;
import org.springframework.beans.BeanUtils;

@Data
@Getter
@Setter
public class CompanyVo extends Company {
	/**
	 * 单位log的url
	 */
	private String clogurl;
	//多封装静态方法，便于方法重载和使用
	public static CompanyVo adapt(Company company) {
		CompanyVo vo = new CompanyVo();
		BeanUtils.copyProperties(company, vo);
		return vo;
	}
}
