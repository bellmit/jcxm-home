/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.sms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 短信配置表实体类
 *
 * @author BladeX
 * @since 2020-02-20
 */
@Data
@TableName("blade_sms")
@ApiModel(value = "Sms对象", description = "短信配置表")
public class Sms{

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;

	/**
	 * 模版标题
	 */
	private String title;


	/**
	 * 模板ID
	 */
	@ApiModelProperty(value = "模板ID")
	private String templateId;


	/**
	 * 模板ID
	 */
	@ApiModelProperty(value = "模板内容")
	private String templateContent;

	/**
	 * accessKey
	 */
	@ApiModelProperty(value = "accessKey")
	private String accessKey;
	/**
	 * secretKey
	 */
	@ApiModelProperty(value = "secretKey")
	private String secretKey;
	/**
	 * regionId
	 */
	@ApiModelProperty(value = "regionId")
	private String regionId;
	/**
	 * 短信签名
	 */
	@ApiModelProperty(value = "短信签名")
	private String signName;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	private Date createAt;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	private Date updateAt;

	@JsonSerialize(using = ToStringSerializer.class)
	private Long createUser;

	@JsonSerialize(using = ToStringSerializer.class)
	private Long updateUser;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;

	/**
	 * 是否删除
	 */
	private Integer isDeleted;

	/**
	 * 状态
	 */
	private Integer status;


}
