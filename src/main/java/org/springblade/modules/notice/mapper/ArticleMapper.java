package org.springblade.modules.notice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.notice.entity.Article;

import java.util.List;


/**
 * Mapper接口
 *
 * @author wzx
 */
public interface ArticleMapper extends BaseMapper<Article> {



	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param type
	 * @return
	 */
	List<Article> selectArticlePage(IPage page, Integer type);


	/**
	 * 查询弹出
	 * @param type
	 * @return
	 */
	Article selectArticlePopup(Integer type);

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return
	 */
	Article selectById(String id);


	/**
	 * 自定义删除
	 *
	 * @param id
	 * @return
	 */
	int deleteById(String id);
}
