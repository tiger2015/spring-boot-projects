package com.tiger.elasticjob;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * @Author Zenghu
 * @Date 2022年07月05日 22:44
 * @Description
 * @Version: 1.0
 **/
@Slf4j
public class MyJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("shard:{}", shardingContext.getShardingItem());
        log.info("execute task");
    }
}
