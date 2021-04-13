package org.springblade.modules.group.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Expert;
import org.springblade.modules.group.mapper.ExpertMapper;
import org.springblade.modules.group.service.ExpertService;
import org.springblade.modules.group.vo.ExpertVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public List<ExpertVO> getAllExpert(Integer cid) {
		return expertMapper.selectAll(cid);
	}

	@Override
	public List<ExpertVO> getDetailsById(Integer id) {
		return expertMapper.selectById(id);
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
