package com.tiger.eureka.service.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: ZengHu
 * @Date: 2020/12/2 23:06
 * @Description:
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/consumptionType")
public class ConsumptionTypeController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Object getAll() {
        return restTemplate.getForObject("http://CONSUMPTIONTYPE-SERVICE/consumptionType", Object.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Integer id){
        return restTemplate.getForObject("http://CONSUMPTIONTYPE-SERVICE/consumptionType/"+id, Object.class);
    }


}
