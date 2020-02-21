package com.tiger.consumer.service;

import com.tiger.consumer.entity.LoginLog;
import com.tiger.consumer.entity.PageResult;

import java.util.List;

public interface LoginLogService {
    boolean addLoginLog(LoginLog loginLog);

    List<LoginLog> listLoginLog(String startDate, String endDate);

    PageResult<LoginLog> listLoginLog(int pageNumber);

    PageResult<LoginLog> listLoginLog(String userName, int pageNumber);

    int removeLoginLog(List<Long> ids);
}
