package com.study.shardingsphereboot.geneDemo;

/**
 * 基因分片实例（通过二进制基因，关联到对应分片）
 *
 * @author luohx
 * @version 1.0.0
 * @date: 2023/10/6 下午9:54
 * @menu
 */
public class geneDemo {

    public static final int datasize = 3;

    public static void main(String[] args) {
        //原始预备生成的用户名
        String userName = "testroy";
        System.out.println("原始预备插入的用户名：" + userName);
        //原始预备生成的唯一id
        long originId = 12394846L;
        System.out.println("原始预备插入的用户id：" + originId);
        //掩码，二进制表述为全部是1
        int mask = (int) (Math.pow(2, datasize) - 1);
        //根据用户名查询时，获取到的分片结果
        System.out.println("根据用户名获取到的分片结果：" + (userName.hashCode() & mask));
        //给id添加用户名的基因片段后的新id  -- 将userName.hashCode()二进制的后三位替换成用户名的分片结果，这样无法保证ID的唯一性
        long newId = originId - (originId & mask) + (userName.hashCode() & mask);
        System.out.println("添加分片基因后的用户ID：" + newId);
        //新生成的id与用户名的分片结果保持一致，这样就能同时支持username和id都作为分片键
        System.out.println("根据用户id获取到的分片结果：" + (newId & mask));

        //给id添加用户名的基因片段后的新id  -- 将userName.hashCode()二进制左移三位，再添加用户名的分片结果，这样保证了原始id的唯一性
        long newId2 = (originId << datasize) + (userName.hashCode() & mask);
        System.out.println("添加分片基因后的用户ID2:"+ newId2);
        System.out.println("根据用户id2获取到的分片结果：" + (newId2 & mask));
    }

}
