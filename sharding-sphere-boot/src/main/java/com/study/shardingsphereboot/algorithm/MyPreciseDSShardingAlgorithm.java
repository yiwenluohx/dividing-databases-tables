package com.study.shardingsphereboot.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

/**
 * standard标准分片策略（库）-精准查询算法
 * 泛型Long：分片键cid的类型是
 *
 * @author luohx
 * @version 1.0.0
 * @date: 2023/10/5 下午10:21
 * @menu
 */
public class MyPreciseDSShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     * @param availableTargetNames 有效的数据源或表的名字，这里就对应配置文件中配置的数据源信息
     * @param shardingValue        包含逻辑表名、分片列和分片列的值
     * @return {@link String}
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        //实现按照 = 或 in进行精确分片
        //例如 select * from course where cid = 1 or cid in (1,3,5)
        //实现course_$->{cid%2+1} 分表策略
        BigInteger shardingValueB = BigInteger.valueOf(shardingValue.getValue());
        BigInteger resB = (shardingValueB.mod(new BigInteger("2"))).add(new BigInteger("1"));
        String key = "m" + resB;
        if(availableTargetNames.contains(key)){
           return key;
        }
        throw new UnsupportedOperationException(" route "+ key +" is not supported. please check your config");
    }
}
