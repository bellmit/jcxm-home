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
package org.springblade.modules.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.system.entity.RoleMenu;
import org.springblade.modules.system.entity.RoleScope;
import org.springblade.modules.system.service.IRoleMenuService;
import org.springblade.modules.system.service.IRoleScopeService;
import org.springblade.modules.system.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

import static org.springblade.common.constant.CommonConstant.API_SCOPE_CATEGORY;
import static org.springblade.common.constant.CommonConstant.DATA_SCOPE_CATEGORY;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@Validated
@AllArgsConstructor
public class RoleServiceImpl implements IRoleService {

	private IRoleMenuService roleMenuService;
	private IRoleScopeService roleScopeService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean grant(@NotEmpty String roleId, List<Long> menuIds, List<Long> dataScopeIds, List<Long> apiScopeIds) {
		// 删除角色配置的菜单集合
		roleMenuService.remove(Wrappers.<RoleMenu>update().lambda().eq(RoleMenu::getRoleId, roleId));
		// 组装配置
		List<RoleMenu> roleMenus = new ArrayList<>();
		menuIds.forEach(menuId -> {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleId(Func.toLong(roleId));
			roleMenu.setMenuId(menuId);
			roleMenus.add(roleMenu);
		});
		// 新增配置
		roleMenuService.saveBatch(roleMenus);

		// 删除角色配置的数据权限集合
		roleScopeService.remove(Wrappers.<RoleScope>update().lambda().eq(RoleScope::getScopeCategory, DATA_SCOPE_CATEGORY).eq(RoleScope::getRoleId, roleId));
		// 组装配置
		List<RoleScope> roleDataScopes = new ArrayList<>();
		 dataScopeIds.forEach(scopeId -> {
			RoleScope roleScope = new RoleScope();
			roleScope.setScopeCategory(DATA_SCOPE_CATEGORY);
			roleScope.setRoleId(Func.toLong(roleId));
			roleScope.setScopeId(scopeId);
			roleDataScopes.add(roleScope);
		});
		// 新增配置
		roleScopeService.saveBatch(roleDataScopes);

		// 删除角色配置的接口权限集合
		roleScopeService.remove(Wrappers.<RoleScope>update().lambda().eq(RoleScope::getScopeCategory, API_SCOPE_CATEGORY).in(RoleScope::getRoleId, roleId));
		// 组装配置
		List<RoleScope> roleApiScopes = new ArrayList<>();
		apiScopeIds.forEach(scopeId -> {
			RoleScope roleScope = new RoleScope();
			roleScope.setScopeCategory(API_SCOPE_CATEGORY);
			roleScope.setScopeId(scopeId);
			roleScope.setRoleId(Func.toLong(roleId));
			roleApiScopes.add(roleScope);
		});
		// 新增配置
		roleScopeService.saveBatch(roleApiScopes);

		return true;
	}
}
