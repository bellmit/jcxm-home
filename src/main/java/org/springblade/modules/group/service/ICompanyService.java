package org.springblade.modules.group.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.group.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.group.entity.Group;
import org.springblade.modules.group.vo.CompanyVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
public interface ICompanyService extends IService<Company> {
	/**
	 * 根据分组获取分组下的单位
	 * @param group
	 * @return
	 */
	public List<Company> getListTbCompanyForgroup(Group group);

	/**
	 * 在分组下添加单位
	 * @param company
	 * @param groupId
	 * @return
	 */
	public boolean saveCompany(Company company, Integer groupId);

	/**
	 * 从分组下删除单位
	 * @param cid
	 * @param gid
	 * @return
	 */
	public boolean deleteCompany(Integer cid,Integer gid);

	/**
	 * 修改单位内容
	 * @param company
	 * @return
	 */
	public boolean updateCompany(Company company);

	/**
	 * 获取分组下单位的详细信息
	 * @param group
	 * @return
	 */
	public List<CompanyVo> getListTbCompanyVoForgroup(Group group);

	/**
	 * 分组下的单位分页
	 * @param page
	 * @param group
	 * @return
	 */
	public IPage<Company> selectCompanyPage(IPage<Company> page, Group group);

	/**
	 * 逻辑删除单位
	 * @param company
	 * @return
	 */
	public boolean delCompany(Company company);

	/**
	 * 获取分组下的单位信息
	 * @param group
	 * @return
	 */
	public List<Company> getListTbCompanyForgroupbyname(Group group);
}
