package com.study.shardingsphereboot.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

/**
 * standard标准分片策略（表）-精准查询算法
 *
 * @author luohx
 * @version 1.0.0
 * @date: 2023/10/5 下午10:20
 * @menu
 */
public class MyPreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        //实现按照 = 或 in进行精确分片
        //例如 select * from course where cid = 1 or cid in (1,3,5)
        //实现course_$->{cid%2+1} 分表策略
        BigInteger shardingValueB = BigInteger.valueOf(shardingValue.getValue());
        BigInteger resB = (shardingValueB.mod(new BigInteger("2"))).add(new BigInteger("1"));
        String key = shardingValue.getLogicTableName() + "_" + resB;
        if (availableTargetNames.contains(key)) {
            return key;
        }
        throw new UnsupportedOperationException(" route " + key + " is not supported. please check your config");
    }
}
