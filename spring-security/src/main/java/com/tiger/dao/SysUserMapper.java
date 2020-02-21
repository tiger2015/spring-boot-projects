package com.tiger.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.tiger.entity.SysUser;

/**
 * @ClassName SysUserMapper
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/14 20:59
 * @Version 1.0
 **/
@Mapper
public interface SysUserMapper {

    @Select("select * from sys_user where id = #{id}")
    SysUser selectById(Integer id);

    @Select("select * from sys_user where name = #{name}")
    SysUser selectByName(String name);
}
