package org.springblade.modules.group.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Target;
import org.springblade.modules.group.mapper.TargetMapper;
import org.springblade.modules.group.service.TargetService;
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
public class TargetServiceImpl extends ServiceImpl<TargetMapper, Target> implements TargetService {
	@Autowired
	private TargetMapper targetMapper;

	@Override
	public List<Target> selectAll(Company company) {
		return targetMapper.selectAll(company);
	}

	@Override
	public Target selectById(Integer id) {
		return targetMapper.selectById(id);
	}

	@Override
	public boolean update(Target target) {
		return targetMapper.update(target);
	}

	@Override
	public boolean insertTarget(Target target) {
		return targetMapper.insertTarget(target);
	}

	@Override
	public boolean deleteById(Integer id) {
		return targetMapper.deleteById(id);
	}
}
