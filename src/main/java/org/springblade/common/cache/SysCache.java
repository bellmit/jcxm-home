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
package org.springblade.common.cache;

import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.modules.system.entity.*;
import org.springblade.modules.system.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springblade.core.cache.constant.CacheConstant.SYS_CACHE;

/**
 * 系统缓存
 *
 * @author Chill
 */
public class SysCache {
	private static final String MENU_ID = "menu:id:";

	private static IMenuService menuService;

	static {
		menuService = SpringUtil.getBean(IMenuService.class);
	}

	/**
	 * 获取菜单
	 *
	 * @param id 主键
	 * @return
	 */
	public static Menu getMenu(Long id) {
		return CacheUtil.get(SYS_CACHE, MENU_ID, id, () -> menuService.getById(id));
	}
}
