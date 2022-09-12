package com.tiger.spring.load;

import com.tiger.spring.load.App1.SpringApp;
import com.tiger.spring.load.App2.OtherSpringApp;

import java.util.concurrent.TimeUnit;

/**
 * @Author Zenghu
 * @Date 2022年06月11日 21:55
 * @Description
 * @Version: 1.0
 **/
public class LoadApp {
    public static void main(String[] args) {

        SpringApp.run(args);

        OtherSpringApp.run(args);

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
