/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.sms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.common.dto.PersonDTO;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.sms.dto.SmsDTO;
import org.springblade.modules.sms.entity.Sms;
import org.springblade.modules.sms.entity.SmsLogs;
import org.springblade.modules.sms.service.ISmsLogsService;
import org.springblade.modules.sms.service.ISmsService;
import org.springblade.modules.sms.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springblade.core.cache.constant.CacheConstant.RESOURCE_CACHE;

/**
 * 短信配置表 控制器
 *
 * @author BladeX
 * @since 2020-02-20
 */
@ApiIgnore
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/sms")
@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
@Api(value = "短信配置表", tags = "短信配置表接口")
public class SmsController extends BladeController {

	@Autowired
	private ISmsService smsService;

	@Autowired
	private BladeRedis bladeRedis;

	@Autowired
	private ISmsLogsService smsLogsService;

	private static final int COUNT = 5;

	private static final String RED_PREFIX = "sms:phone:";

	@GetMapping("/all")
	public R<List<Sms>> all() {
		List<Sms> list = smsService.findAll();
		return R.data(list);
	}

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sms")
	public R<Sms> detail(Sms sms) {
		Sms detail = smsService.getOne(Condition.getQueryWrapper(sms));
		return R.data(detail);
	}

	/**
	 * 分页 短信配置表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sms")
	public R<IPage<Sms>> list(Sms sms, Query query) {
		IPage<Sms> pages = smsService.page(Condition.getPage(query), Condition.getQueryWrapper(sms));
		return R.data(pages);
	}


	/**
	 * 自定义分页 短信配置表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入sms")
	public R<IPage<Sms>> page(Sms sms, Query query) {
		IPage<Sms> pages = smsService.selectSmsPage(Condition.getPage(query), sms);
		return R.data(pages);
	}

	/**
	 * 新增 短信配置表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入sms")
	public R save(@Valid @RequestBody Sms sms) {
		CacheUtil.clear(RESOURCE_CACHE);
		return R.status(smsService.save(sms));
	}

	/**
	 * 修改 短信配置表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入sms")
	public R update(@Valid @RequestBody Sms sms) {
		CacheUtil.clear(RESOURCE_CACHE);
		return R.status(smsService.updateById(sms));
	}

	/**
	 * 新增或修改 短信配置表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入sms")
	public R submit(@Valid @RequestBody Sms sms) {
		CacheUtil.clear(RESOURCE_CACHE);
		return R.status(smsService.submit(sms));
	}


	/**
	 * 删除 短信配置表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		CacheUtil.clear(RESOURCE_CACHE);
		return R.status(smsService.remove(Func.toStrList(ids)));
	}

	/**
	 * 启用
	 */
	@PostMapping("/enable")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "配置启用", notes = "传入id")
	public R enable(@ApiParam(value = "主键", required = true) @RequestParam String id) {
		CacheUtil.clear(RESOURCE_CACHE);
		return R.status(smsService.enable(id));
	}

	@PostMapping("/send-message")
	public R send(@RequestBody SmsDTO sms) {
		if (StringUtil.isEmpty(sms.getPhoneNumbers())) {
			throw new Error("发送的手机号不能为空");
		}
		List<PersonDTO> persons = JSON.parseArray(sms.getPhoneNumbers(), PersonDTO.class);
		JSONArray params = JSON.parseArray(sms.getParams());
		Map<String,String> map = new HashMap<>();
		for(int i=0;i<params.size();i++) {
			JSONObject obj = params.getJSONObject(i);
			map.put(obj.getString("key"),obj.getString("value"));
		}
		long current = System.currentTimeMillis() / 1000;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		long end = 0;
		String date = df.format(new Date());
		try {
			end = df.parse(date).getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time = current - end;
		for (PersonDTO p : persons) {
			SmsLogs smsLogs = new SmsLogs();
			String key = RED_PREFIX + DigestUtils.md5DigestAsHex(p.getPhone().getBytes());
			int num = 1;
			if (bladeRedis.exists(key)) {
				int data = bladeRedis.get(key);
				if (data > COUNT) {
					smsLogs.setCode("Error");
					smsLogs.setTemplateId(sms.getSms().getTemplateId());
					smsLogs.setCreateAt(new Date());
					smsLogs.setMessage("发送失败，超出单天发送最大数限制。");
					smsLogs.setName(p.getName());
					smsLogs.setPhone(p.getPhone());
					smsLogsService.save(smsLogs);
					continue;
				}
				num += data;
			}
			map.put("name", p.getName());
			try {
				CommonResponse res = SmsUtil.sendMessage(sms.getSms(), JSON.toJSONString(map), p.getPhone());
				JSONObject json = JSON.parseObject(res.getData());
				smsLogs.setCode(json.getString("Code"));
				smsLogs.setTemplateId(sms.getSms().getTemplateId());
				smsLogs.setBizId(json.getString("BizId"));
				smsLogs.setCreateAt(new Date());
				smsLogs.setMessage(json.getString("Message"));
				smsLogs.setRequestId(json.getString("RequestId"));
				smsLogs.setName(p.getName());
				smsLogs.setPhone(p.getPhone());
				bladeRedis.set(key,num);
				bladeRedis.expire(key,time);
				smsLogsService.save(smsLogs);
			} catch (ClientException e) {
				smsLogs.setCode("Error");
				smsLogs.setTemplateId(sms.getSms().getTemplateId());
				smsLogs.setCreateAt(new Date());
				smsLogs.setMessage("发送失败，发送异常。");
				smsLogs.setName(p.getName());
				smsLogs.setPhone(p.getPhone());
				smsLogs.setErrorInfo(e.getMessage());
				smsLogsService.save(smsLogs);
			}
		}
		return R.status(true);
	}

}
