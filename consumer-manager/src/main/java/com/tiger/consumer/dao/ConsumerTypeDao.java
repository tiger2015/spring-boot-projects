package com.tiger.consumer.dao;

import com.tiger.consumer.entity.ConsumerType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ConsumerTypeDao {

    /**
     * 查询所有的消费类型
     * @return
     */
    List<ConsumerType> selectAllConsumerType();
}
