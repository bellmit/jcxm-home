package org.springblade.modules.group.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.group.entity.GroupCompany;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
public interface GroupCompanyMapper extends BaseMapper<GroupCompany> {
	int insertGroupCompany(GroupCompany groupCompany);
	int deleteGroupCompany(Integer cid,Integer gid);
}
