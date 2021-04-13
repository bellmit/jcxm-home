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
package org.springblade.modules.sms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.sms.entity.Sms;

import java.util.List;


/**
 * 短信配置表 服务类
 *
 * @author BladeX
 * @since 2020-02-20
 */
public interface ISmsService extends IService<Sms> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param sms
	 * @return
	 */
	IPage<Sms> selectSmsPage(IPage<Sms> page, Sms sms);

	/**
	 * 查询所有数据
	 * @return
	 */
	List<Sms> findAll();

	/**
	 * 提交oss信息
	 *
	 * @param oss
	 * @return
	 */
	boolean submit(Sms oss);

	/**
	 * 启动配置
	 *
	 * @param id
	 * @return
	 */
	boolean enable(String id);

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	boolean remove(List<String> ids);

}
