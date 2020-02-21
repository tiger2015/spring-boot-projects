package com.tiger.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.tiger.entity.SysUserRole;

import java.util.List;

/**
 * @ClassName SysUserRoleMapper
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/14 21:02
 * @Version 1.0
 **/
@Mapper
public interface SysUserRoleMapper {

    @Select("select * from sys_user_role where user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);
}
