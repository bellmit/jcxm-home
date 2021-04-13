package org.springblade.modules.notice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.notice.entity.Uploadfile;
import org.springblade.modules.notice.mapper.UploadfileMapper;
import org.springblade.modules.notice.service.IUploadfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadfileServiceImpl extends ServiceImpl<UploadfileMapper, Uploadfile> implements IUploadfileService {


	@Override
	public List<Uploadfile> findByIds(List<String> ids) {
		return baseMapper.findByIds(ids);
	}
}
