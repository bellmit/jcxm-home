package org.springblade.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	private String name;

	private String sfz;

	private Integer sex;

	private String phone;

	private String tel;

	private String address;

	private String email;

	private String nation;

	private String unit;

	private String education;

	private String degree;

	private String remark;

	private String projectTeamId;

	private Long userId;

}
