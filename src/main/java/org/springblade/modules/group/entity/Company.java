package org.springblade.modules.group.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_company")
public class Company {

    private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private int id;

    /**
     * 单位名称
     */
    private String cname;


    /**
     * 单位介绍
     */
    private String ccontent;

	/**
	 * 是否已经删除 0否 1是
	 */
	@TableField("is_deleted")
	private Integer isdeleted;



}
