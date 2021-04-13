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
package org.springblade.core.datascope.handler;

import org.springblade.core.datascope.model.DataScopeModel;

import java.util.List;

/**
 * 获取数据权限模型统一接口
 *
 * @author Chill
 */
public interface ScopeModelHandler {

	/**
	 * 获取数据权限
	 *
	 * @param mapperId 数据权限mapperId
	 * @return DataScopeModel
	 */
	DataScopeModel getDataScopeByMapper(String mapperId);

	/**
	 * 获取数据权限
	 *
	 * @param code 数据权限资源编号
	 * @return DataScopeModel
	 */
	DataScopeModel getDataScopeByCode(String code);

}
