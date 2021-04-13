package org.springblade.core.secure.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("ylkj.iam")
public class YLKJIAMProperties {
	private String url;
	private String clientId;
	private String clientSecret;
}
