package com.wxss.springbootshiro.mapper;

import com.wxss.springbootshiro.domain.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("permId") Long permId);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}