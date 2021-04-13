package org.springblade.modules.group.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.group.entity.Uploadimg;

import java.util.List;

public interface UploadimgMapper extends BaseMapper<Uploadimg> {

	/**
	 * 根据id查询
	 * @param ids
	 * @return uploadfile
	 */
	List<Uploadimg> findByIds(@Param("ids") List<String> ids);

	/**
	 * 根据clogid查询
	 * @param clogid
	 * @return
	 */
	Uploadimg findByclogid(@Param("clogid") Integer clogid);

	/**
	 * 根据gbgid查询
	 * @param gbgid
	 * @return
	 */
	Uploadimg findBygbgid(@Param("gbgid") Integer gbgid);

	/**
	 * 根据gimgid查询
	 * @param gimgid
	 * @return
	 */
	Uploadimg findBygimgid(@Param("gimgid") Integer gimgid);

	/**
	 * 根据gimgid查询
	 * @param cimgid
	 * @return
	 */
	Uploadimg findBycimgid(@Param("cimgid") Integer cimgid);
}
