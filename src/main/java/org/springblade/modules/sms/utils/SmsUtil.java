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
package org.springblade.modules.sms.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.modules.sms.entity.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

/**
 * 短信服务工具类
 *
 * @author Chill
 */
public class SmsUtil {



	private static final String DOMAIN = "dysmsapi.aliyuncs.com";
	private static final String VERSION = "2017-05-25";
	private static final String ACTION = "SendSms";

	public static final String PARAM_KEY = "code";
	public static final String SEND_SUCCESS = "短信发送成功";
	public static final String SEND_FAIL = "短信发送失败";
	public static final String VALIDATE_SUCCESS = "短信校验成功";
	public static final String VALIDATE_FAIL = "短信校验失败";


	public static IAcsClient smsClient(Sms sms) {
		IClientProfile profile = DefaultProfile.getProfile(sms.getRegionId(), sms.getAccessKey(), sms.getSecretKey());
		IAcsClient client = new DefaultAcsClient(profile);
		return client;
	}

	public static  CommonRequest smsRequest(Sms sms){
		CommonRequest request = new CommonRequest();
		request.setSysMethod(MethodType.POST);
		request.setSysDomain(DOMAIN);
		request.setSysVersion(VERSION);
		request.setSysAction(ACTION);
		return  request;
	}

	/**
	 * 发送短信
	 *
	 * @param sms   资源信息
	 * @param phone 手机号码
	 * @return 发送结果
	 */
	public static CommonResponse sendMessage(Sms sms,String params,String phone) throws ClientException {
		CommonRequest request = smsRequest(sms);
		request.putQueryParameter("PhoneNumbers", phone);
		request.putQueryParameter("TemplateCode", sms.getTemplateId());
		request.putQueryParameter("TemplateParam", params);
		request.putQueryParameter("SignName", sms.getSignName());
		request.putQueryParameter("RegionId", sms.getRegionId());
		IAcsClient client = smsClient(sms);
		return client.getCommonResponse(request);
	}

}
