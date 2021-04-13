package org.springblade.modules.group.dto;

import java.util.List;

import org.springblade.modules.group.entity.Group;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Administrator
 * 分组层级树模型
 * 含义是：该分组dto中包含子分组层级模型集合空集， 用于等待存储即将放入到结构中做页面呈现的dto
 * dto是直接跟数据库交互的数据传输对象
 * Data Transfer Object
 * 为了减少数据字段的重复书写，采用继承pojo的模式，引用pojo的字段
 */
@Getter
@Setter
@ToString
public class GroupDto extends Group {
	//静态方法简化泛型书写，实际上等同于
	//List<T> list=new ArrayList<T>();
	private List<GroupDto> groupList = Lists.newArrayList();
	//将pojo中的字段传递给跟数据层交互的dto
	//多封装静态方法，便于方法重载和使用
	public static GroupDto adapt(Group group) {
		GroupDto dto = new GroupDto();
		BeanUtils.copyProperties(group, dto);
		return dto;
	}
}
