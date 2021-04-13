package org.springblade.common.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;


@Data
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户")
	private UserDTO user;

	@ApiModelProperty(value = "角色集合")
	private List<RoleDTO> roles;

	@ApiModelProperty(value = "部门集合")
	private DeptDTO dept;

	@ApiModelProperty(value = "实人")
	private PersonDTO person;

	@ApiModelProperty(value = "项目组ID")
	private List<ProjectTeamDTO> projectTeams;

	@ApiModelProperty(value = "专家信息")
	private ExpertDTO expert;

}
