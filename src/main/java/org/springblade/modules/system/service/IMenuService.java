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
package org.springblade.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.support.Kv;
import org.springblade.modules.system.entity.Menu;
import org.springblade.modules.system.vo.MenuVO;

import java.util.List;
import java.util.Map;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IMenuService extends IService<Menu> {

	/**
	 * 懒加载列表
	 *
	 * @param parentId
	 * @param param
	 * @return
	 */
	List<MenuVO> lazyList(Long parentId, Map<String, Object> param);

	/**
	 * 懒加载菜单列表
	 *
	 * @param parentId
	 * @param param
	 * @return
	 */
	List<MenuVO> lazyMenuList(Long parentId, Map<String, Object> param);

	/**
	 * 菜单树形结构
	 *
	 * @return
	 */
	List<MenuVO> routes();

	/**
	 * 按钮树形结构
	 *
	 * @return
	 */
	List<MenuVO> buttons();

	/**
	 * 树形结构
	 *
	 * @return
	 */
	List<MenuVO> tree();

	/**
	 * 授权树形结构
	 *
	 * @return
	 */
	List<MenuVO> grantTree();

	/**
	 * 顶部菜单树形结构
	 *
	 * @return
	 */
	List<MenuVO> grantTopTree();

	/**
	 * 数据权限授权树形结构
	 *
	 * @return
	 */
	List<MenuVO> grantDataScopeTree();

	/**
	 * 接口权限授权树形结构
	 *
	 * @return
	 */
	List<MenuVO> grantApiScopeTree();

	/**
	 * 默认选中节点
	 *
	 * @param roleIds
	 * @return
	 */
	List<String> roleTreeKeys(String roleIds);

	/**
	 * 默认选中节点
	 *
	 * @param roleIds
	 * @return
	 */
	List<String> dataScopeTreeKeys(String roleIds);

	/**
	 * 默认选中节点
	 *
	 * @param roleIds
	 * @return
	 */
	List<String> apiScopeTreeKeys(String roleIds);

	/**
	 * 获取配置的角色权限
	 *
	 * @return
	 */
	List<Kv> authRoutes();

	/**
	 * 删除菜单
	 *
	 * @param ids
	 * @return
	 */
	boolean removeMenu(String ids);

	/**
	 * 提交
	 *
	 * @param menu
	 * @return
	 */
	boolean submit(Menu menu);

}
