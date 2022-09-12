package com.tiger.elasticjob;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zenghu
 * @Date 2022年07月06日 22:53
 * @Description
 * @Version: 1.0
 **/
@Slf4j
public class MyFlowJob implements DataflowJob<String> {
    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
        log.info("shard:{} fetch data", shardingContext.getShardingItem());
        List<String> list = new ArrayList<>();
        list.add("aaaaa");
        list.add("bbbbb");
        return list;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> data) {
        log.info("shard:{} process data", shardingContext.getShardingItem());
    }
}
