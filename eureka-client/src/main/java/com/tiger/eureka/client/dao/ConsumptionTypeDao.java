package com.tiger.eureka.client.dao;

import com.tiger.eureka.client.model.ConsumptionType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: ZengHu
 * @Date: 2020/12/1 22:36
 * @Description:
 * @Version: 1.0
 **/
@Mapper
public interface ConsumptionTypeDao {
    List<ConsumptionType> selectAll();

    ConsumptionType selectById(Integer id);

    int insert(ConsumptionType consumptionType);

    int delete(Integer id);

    int update(ConsumptionType consumptionType);
}
