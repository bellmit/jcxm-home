package org.springblade.modules.group.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
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
@TableName("tb_achievements")
@ApiModel(value = "Achievements对象", description = "成果表")
public class Achievement extends Model<Achievement> {

	private static final long serialVersionUID = 1L;
	@NotNull(message = "id不能为空")
	private int id;

	/**
	 * 成果名称
	 */
	private String name;

	private String brief;

	/**
	 * 成果介绍
	 */
	@TableField("content")
	private String content;

	/**
	 * 单位id
	 */
	private int cid;
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
	/**
	 * 是否删除 0为否 1为是
	 */

	private Integer isDeleted;


}
