package org.springblade.modules.group.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author parezhang
 * @since 2021-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_group_company")
public class GroupCompany {

    private static final long serialVersionUID = 1L;
	private Integer id;
    /**
     * 单位id
     */
    private Integer cid;

    /**
     * 分组id
     */
    private Integer gid;




}
