package org.springblade.common.feign;


import com.alibaba.fastjson.JSONObject;
import org.springblade.common.constant.CommonConstant;
import org.springblade.common.config.FeignRequestInterceptor;
import org.springblade.common.dto.*;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = CommonConstant.APPLICATION_IAM_NAME,url = "${ylkj.iam.url}",fallbackFactory = ApiFallBackFactory.class,configuration = FeignRequestInterceptor.class )
public interface ApiClient {

	String USER_INFO = "/oauth/user-info";
	String PROJECT_TEAM = "/ylkj-api/projectItemByIds";
	String USER_DETAIL = "/blade-user/detail";

	/**
	 * 获取用户信息
	 * @return
	 */
	@GetMapping(USER_INFO)
	R<UserInfo> getUserInfo();


	@PostMapping(PROJECT_TEAM)
	R<List<ProjectTeamDTO>> projectItemByIds(@RequestBody String ids);

	@GetMapping(USER_DETAIL)
	R<UserDTO> getUserDetail(@RequestParam  Long id);

}
