package com.wxss.springbootshiro.mapper;

import com.wxss.springbootshiro.domain.SysRole;
import com.wxss.springbootshiro.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {

    SysUser selectUser(@Param("loginName") String loginName, @Param("password") String password);

    SysUser selectUserByLoginName(String loginName);

    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

}