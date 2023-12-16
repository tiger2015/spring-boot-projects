package com.tiger.qr.service;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zenghu
 * @Date 2022年12月29日 21:41
 * @Description
 * @Version: 1.0
 **/
@Service
@Slf4j
public class AsyncService {

    @Async(value = "asyncTaskExecutor")
    public CompletableFuture<String> asyncHello(String name) {
        log.info("start task");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end task");
        return CompletableFuture.completedFuture("hello," + name);
    }


    @Async(value = "asyncTaskExecutor")
    public Future<String> asyncHelloWithAsyncResult(String name) {
        log.info("start task");
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end task");
        return new AsyncResult<>("hello," + name);
    }


    @Async("asyncTaskExecutor")
    public Future<Void> writeResponse(HttpServletResponse response) {

        log.info("start download");
        try {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Content-Type", "image/jpeg");
            String content = "http://192.168.100.6/";
            String uuid = UUID.randomUUID().toString();
            response.setHeader("uuid", uuid);
            response.addHeader("Access-Control-Expose-Headers", "uuid");
            QrCodeUtil.generate(content, 300, 300, ImgUtil.IMAGE_TYPE_JPG, response.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        log.info("finish download");
        return CompletableFuture.completedFuture(null);
    }

    @Async("asyncTaskExecutor")
    public void helloWithDeferredResult(DeferredResult<String> deferredResult, String name) {
        log.info("start task");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deferredResult.setResult("hello, " + name);
        log.info("end task");
    }


    @Async("asyncTaskExecutor")
    public String hello(String name){
        log.info("start task");
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end task");
        return "hello, " + name;
    }

}
