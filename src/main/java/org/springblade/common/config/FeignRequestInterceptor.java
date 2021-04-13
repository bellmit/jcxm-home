package org.springblade.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Feign 请求拦截器
 **/
@Order
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

	@Value("${ylkj.iam.client-id}")
	private String clientId;

	@Value("${ylkj.iam.client-secret}")
	private String clientSecret;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		requestTemplate.header(AuthUtil.ACCESS_TOKEN,AuthUtil.getToken());
		requestTemplate.header(AuthUtil.CLIENT_ID,clientId);
		requestTemplate.header(AuthUtil.CLIENT_SECRET,clientSecret);
	}
}
