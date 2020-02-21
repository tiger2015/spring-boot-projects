package com.tiger.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.tiger.entity.SysPersmission;

import java.util.List;

/**
 * @ClassName SysPermissionMapper
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/18 22:20
 * @Version 1.0
 **/
@Mapper
public interface SysPermissionMapper {

    @Select("select * from sys_permission where role_id = #{roleId}")
    List<SysPersmission> listByRoleId(Integer roleId);
}
