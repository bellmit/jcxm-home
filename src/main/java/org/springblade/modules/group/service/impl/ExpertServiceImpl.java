package org.springblade.modules.group.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Expert;
import org.springblade.modules.group.mapper.ExpertMapper;
import org.springblade.modules.group.mapper.UploadimgMapper;
import org.springblade.modules.group.service.ExpertService;
import org.springblade.modules.group.vo.ExpertVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
@Service
public class ExpertServiceImpl extends ServiceImpl<ExpertMapper, Expert> implements ExpertService {
	@Autowired
	private ExpertMapper expertMapper;
	@Autowired
	private UploadimgMapper uploadimgMapper;

	@Override
	public List<ExpertVO> getAllExpert(Integer cid) {
		List<Expert> expertList = expertMapper.selectAll(cid);
		List<ExpertVO> expertVOList=new ArrayList<>();
		for (Expert expert:expertList){
			ExpertVO expertVO=ExpertVO.exp(expert);
			if (uploadimgMapper.findByeimgid(expert.getId())!=null){
				//拿到最新的专家图片
				expertVO.setEimgurl(uploadimgMapper.findByeimgid(expert.getId()).getUrl());
			}
			expertVOList.add(expertVO);
		}
		//返回volist
		return expertVOList;
	}

	@Override
	public ExpertVO getDetailsById(Integer id) {
		//拿到专家信息
		Expert expert = expertMapper.selectById(id);
		//专家VO
		ExpertVO expertVO=ExpertVO.exp(expert);
		if (uploadimgMapper.findByeimgid(expert.getId())!=null){
			//拿到最新的专家图片
			expertVO.setEimgurl(uploadimgMapper.findByeimgid(expert.getId()).getUrl());
		}
		return expertVO;
	}

	@Override
	public boolean update(Expert expert) {
		return expertMapper.update(expert);
	}

	@Override
	public boolean insertExpert(Expert expert) {
		return expertMapper.insertExpert(expert);
	}

	@Override
	public boolean deleteById(Integer id) {
		return expertMapper.deleteById(id);
	}

}
