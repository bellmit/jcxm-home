package org.springblade.modules.group.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.group.entity.Uploadimg;

import java.util.List;

public interface IUploadimgService extends IService<Uploadimg> {


	/**
	 * 根据id查询
	 * @param ids
	 * @return
	 */
	List<Uploadimg> findByIds(List<String> ids);

	/**
	 * 根据clogid查询
	 * @param clogid
	 * @return
	 */
	public Uploadimg findByclogid(Integer clogid);

	/**
	 * 根据gbgid查询
	 * @param gbgid
	 * @return
	 */
	public Uploadimg findBygbgid( Integer gbgid);

	/**
	 * 根据gimgid查询
	 * @param gimgid
	 * @return
	 */
	public Uploadimg findBygimgid(Integer gimgid);

	/**
	 * 根据cimgid查询
	 * @param cimgid
	 * @return
	 */
	public Uploadimg findBycimgid(Integer cimgid);

	/**
	 * 根据abgid查询
	 * @param abgid
	 * @return
	 */
	Uploadimg findByabgid(Integer abgid);

	/**
	 * 根据aimgid查询
	 * @param aimgid
	 * @return
	 */
	Uploadimg findByaimgid(Integer aimgid);

	/**
	 * 根据eimgid查询
	 * @param eimgid
	 * @return
	 */
	Uploadimg findByeimgid(Integer eimgid);
}
