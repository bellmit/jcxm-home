package org.springblade.modules.group.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_expert")
public class Expert extends Model<Expert> {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "专家主键")
	private int id;
	/**
	 * 专家姓名
	 */
	private String name;

	/**
	 * 专家职称
	 */
	private String title;

	/**
	 * 专家介绍url
	 */
	@TableField("content")
	private String content;

	/**
	 * 单位id
	 */
	private int cid;

	/**
	 * 专家图片url
	 */
	@TableField("is_deleted")
	private String isDeleted;

	private String sex;
	/**
	 * 创建时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createAt;
	/**
	 * 更新时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateAt;


	@Override
	protected Serializable pkVal() {
		return null;
	}

}
