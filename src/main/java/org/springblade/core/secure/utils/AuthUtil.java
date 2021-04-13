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
package org.springblade.core.secure.utils;


import org.springblade.common.dto.UserInfo;
import org.springblade.common.feign.ApiClient;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Auth工具类
 *
 * @author Chill
 */

public class AuthUtil {
	private static final String YL_USER_REQUEST_ATTR = "_YL_USER_REQUEST_ATTR_";
	public final static String ACCESS_TOKEN = "access_token";
	public final static String CLIENT_ID = "client_id";
	public final static String CLIENT_SECRET = "client_secret";

	private static ApiClient APICLIENT;
	static {
		APICLIENT = SpringUtil.getBean(ApiClient.class);
	}

	/**
	 * 获取用户信息
	 *
	 * @return BladeUser
	 */
	public static BladeUser getUser() {
		HttpServletRequest request = WebUtil.getRequest();
		if (request == null) {
			return null;
		}
		// 优先从 request 中获取
		Object bladeUser = request.getAttribute(YL_USER_REQUEST_ATTR);
		if (bladeUser == null) {
			R<UserInfo> userInfo = APICLIENT.getUserInfo();
			if(null != userInfo.getData()){
				bladeUser = BladeUser.parse(userInfo.getData());
			}
			if (bladeUser != null) {
				request.setAttribute(YL_USER_REQUEST_ATTR, bladeUser);
			}
		}
		return (BladeUser) bladeUser;
	}

	public static String getToken(){
		HttpServletRequest request = WebUtil.getRequest();
		String token = request.getHeader(AuthUtil.ACCESS_TOKEN);
		if (token == null) {
			token = request.getParameter(AuthUtil.ACCESS_TOKEN);
		}
		return token;
	}


	public static List<Long> getRoleIds(){
		return getUser().getRoleIds();
	}


	/**
	 * 是否为超管
	 *
	 * @return boolean
	 */
	public static boolean isAdministrator() {
		return StringUtil.containsAny(getUserRole(), RoleConstant.ADMINISTRATOR);
	}
	/**
	 * 获取用户id
	 *
	 * @return userId
	 */
	public static Long getUserId() {
		BladeUser user = getUser();
		return (null == user) ? -1 : user.getUserId();
	}


	/**
	 * 获取用户角色
	 *
	 * @return userName
	 */
	public static String getUserRole() {
		BladeUser user = getUser();
		return (null == user) ? StringPool.EMPTY : user.getRoleAlias();
	}

	public static String getUserAccount(HttpServletRequest request) {
		BladeUser user = getUser();
		return (null == user.getUserInfo()) ? StringPool.EMPTY : user.getUsername();
	}

	public static String getTenantId(){
		return StringPool.EMPTY;
	}

}
