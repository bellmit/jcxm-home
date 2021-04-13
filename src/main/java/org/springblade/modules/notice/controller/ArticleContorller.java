package org.springblade.modules.notice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.oss.OssTemplate;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.notice.entity.Article;
import org.springblade.modules.notice.service.IArticleService;
import org.springblade.modules.notice.vo.ArticleVO;
import org.springblade.modules.notice.wrapper.ArticleWrapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("ylkj-notice/article")
public class ArticleContorller {

	private IArticleService articleService;

	@PostMapping("/submit")
	public R submit(@RequestBody Article article){
		try {
			Date date = new Date();
			if(article.getId()==null){
				article.setCreateAt(date);
			}
			article.setUpdateAt(date);
			return R.status(articleService.saveOrUpdate(article));
		}catch (Exception e){
			e.printStackTrace();
			return R.status(false);
		}
	}

	@GetMapping("/list")
	public R<IPage<ArticleVO>> list(Article article, Query query){
		IPage<Article> page = articleService.selectArticlePage(Condition.getPage(query),article);
		return R.data(ArticleWrapper.build().pageVO(page));
	}

	@GetMapping("/popup")
	public R<ArticleVO> popup(Integer type){
		Article article = articleService.selectArticlePopup(type);
		ArticleVO articleVO = null;
		if(article!=null){
			articleVO = ArticleWrapper.build().entityVO(article);
		}
		return R.data(articleVO);
	}

	@GetMapping("/detail")
	public R<ArticleVO> detail(String id){
		if(StringUtil.isEmpty(id)) throw new Error("无效ID");
		Article article = articleService.getById(id);
		ArticleVO articleVO = null;
		if(article!=null){
			articleVO = ArticleWrapper.build().entityVO(article);
		}
		return R.data(articleVO);
	}

	@PostMapping("/remove")
	public R remove(@RequestBody  Article article){
		if(StringUtil.isEmpty(article.getId())) throw new Error("无效ID");
		return R.status(articleService.remove(article.getId()));
	}

}
