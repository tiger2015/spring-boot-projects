package com.tiger.qr.controller;

import com.tiger.qr.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zenghu
 * @Date 2023年01月01日 10:33
 * @Description
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "/asyncTest")
@Slf4j
public class AsyncController {

    @Autowired
    private AsyncService asyncService;


    //@GetMapping(value = "/hello/{name}")
    public void asyncHelloWithServlet(@PathVariable String name, HttpServletRequest request) {
        log.info("receive request");
        final AsyncContext asyncContext = request.startAsync();
        asyncContext.start(() -> {
            try {
                log.info("start task");
                TimeUnit.SECONDS.sleep(10);
                ServletResponse response = asyncContext.getResponse();
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json");
                response.getWriter().write("hello, " + name);
                response.getWriter().flush();
                log.info("end task");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                asyncContext.complete();
            }
        });
    }

    // @GetMapping(value = "/hello/{name}")
    public Callable<String> asyncHelloWithCallable(@PathVariable String name) {
        log.info("receive request");
        return () -> {
            log.info("start task");
            TimeUnit.SECONDS.sleep(10);
            log.info("end task");
            return "hello";
        };
    }


    public WebAsyncTask<String> helloWithWebAsyncTask(@PathVariable String name) {
        log.info("receive request");
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(-1, new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("start task");
                TimeUnit.SECONDS.sleep(10);
                log.info("end task");
                return "hello," + name;
            }
        });
        return webAsyncTask;
    }

    @GetMapping(value = "/hello/{name}")
    @ResponseBody
    public DeferredResult<String> helloWithDeferredResult(@PathVariable String name, HttpServletRequest request) {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        //deferredResult.setResultHandler(new ResultHandler());
        asyncService.helloWithDeferredResult(deferredResult, name);


        return deferredResult;
    }

    // @GetMapping(value = "/hello/{name}")
    @ResponseBody
    public Future<String> hello(@PathVariable String name) {
        log.info("receive request");
        Future<String> stringFuture = asyncService.asyncHello(name);
        log.info("finish request");
        return stringFuture;
    }

    private class ResultHandler implements DeferredResult.DeferredResultHandler {
        @Override
        public void handleResult(Object result) {
            log.info("result:{}", result);
        }
    }
}
