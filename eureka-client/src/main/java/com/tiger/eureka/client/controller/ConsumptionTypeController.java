package com.tiger.eureka.client.controller;

import com.tiger.eureka.client.dao.ConsumptionTypeDao;
import com.tiger.eureka.client.model.ConsumptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ZengHu
 * @Date: 2020/12/1 22:47
 * @Description:
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/")
public class ConsumptionTypeController {

    @Autowired
    private ConsumptionTypeDao consumptionTypeDao;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Object getAll() {
        return consumptionTypeDao.selectAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable("id") Integer id) {
        return consumptionTypeDao.selectById(id);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Object add(@RequestBody ConsumptionType consumptionType) {
        return consumptionTypeDao.insert(consumptionType);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public Object remove(@PathVariable("id") Integer id) {
        return consumptionTypeDao.delete(id);
    }


    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public Object update(@RequestBody ConsumptionType consumptionType) {
        return consumptionTypeDao.update(consumptionType);
    }
}
