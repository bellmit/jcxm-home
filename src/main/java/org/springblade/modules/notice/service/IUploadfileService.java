package org.springblade.modules.notice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.notice.entity.Uploadfile;

import java.util.List;

public interface IUploadfileService extends IService<Uploadfile> {


	/**
	 * 根据id查询
	 * @param ids
	 * @return
	 */
	List<Uploadfile> findByIds(List<String> ids);

}
