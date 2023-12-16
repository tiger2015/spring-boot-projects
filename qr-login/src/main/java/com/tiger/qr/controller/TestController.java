package com.tiger.qr.controller;

import com.tiger.qr.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author Zenghu
 * @Date 2022年12月20日 22:41
 * @Description
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/test")
@Slf4j
@CacheConfig(cacheNames = "testController")
public class TestController {

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    @ResponseBody
   // @Cacheable(key = "#name")
    public String hello(@PathVariable String name) throws ExecutionException, InterruptedException {
        // 不是异步请求
        // 在get过程中会阻塞
        /**
        log.info("call start");
        CompletableFuture<String> future = asyncService.asyncHello(name);
        log.info("call end");
        return future.get();
         **/
        log.info("start call");
        Future<String> stringFuture = asyncService.asyncHelloWithAsyncResult(name);
        log.info("end call");
        return stringFuture.get();
    }

    @RequestMapping(value = "/asyncHello/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Future<String> asyncHello(@PathVariable String name){
        log.info("call start");
        CompletableFuture<String> future = asyncService.asyncHello(name);
        log.info("call end");
        return future;
    }






    @GetMapping(value = "/clear")
    @ResponseBody
    public void clearCache(){
        log.info("clear cache");
        cacheManager.getCacheNames().forEach(name-> Objects.requireNonNull(cacheManager.getCache(name)).clear());
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response) throws Exception {
        asyncService.writeResponse(response).get();
    }

    @GetMapping(value = "/download/callable")
    public Callable<Void> downloadWithCallable(HttpServletResponse response){
        return () -> {
            log.info("start write");
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("text/plain");
            outputStream.write("hello".getBytes());
            outputStream.flush();
            return null;
        };
    }

}
