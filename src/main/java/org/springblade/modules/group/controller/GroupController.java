package org.springblade.modules.group.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.group.dto.GroupDto;

import org.springblade.modules.group.entity.Group;
import org.springblade.modules.group.service.IGroupService;
import org.springblade.modules.group.service.impl.GroupTreeService;
import org.springblade.modules.group.service.impl.TreeService;
import org.springblade.modules.group.vo.GroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/ylkj/tb-group")
@Api(value = "分组", tags = "分组接口")
public class GroupController extends BladeController {
	@Autowired
	private TreeService treeService;

	@Autowired
	private GroupTreeService groupTreeService;

	@Autowired
	private IGroupService tbGroupService;
	/**
	 * 树形json
	 */
	@GetMapping("/tree")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "树形json")
	public R<List<GroupDto>> tree() {
		List<GroupDto> dtoList = treeService.groupTree();
		return R.data(dtoList);
	}

	/**
	 * 分组树形json
	 */
	@GetMapping("/Grouptree")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "树形json")
	public R<List<GroupDto>> Grouptree() {
		List<GroupDto> dtoList = groupTreeService.groupTree();
		return R.data(dtoList);
	}

	/**
	 * 添加分组
	 */
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "添加分组", notes = "传入dataScope")
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public R save(@Valid @RequestBody Group tbgroup) {

		return R.status(tbGroupService.save(tbgroup));
	}

	/**
	 * 修改分组
	 */
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "修改分组", notes = "传入dataScope")
	@RequestMapping(value = "/updateGroup", method = {RequestMethod.POST})
	public R updateGroup(@Valid @RequestBody Group Group) {
		return R.status(tbGroupService.updateGroup(Group));
	}

	/**
	 * 获取所有分组的信息
	 */
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "获取所有分组的信息", notes = "传入dataScope")
	@RequestMapping(value = "/getListGroup", method = {RequestMethod.GET})
	public R<List<Group>> getListGroup() {
		List<Group> list=tbGroupService.getListGroup();
		return R.data(list);
	}
	/**
	 * 获取所有分组的详细信息
	 */
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "获取所有分组的详细信息", notes = "传入dataScope")
	@RequestMapping(value = "/getListGroupvo", method = {RequestMethod.GET})
	public R<List<GroupVo>> getListGroupvo() {
		List<GroupVo> list=tbGroupService.getListGroupvo();
		return R.data(list);
	}

	/**
	 * 删除分组
	 * @param Group
	 * @return
	 */
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "删除分组", notes = "传入dataScope")
	@RequestMapping(value = "/deleteGroup", method = {RequestMethod.POST})
	public R deleteGroup(@Valid @RequestBody Group Group) {
		return R.status(tbGroupService.deleteGroup(Group));
	}

}
