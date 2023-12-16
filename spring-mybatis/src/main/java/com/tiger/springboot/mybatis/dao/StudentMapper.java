package com.tiger.springboot.mybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiger.springboot.mybatis.model.po.StudentPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ZengHu
 * @Date: 2020/11/29 22:25
 * @Description:
 * @Version: 1.0
 **/
@Mapper
public interface StudentMapper extends BaseMapper<StudentPo> {

}
