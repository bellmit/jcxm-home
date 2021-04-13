package org.springblade.modules.group.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;
/**
 * <p>
 *
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
@Data
@ApiModel(value = "TbGroup对象", description = "分组")
@TableName("tb_group")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Group {

    private static final long serialVersionUID = 1L;


	private Integer id;

    /**
     * 小组名称
     */
    private String gname;

    /**
     * 小组父级id
     */
    @TableField("parent_id")
    private Integer parentId=0;

    /**
     * 小组层级
     */
    private String level;

    /**
     * 小组优先显示级别
     */
    private Integer seq;

    /**
     * 小组英文名称
     */
    private String gename;

    /**
     * 封面简介
     */
    private String content;

    /**
     * 关于
     */
    private String about;

	/**
	 * 判断是分组还是单位
	 */
	private String isgroup;

	/**
	 * 是否已经删除 0否 1是
	 */
	@TableField("is_deleted")
	private Integer isdeleted;




}
