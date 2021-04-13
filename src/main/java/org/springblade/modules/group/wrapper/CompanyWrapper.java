package org.springblade.modules.group.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.modules.notice.wrapper.ArticleWrapper;

public class CompanyWrapper extends BaseEntityWrapper {
	@Override
	public Object entityVO(Object entity) {
		return null;
	}
	public static CompanyWrapper build() {

		return new CompanyWrapper();
	}
}
