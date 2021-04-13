package org.springblade.modules.group.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springblade.modules.group.dto.GroupDto;
import org.springblade.modules.group.entity.Group;
import org.springblade.modules.group.mapper.CompanyMapper;
import org.springblade.modules.group.mapper.GroupMapper;
import org.springblade.modules.group.utils.LevelUtil;
import org.springframework.stereotype.Service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * @author yd
 * 计算层级树
 * 包含了各种模块的层级管理
 */
@Service
public class GroupTreeService {

	@Resource
	private GroupMapper groupMapper;
	@Resource
	private CompanyMapper companyMapper;


    ///////////////////////////////////////////////////////////////////////////
    //树形层级计算模板
    //分组树形层级功能计算
    public List<GroupDto> groupTree() {
    	//获取所有分组的数据，形成集合，便于操作
        List<Group> groupList = groupMapper.getAllGroup();
        //使用工具，完成泛型简便操作，形成带类型的空集合，用于存储需要使用的数据
        List<GroupDto> dtoList = Lists.newArrayList();
        //遍历所有的集合列表
        for (Group gruop : groupList) {
        	//取出分组集合中的所有分组信息，封装进刚才准备好的dto集合，用于进行分组层级数据处理
			GroupDto dto = GroupDto.adapt(gruop);
            dtoList.add(dto);
        }
        //树形结构处理
        return groupListToTree(dtoList);
    }
    //分组树形递归处理，接口放入一个没有经过任何处理的dto集合
    public List<GroupDto> groupListToTree(List<GroupDto> groupLevelList) {
    	///////////////////////////////////////////////////////////////
    	//01完成基本数据结构初始化
    	//集合工具简易校验传入集合数据是否为空
        if (CollectionUtils.isEmpty(groupLevelList)) {
        	//如果为空，就重新返回一个空的集合
            return Lists.newArrayList();
        }
        //需求，以level为key，相同层级的分组放在一个集合中
        //google工具包中guava有一种特殊的数据结构Multimap<key,value>

        // level -> [dept1,dept2, ...]  Map<String, List<DeptLevelDto>>
        Multimap<String, GroupDto> levelGroupMap = ArrayListMultimap.create();
        //拿出一级分组，初始化空集，可以往里面放入集合了
        List<GroupDto> rootList = Lists.newArrayList();
        //遍历拿到的所有分组数据集合
        for (GroupDto dto : groupLevelList) {
        	//取出当前分组的层级，并将带有子分组数据的分组集合结构存储到map中
			levelGroupMap.put(dto.getLevel(), dto);
            //如果是定级分组，则将其放入rootlist集合中
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        ///////////////////////////////////////////////////////////////
        //按照seq从小到大排序
        //java自带工具包util中的比较功能
        Collections.sort(rootList, new Comparator<GroupDto>() {
            public int compare(GroupDto o1, GroupDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });
        //从一级分组开始，递归排序所有分组，把所有分组的子分组下属分组排好序
        // 递归生成树（顶级分组集合，层级为一，包含层级在内的所有dto集合）
        transformDeptTree(rootList, LevelUtil.ROOT, levelGroupMap);
        return rootList;
    }
    // level:0, 0, all 0->0.1,0.2
    // level:0.1
    // level:0.2
    public void transformDeptTree(List<GroupDto> gruopLevelList, String level, Multimap<String, GroupDto> levelGroupMap) {
        for (int i = 0; i < gruopLevelList.size(); i++) {
            // 遍历该层的每个元素
			GroupDto groupLevelDto = gruopLevelList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, groupLevelDto.getId());
            // 处理下一层
            List<GroupDto> tempGroupList = (List<GroupDto>) levelGroupMap.get(nextLevel);
            // 如果还有下级分组就继续遍历分组，如果没有下级分组就查询出此分组下的单位集合
            if (CollectionUtils.isNotEmpty(tempGroupList)) {
                // 排序
                Collections.sort(tempGroupList, deptSeqComparator);
                // 设置下一层分组
				groupLevelDto.setGroupList(tempGroupList);
                // 进入到下一层处理
                transformDeptTree(tempGroupList, nextLevel, levelGroupMap);
            }

        }
    }
    //子分组排序
    public Comparator<GroupDto> deptSeqComparator = new Comparator<GroupDto>() {
        public int compare(GroupDto o1, GroupDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

}
