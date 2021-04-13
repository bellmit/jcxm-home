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
package org.springblade.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.sms.entity.Sms;
import org.springblade.modules.sms.mapper.SmsMapper;
import org.springblade.modules.sms.service.ISmsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 短信配置表 服务实现类
 *
 * @author BladeX
 * @since 2020-02-20
 */
@Service
public class SmsServiceImpl extends ServiceImpl<SmsMapper, Sms> implements ISmsService {

	@Override
	public IPage<Sms> selectSmsPage(IPage<Sms> page, Sms sms) {
		return page.setRecords(baseMapper.selectSmsPage(page, sms));
	}

	@Override
	public List<Sms> findAll() {
		return baseMapper.findAll();
	}

	@Override
	public boolean submit(Sms sms) {
		LambdaQueryWrapper<Sms> lqw = Wrappers.<Sms>query().lambda()
			.eq(Sms::getTemplateId, sms.getTemplateId());
		Integer cnt = baseMapper.selectCount(Func.isEmpty(sms.getId()) ? lqw : lqw.notIn(Sms::getId, sms.getId()));
		if (cnt > 0) {
			throw new ServiceException("当前资源编号已存在!");
		}
		return this.saveOrUpdate(sms);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean enable(String id) {
		Sms sms = this.getById(id);
		boolean flag = false;
		if(sms!=null){
			if(sms.getStatus() == 1){
				// 在启用
				flag = this.update(Wrappers.<Sms>update().lambda().set(Sms::getStatus, 2).eq(Sms::getId, id));
			}else {
				flag = this.update(Wrappers.<Sms>update().lambda().set(Sms::getStatus, 1).eq(Sms::getId, id));
			}
		}
		return flag;
	}

	@Override
	public boolean remove(List<String> ids) {
		List<Sms> list = new ArrayList<>();
		ids.forEach(id ->{
			Sms entity = BeanUtil.newInstance(currentModelClass());
			entity.setUpdateAt(DateUtil.now());
			entity.setId(id);
			entity.setIsDeleted(1);
			list.add(entity);
		});
		return super.updateBatchById(list);
	}

}
