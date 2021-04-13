package org.springblade.modules.notice.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.notice.entity.Article;
import org.springblade.modules.notice.entity.Uploadfile;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleVO extends Article {

	/**
	 * 附件列表
	 */
	private List<Uploadfile> fileList;

}
