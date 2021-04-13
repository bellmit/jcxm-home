package org.springblade.modules.group.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.group.entity.Company;
import org.springblade.modules.group.vo.CompanyVo;
import org.springblade.modules.notice.entity.Article;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
public interface CompanyMapper extends BaseMapper<Company> {
	List<Company> getListTbCompany(Integer groupId);
	List<Company> getListTbCompanyForlevel(String level);
	List<Company> getListTbCompanyForname(String gname);
	int insertCompany(Company company);
	Integer getCompanyId(String cname);
	int getCountbyId(Integer cid);
	int updateCompany (Company company);
	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param grouplevel
	 * @return
	 */
	List<Company> selectCompanyPage(IPage page, String grouplevel);
}
