package org.springblade.modules.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.protobuf.ServiceException;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.notice.entity.Article;

public interface IArticleService extends IService<Article> {


	/**
	 * 查询弹出窗
	 * @param type
	 * @return
	 */
	Article selectArticlePopup(Integer type);
	/**
	 * 自定义分页
	 *
	 * @param article
	 * @return
	 */
	IPage<Article> selectArticlePage(IPage<Article> page, Article article);

	/**
	 * 标记删除
	 * @param id
	 * @return
	 */
	boolean remove(String id);


}
