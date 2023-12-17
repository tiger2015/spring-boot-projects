package com.tiger.seata.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tiger.seata.stock.model.Stock;

/**
 * @Author Zenghu
 * @Date 2023年12月17日 16:01
 * @Description
 * @Version: 1.0
 **/
public interface StockService extends IService<Stock> {
    boolean deduceStock(String commodityCode, Integer count);
}
