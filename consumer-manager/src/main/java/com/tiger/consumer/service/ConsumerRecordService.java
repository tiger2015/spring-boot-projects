package com.tiger.consumer.service;

import com.tiger.consumer.entity.ConsumerRecord;
import com.tiger.consumer.entity.PageResult;

import java.util.Date;
import java.util.List;

public interface ConsumerRecordService {

    boolean add(ConsumerRecord record);

    int delete(List<Long> ids);

    PageResult<ConsumerRecord> listAll(int pageNumber);

    PageResult<ConsumerRecord> listAllByUser(String user, int pageNumber);

    PageResult<ConsumerRecord> listAllByDate(Date start, Date end, int pageNumber);

    PageResult<ConsumerRecord> listAllByType(String type, int pageNumber);

    PageResult<ConsumerRecord> listAllByUserAndDate(String user, Date start, Date end, int pageNumber);

    PageResult<ConsumerRecord> listAllByUserAndType(String user, String type, int pageNumber);

}
