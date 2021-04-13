package org.springblade.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springblade.core.tool.node.INode;

import java.io.Serializable;
import java.util.List;

@Data
public class ProjectTeamDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	@JsonSerialize(using = ToStringSerializer.class)
	private Long parentId;

	private String name;

	private String ancestors;

	private Integer sort;

	private String remark;

	private String creditCode;

	private  String address;

	private String contacts;

	private String contactPhone;

	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<INode> children;

	/**
	 * 是否有子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Boolean hasChildren;

}
