package org.springblade.modules.group.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Group;
import org.springblade.modules.group.service.ICompanyService;
import org.springblade.modules.group.service.IUploadimgService;
import org.springblade.modules.group.vo.CompanyVo;
import org.springblade.modules.group.wrapper.CompanyWrapper;
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
@RequestMapping("/ylkj/tb-company")
public class CompanyController {
	@Autowired
	ICompanyService CompanyService;
	@Autowired
	IUploadimgService uploadimgService;

	/**
	 *增加单位
	 */
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "增加单位", notes = "传入dataScope")
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public R save(@Valid @RequestBody Company company, @RequestParam Integer groupId) {
		return R.status(CompanyService.saveCompany(company,groupId));
	}

	/**
	 * 从分组中删除单位
	 */
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "从分组中删除单位", notes = "传入dataScope")
	@RequestMapping(value = "/removeCompany", method = {RequestMethod.POST})
	public R removeCompany(@Valid @RequestBody Company company, @RequestParam Integer groupId) {
		return R.status(CompanyService.deleteCompany(company.getId(),groupId));
	}

	/**
	 * 根据分组获取分组下的单位
	 */
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "根据分组获取分组下的单位", notes = "传入dataScope")
	@GetMapping("/list")
	public R<List<Company>> list(@Valid @RequestBody Group group){
		List<Company> companyList  = CompanyService.getListTbCompanyForgroup(group);
		return R.data(companyList);
	}

	/**
	 * 修改单位内容
	 */
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改单位内容", notes = "传入dataScope")
	@RequestMapping(value = "/updateCompany", method = {RequestMethod.POST})
	public R updateCompany(@Valid @RequestBody Company company) {
		return R.status(CompanyService.updateCompany(company));
	}

	/**
	 * 获取分组下所有单位的详细信息
	 */
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "获取分组下所有单位的详细信息", notes = "传入dataScope")
	@GetMapping("/getListCompanyVo")
	public R<List<CompanyVo>> getListCompanyVo(@Valid @RequestBody Group group){
		List<CompanyVo> companyVoList  = CompanyService.getListTbCompanyVoForgroup(group);
		return R.data(companyVoList);
	}
	/**
	 * 获取分组下所有单位的信息
	 */
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "获取分组下所有单位的信息", notes = "传入dataScope")
	@PostMapping("/getListCompany")
	public R<List<Company>> getListCompany(@Valid @RequestBody Group group){
		List<Company> companyList  = CompanyService.getListTbCompanyForgroupbyname(group);
		return R.data(companyList);
	}

	/**
	 * 根据分组获取分组下的单位分页
	 * @param group
	 * @param query
	 * @return
	 */
	// TODO
	@GetMapping("/listPage")
	public R<IPage<Company>> list(Group group, Query query){
		IPage<Company> page = CompanyService.selectCompanyPage(Condition.getPage(query),group);
		return R.data(CompanyWrapper.build().pageVO(page));
	}
	/**
	 * 逻辑删除单位
	 */
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "逻辑删除单位", notes = "传入dataScope")
	@RequestMapping(value = "/deleteCompany", method = {RequestMethod.POST})
	public R deleteCompany(@Valid @RequestBody Company company) {
		return R.status(CompanyService.delCompany(company));
	}
}
