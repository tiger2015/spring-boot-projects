package com.tiger.test.spring.zookeeper;

import com.tiger.spring.zookeeper.CuratorApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zenghu
 * @Date 2022/1/15
 * @Description
 * @Version: 1.0
 **/
@SpringBootTest(classes = CuratorApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ZookeeperTest {

    @Autowired
    private CuratorFramework curatorFramework;


    @Test
    public void create() {
        try {
            String result = curatorFramework.create().forPath("/test", "test".getBytes(StandardCharsets.UTF_8));
            log.info("result:{}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void get() {
        try {
            byte[] forPath = curatorFramework.getData().forPath("/test");
            log.info("data:{}", new String(forPath, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void delete() {
        try {
            curatorFramework.delete().deletingChildrenIfNeeded().forPath("/test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void watch() throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, "/test", false);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData currentData = nodeCache.getCurrentData();
                log.info("data change:{}", new String(currentData.getData(), StandardCharsets.UTF_8));
            }
        });

        TimeUnit.SECONDS.sleep(120);
        nodeCache.close();
    }

    @Test
    public void watch2() throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/test2", true);

        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                PathChildrenCacheEvent.Type type = event.getType();
                switch (type) {
                    case CHILD_ADDED:
                        log.info("child add:{}, data:{}", event.getData().getPath(), new String(event.getData().getData(), StandardCharsets.UTF_8));
                        break;
                    case CHILD_REMOVED:
                        log.info("child removed:{}, data:{}", event.getData().getPath(), new String(event.getData().getData(), StandardCharsets.UTF_8));
                        break;
                    case CHILD_UPDATED:
                        log.info("child update:{}, data:{}", event.getData().getPath(), new String(event.getData().getData(), StandardCharsets.UTF_8));
                        break;
                    case CONNECTION_LOST:
                        log.info("connection loss");
                        break;
                    case CONNECTION_RECONNECTED:
                        log.info("reconnected");
                        break;
                }

            }
        });
        pathChildrenCache.start();

        TimeUnit.SECONDS.sleep(180);
    }


    @Test
    public void elections() {

        LeaderLatch leaderLatch = new LeaderLatch(curatorFramework, "/nodes");
        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                log.info("leader");
            }

            @Override
            public void notLeader() {
                log.info("not leader");
            }
        });
        try {
            leaderLatch.start();
            while (true) {
                try {
                    leaderLatch.await();
                    log.info("leader ");
                } catch (Exception e) {

                }
                TimeUnit.SECONDS.sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void distributeLock() {

        // 可重入
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/lock");
        try {
            mutex.acquire();
            mutex.acquire();
            log.info("acquire lock");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    @Test
    public void noreentrantLock() {

        InterProcessSemaphoreMutex mutex = new InterProcessSemaphoreMutex(curatorFramework, "/lock");

        try {
            mutex.acquire();
            log.info("acquire lock");

            mutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void semaphoreTest(){
        InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(curatorFramework,"/semaphore", 2);
        Lease acquire = null;
        try {
             acquire = semaphore.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            semaphore.returnLease(acquire);
        }
    }
}
