package com.tiger.miniprogam.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Zenghu
 * @Date 2022年07月31日 12:07
 * @Description
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/consumption/records")
public class ConsumptionRecordsController {


    @RequestMapping(path = "/", method = {RequestMethod.GET})
    public String index(){
        return  "index";
    }







}
