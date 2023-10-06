package com.study.shardingsphereboot.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * standard标准分片策略（表）-范围查询算法
 *
 * @author luohx
 * @version 1.0.0
 * @date: 2023/10/5 下午10:20
 * @menu
 */
public class MyRangeTableShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        //实现按照between进行范围分片
        //例如 select * from course where cid between 2000 and 3000;
        //2000
        Long lowerEndpoint = shardingValue.getValueRange().lowerEndpoint();
        //3000
        Long upperEndpoint = shardingValue.getValueRange().upperEndpoint();
        //实现course_$->{(3000-2000)%2+1} 分片策略
//        return Arrays.asList(shardingValue.getLogicTableName() + "_" + ((upperEndpoint - lowerEndpoint))
                //对于奇偶分离的场景，大部分范围查询都是要两张表都查
        return Arrays.asList(shardingValue.getLogicTableName() + "_1", shardingValue.getLogicTableName() + "_2");
    }
}
