package org.springblade.modules.notice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName(value = "article")
@ApiModel(value = "Article对象", description = "通知通告表")
public class Article {

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 摘要
	 */
	private String summary;
	/**
	 * 内容
	 */
	private String content;

	/**
	 *是否置顶
	 */
	private Integer isTop;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	private Date createAt;
	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	private Date updateAt;
	/**
	 * 上传文件ID
	 */
	private String uploadfileId;
	/**
	 * 上传图片路径
	 */
	private String imageUrl;
	/**
	 * 是否删除
	 */
	private Integer isDeleted;
	/**
	 * 浏览次数
	 */
	private Long browseTimes;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 类型 1:是通知公告，2:是新闻
	 */
	private Integer type;
	/**
	 * 修改热
	 */
	private Long updateUser;
	/**
	 * 创建人
	 */
	private Long createUser;
	/**
	 * 创建部门
	 */
	private Long createDept;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 展示类型 0：默认列表   1：弹窗显示，2：移动显示，3：滚动显示
	 */
	private Integer showType;

	/**
	 * 开始时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date startTime;

	/**
	 * 结束时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	private Date endTime;


}
