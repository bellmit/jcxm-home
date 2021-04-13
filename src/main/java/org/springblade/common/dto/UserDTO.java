package org.springblade.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	private String phone;

	private String nickname;

	private String email;

	private String avatar;

	private String roleId;

	private String deptId;

	private String lastTime;

	private String remark;

	private Long personId;

	private Integer status;

}
