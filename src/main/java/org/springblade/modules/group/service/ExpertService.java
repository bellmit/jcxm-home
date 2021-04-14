package org.springblade.modules.group.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.entity.Expert;
import org.springblade.modules.group.vo.ExpertVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
public interface ExpertService extends IService<Expert> {
	/**
	 * 获取所有专家
	 * @param cid
	 * @return
	 */
	List<ExpertVO> getAllExpert(Integer cid);

	/**
	 * 专家详情
	 * @param id
	 * @return
	 */
	ExpertVO getDetailsById(Integer id);

	/**
	 * 更新专家信息
	 * @param expert
	 * @return
	 */
	boolean update(Expert expert);

	/**
	 * 新增专家
	 * @param expert
	 * @return
	 */
	boolean insertExpert(Expert expert);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean deleteById(Integer id);
}
