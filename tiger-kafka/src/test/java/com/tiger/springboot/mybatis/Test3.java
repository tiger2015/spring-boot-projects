package com.tiger.springboot.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.Utils;

import java.nio.charset.Charset;
import java.util.*;

/**
 * @Author Zenghu
 * @Date 2022年04月07日 20:31
 * @Description
 * @Version: 1.0
 **/
@Slf4j
public class Test3 {
    public static void main(String[] args) {
        Map<Integer, SortedSet<Integer>> counter = new HashMap<>();

        for (int i = 1; i < 3000; i++) {
            int partition = Utils.toPositive(Utils.murmur2((i + "").getBytes(Charset.defaultCharset()))) % 8;
            counter.putIfAbsent(partition, new TreeSet<>());
            counter.get(partition).add(i);
        }
        for (Integer key : counter.keySet()) {
            log.info("key:{} -> value:{}", key, counter.get(key).size());
        }


    }
}
