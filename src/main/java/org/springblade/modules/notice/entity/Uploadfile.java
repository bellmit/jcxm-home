package org.springblade.modules.notice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName(value = "uploadfile")
@ApiModel(value = "Uploadfile对象", description = "Uploadfile对象")
public class Uploadfile {

	@TableId(value = "id",type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 文件名称
	 */
	private String name;
	/**
	 * 文件后缀名
	 */
	private String suffix;

	/**
	 * 文件路径
	 */
	private String url;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	private Date createAt;
	/**
	 * 文件大小
	 */
	private Long fileSize;

	/**
	 * 文件类型
	 */
	private String fileType;

}
