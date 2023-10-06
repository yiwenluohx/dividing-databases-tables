package com.study.shardingsphereboot.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * standard标准分片策略（库）-范围查询算法
 * @author luohx
 * @version 1.0.0
 * @date: 2023/10/5 下午10:21
 * @menu
 */
public class MyRangeDSShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    /**
     * @param availableTargetNames
     * @param shardingValue
     * @return {@link Collection}<{@link String}>
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        //实现按照between进行范围分片
        //例如 select * from course where cid between 2000 and 3000;
        //2000
        Long lowerEndpoint = shardingValue.getValueRange().lowerEndpoint();
        //3000
        Long upperEndpoint = shardingValue.getValueRange().upperEndpoint();
        //对于奇偶分离的场景，大部分范围查询都是要两张表都查
        return availableTargetNames;
    }
}
