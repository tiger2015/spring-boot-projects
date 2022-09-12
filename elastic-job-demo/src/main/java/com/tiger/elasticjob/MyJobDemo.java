package com.tiger.elasticjob;

import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * @Author Zenghu
 * @Date 2022年07月05日 22:46
 * @Description
 * @Version: 1.0
 **/
public class MyJobDemo {

    public static void main(String[] args) {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration("192.168.100.6:2181", "elastic-job");
        zkConfig.setMaxRetries(5);
        zkConfig.setBaseSleepTimeMilliseconds(3000);
        zkConfig.setMaxSleepTimeMilliseconds(30000);
        zkConfig.setConnectionTimeoutMilliseconds(10 * 1000);
        zkConfig.setSessionTimeoutMilliseconds(30 * 1000);
        CoordinatorRegistryCenter coordinator = new ZookeeperRegistryCenter(zkConfig);
        coordinator.init();



       //
        



        JobConfiguration jobConfig = JobConfiguration.newBuilder("demo-flow", 2)
                //.cron("0/1 * * * * ?")
                .failover(true)
                .monitorExecution(true)
                .build();



        ScheduleJobBootstrap scheduleJobBootstrap = new ScheduleJobBootstrap(coordinator, new MyFlowJob(), jobConfig);
        // scheduleJobBootstrap.shutdown();

        scheduleJobBootstrap.schedule();


        //JobScheduleController scheduleController = JobRegistry.getInstance().getJobScheduleController("demo");

        //scheduleController.pauseJob();
       // scheduleController.shutdown();
        //coordinator.remove("/demo");
    }
}
