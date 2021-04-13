package org.springblade.modules.group.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Target;
import org.springblade.modules.group.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/projectManage/target")
public class TargetController {
	@Autowired
	private TargetService targetService;

	@GetMapping("/getAll")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询所有科研目标", notes = "传入单位")
	public R<List<Target>> getAll(@Valid  @RequestBody Company company) {
		List<Target> targetList=targetService.selectAll(company);
		return R.data(targetList);
	}

	@GetMapping("/getDetails")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "科研目标详情", notes = "传入科研目标id")
	public R<Target> getDetails(@Valid @RequestParam("id") Integer id) {
		Target target= targetService.selectById(id);
		return R.data(target);
	}

	@PostMapping("/update")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "修改", notes = "传入target对象")
	public R<Target> updateById(@Valid @RequestBody Target target) {
		return R.status(targetService.update(target));
	}

	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增目标" ,notes = "传入target")
	public R<Target> insert(@Valid @RequestBody Target target){
		return R.status(targetService.insertTarget(target));
	}

	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "删除",notes = "传入")
	public R<Target> remove(@Valid @RequestParam("id") Integer id){
		return R.status(targetService.deleteById(id));
	}

}
