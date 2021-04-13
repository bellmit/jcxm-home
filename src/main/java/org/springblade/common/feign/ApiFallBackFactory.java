package org.springblade.common.feign;

import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;
import org.springblade.common.dto.ProjectTeamDTO;
import org.springblade.common.dto.UserDTO;
import org.springblade.common.dto.UserInfo;
import org.springblade.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ApiFallBackFactory implements FallbackFactory<ApiClient> {
	@Override
	public ApiClient create(Throwable throwable) {

		return new ApiClient() {
			@Override
			public R<UserInfo> getUserInfo() {
				return R.fail("获取数据失败");
			}

			@Override
			public R<List<ProjectTeamDTO>> projectItemByIds(String ids) {
				return R.fail("获取数据失败");
			}

			@Override
			public R<UserDTO> getUserDetail(Long id) {
				return R.fail("获取数据失败");
			}
		};
	}
}
