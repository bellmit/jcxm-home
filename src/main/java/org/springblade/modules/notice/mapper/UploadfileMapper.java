package org.springblade.modules.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.notice.entity.Uploadfile;


import java.util.List;

public interface UploadfileMapper extends BaseMapper<Uploadfile> {

	/**
	 * 根据id查询
	 * @param ids
	 * @return uploadfile
	 */
	List<Uploadfile> findByIds(@Param("ids") List<String> ids);

}
