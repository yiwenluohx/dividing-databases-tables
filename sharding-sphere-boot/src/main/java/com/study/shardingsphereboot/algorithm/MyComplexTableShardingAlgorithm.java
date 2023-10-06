package com.study.shardingsphereboot.algorithm;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * @author luohx
 * @version 1.0.0
 * @date: 2023/10/6 上午11:42
 * @menu
 */
public class MyComplexTableShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    /**
     * @param availableTargetNames
     * @param shardingValue
     * @return {@link Collection}<{@link String}>
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        //实现按照 between进行范围分片
        //例如 select * from course where cid in (1,2,3) and userId between 200 and 300;
        Collection<Long> cidCol = shardingValue.getColumnNameAndShardingValuesMap().get("cid");
        Range<Long> userRange = shardingValue.getColumnNameAndRangeValuesMap().get("user_id");
        List<String> result = Lists.newArrayList();
        Long uageLower = userRange.lowerEndpoint();
        Long uageUpper = userRange.upperEndpoint();

        //实现自定义分片逻辑，实现 course_$->{cid%2+1 + (30-20)+1} 这样的复杂分片逻辑
        if(uageLower > uageUpper){
            System.out.println("uageUpper is less then uageLower");
            throw new UnsupportedOperationException(" error param of user range");
        }

        if(uageUpper <= 1000 || uageLower >= 2000){
            System.out.println("error param of user range");
            return result;
        }

        cidCol.forEach(k -> {
            BigInteger cidI = BigInteger.valueOf(k);
            BigInteger target = (cidI.mod(BigInteger.valueOf(2L))).add(new BigInteger("1"));
            result.add("course_"+ target);
        });

        return result;
    }
}
