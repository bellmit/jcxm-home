package org.springblade.modules.notice.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.notice.entity.Article;
import org.springblade.modules.notice.service.IUploadfileService;
import org.springblade.modules.notice.vo.ArticleVO;

import java.util.List;
import java.util.Objects;

public class ArticleWrapper extends BaseEntityWrapper<Article, ArticleVO> {

	private static IUploadfileService uploadfileService;

	public static ArticleWrapper build() {
		uploadfileService = SpringUtil.getBean(IUploadfileService.class);
		return new ArticleWrapper();
	}


	@Override
	public ArticleVO entityVO(Article article) {
		ArticleVO articleVO = Objects.requireNonNull(BeanUtil.copy(article,ArticleVO.class));
		String id = article.getUploadfileId();
		if(StringUtil.isNotBlank(id)){
			List<String> ids = Func.toStrList(id);
			articleVO.setFileList(uploadfileService.findByIds(ids));
		}
		return articleVO;
	}
}
