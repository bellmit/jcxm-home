package org.springblade.modules.group.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springblade.modules.group.entity.Achievement;
import org.springblade.modules.group.vo.AchievementVO;
import org.springblade.modules.group.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 成果控制器
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/group/achievement")
@Api(value = "单位研究成果",tags = "研究成果")
public class AchievementController {
	@Autowired
	private AchievementService achievementService;

	@GetMapping("/getAll")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取所有项目成果", notes = "传入单位id")
	public R<List<AchievementVO>> getAllAchievements(@Valid @RequestParam("cid") Integer cid) {
		List<AchievementVO> achievementList=achievementService.getAllAchievements(cid);
		return R.data(achievementList);
	}

	@GetMapping("/getDetails")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "获取科研成果详情",notes = "传入achievement的id")
	public R<AchievementVO> getDetails(@Valid @RequestParam("id") Integer id){
		AchievementVO achievementDetails= achievementService.selectById(id);
		return R.data(achievementDetails);
	}

	@PostMapping("/update")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "更改", notes = "传入achievement对象")
	public R<Achievement> updateAchieveById(@Valid @RequestBody Achievement achievement) {
		return R.status(achievementService.updateAchieveById(achievement));
	}

	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增",notes = "传入achievement对象")
	public R<Achievement> insert(@Valid @RequestBody Achievement achievement){
		return R.status(achievementService.insert(achievement));
	}

	@PostMapping("/remove")
	@ApiOperationSupport(order =5)
	@ApiOperation(value = "删除",notes = "传入achievement的id")
	public R<Achievement> remove(@Valid @RequestParam("id") Integer id){
		return R.status(achievementService.deleteById(id));
	}
}
