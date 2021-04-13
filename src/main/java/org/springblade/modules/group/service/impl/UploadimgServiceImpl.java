package org.springblade.modules.group.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.group.entity.Uploadimg;
import org.springblade.modules.group.mapper.UploadimgMapper;
import org.springblade.modules.group.service.IUploadimgService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadimgServiceImpl extends ServiceImpl<UploadimgMapper, Uploadimg> implements IUploadimgService {
	@Autowired
	UploadimgMapper uploadimgMapper;
	@Override
	public List<Uploadimg> findByIds(List<String> ids) {
		return baseMapper.findByIds(ids);
	}
	/**
	 * 根据clogid查询
	 * @param clogid
	 * @return
	 */
	 public Uploadimg findByclogid(Integer clogid){
		return uploadimgMapper.findByclogid(clogid);
	 };

	/**
	 * 根据gbgid查询
	 * @param gbgid
	 * @return
	 */
	public Uploadimg findBygbgid( Integer gbgid){
		return uploadimgMapper.findBygbgid(gbgid);
	};

	/**
	 * 根据gimgid查询
	 * @param gimgid
	 * @return
	 */
	public Uploadimg findBygimgid(Integer gimgid){
		return uploadimgMapper.findBygimgid(gimgid);
	};
	/**
	 * 根据cimgid查询
	 * @param cimgid
	 * @return
	 */
	public Uploadimg findBycimgid(Integer cimgid){
		return uploadimgMapper.findBycimgid(cimgid);
	}
}
