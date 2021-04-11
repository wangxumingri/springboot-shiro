package com.wxss.springbootshiro.mapper;

import com.wxss.springbootshiro.domain.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper {
    int deleteByPrimaryKey(Long permId);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long permId);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> getPermissionByRoleIds(List<Long> roleIds);
}