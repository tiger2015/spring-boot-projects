package com.tiger.springboot.mybatis.dao;

import com.tiger.springboot.mybatis.model.ConsumptionType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: ZengHu
 * @Date: 2020/11/29 21:06
 * @Description:
 * @Version: 1.0
 **/
//@Mapper
public interface ConsumptionDao {

    List<ConsumptionType> selectAll();

}
