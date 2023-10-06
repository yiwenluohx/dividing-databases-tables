package com.study.shardingsphereboot.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author luohx
 * @version 1.0.0
 * @date: 2023/10/6 下午4:31
 * @menu
 */
public class MyHintDSShardingAlgorithm implements HintShardingAlgorithm<Integer> {

    /**
     * @param availableTargetNames 可选数据源和表名称
     * @param shardingValue
     * @return {@link Collection}<{@link String}>
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Integer> shardingValue) {
        //对sql的零侵入分片方案，shardingValue是通过HintManager
        //sql：select * from t_user where user_id in {1,2,3,4,5,...};按照in的第一个值，全部路由到course_1表中。
        //注意使用时有非常多的限制
//        String key = "m" + shardingValue.getValues().toArray()[0];
//        if (availableTargetNames.contains(key)) {
//           return Arrays.asList(key);
//        }
//        throw new UnsupportedOperationException(" route "+ key +" is not supported. please check your config");
        return Arrays.asList("m1", "m2");
    }
}
