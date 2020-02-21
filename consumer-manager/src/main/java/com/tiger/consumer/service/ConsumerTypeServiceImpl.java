package com.tiger.consumer.service;

import com.tiger.consumer.dao.ConsumerTypeDao;
import com.tiger.consumer.entity.ConsumerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConsumerTypeServiceImpl implements ConsumerTypeService {
    @Autowired
    private ConsumerTypeDao consumerTypeDao;

    @Override
    public List<ConsumerType> listAll() {
        return consumerTypeDao.selectAllConsumerType();
    }
}
