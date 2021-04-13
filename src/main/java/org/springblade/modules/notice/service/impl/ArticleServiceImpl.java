package org.springblade.modules.notice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.modules.notice.entity.Article;
import org.springblade.modules.notice.mapper.ArticleMapper;
import org.springblade.modules.notice.service.IArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

	@Override
	public Article selectArticlePopup(Integer type) {
		return baseMapper.selectArticlePopup(type);
	}

	@Override
	public IPage<Article> selectArticlePage(IPage<Article> page, Article article) {
		return page.setRecords(baseMapper.selectArticlePage(page,article.getType()));
	}

	@Override
	public boolean remove(String id) {
		return baseMapper.deleteById(id) > 0;
	}


}
