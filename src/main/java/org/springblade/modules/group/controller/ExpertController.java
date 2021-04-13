package org.springblade.modules.group.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Expert;
import org.springblade.modules.group.service.ExpertService;
import org.springblade.modules.group.vo.ExpertVO;
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
@RequestMapping("/projectManage/expert")
@Api(value = "专家控制器",tags = "专家控制器")
public class ExpertController {
	@Autowired
	private ExpertService expertService;

	@GetMapping("/getAll")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询所有专家", notes = "传入单位id")
	public R<List<ExpertVO>> getAll(@Valid @RequestParam("cid") Integer cid) {
		List<ExpertVO> expertList=expertService.getAllExpert(cid);
		return R.data(expertList);
	}

	@GetMapping("/getDetails")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "专家详情",notes ="传入专家id")
	public R<List<ExpertVO>> getDetails(@Valid @RequestParam("id") Integer id){
		List<ExpertVO> expertDetails= expertService.getDetailsById(id);
		return R.data(expertDetails);
	}

	@PostMapping("/update")
	@ApiOperationSupport(order = 3)
	@ApiOperation(notes = "传入专家",value ="修改专家信息" )
	public R<Expert> updateById(@Valid @RequestBody Expert expert){
		return R.status(expertService.update(expert));
	}
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增专家",notes = "传入专家信息")
	public R<Expert> insert(@Valid @RequestBody Expert expert){
		return R.status(expertService.insertExpert(expert));
	}

	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "删除",notes = "传入专家id")
	public R<Expert> remove(@Valid @RequestParam("id") Integer id){
		return R.status(expertService.deleteById(id));
	}

}
