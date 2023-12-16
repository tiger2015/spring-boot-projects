package com.tiger.qr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author Zenghu
 * @Date 2022年12月31日 17:56
 * @Description
 * @Version: 1.0
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private AtomicLong threadIndex = new AtomicLong(0);

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        /**
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 120, TimeUnit.SECONDS, new LinkedBlockingQueue<>(8));
        threadPoolExecutor.setThreadFactory(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread();
                thread.setName("async-thread-" + threadIndex.getAndIncrement());
                return thread;
            }
        });

        AsyncTaskExecutor executor = new ConcurrentTaskExecutor(threadPoolExecutor);
        configurer.setTaskExecutor(executor);

         **/

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(4);
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setThreadNamePrefix("async-thread-");
        threadPoolTaskExecutor.initialize();

        configurer.setTaskExecutor(threadPoolTaskExecutor);
    }
}
