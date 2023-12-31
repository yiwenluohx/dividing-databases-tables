package com.study.shardingsphereboot;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.shardingsphereboot.dao.CourseMapper;
import com.study.shardingsphereboot.model.Course;
import org.apache.shardingsphere.api.hint.HintManager;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingSphereBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShardingSphereBootApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void contextLoads() {
        List<Long> cids = Lists.newArrayList(
                916371711841013761L,
                916371711882956801L,
                916371711916511233L,
                916371711945871361L,
                916371711979425793L,
                916371710750494720L,
                916371711866179584L,
                916371711903928320L,
                916371711933288448L,
                916371711962648576L
        );
        List<Long> collect = cids.stream().sorted().collect(Collectors.toList());
        collect.forEach(m -> System.out.println(m));
    }


    /**
     * 插入数据
     */
    @Test
    public void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course c = new Course();
            c.setCname("java");
            c.setUserId(1001L);
            c.setCstatus("1");
            courseMapper.insert(c);
            System.out.println(c);
        }
    }

    @Test
    public void queryCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>();
//        wrapper.eq("cid", 916371711882956801L);
        wrapper.in("cid", 916371711882956801L, 916371711866179584L, 12487L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(k -> System.out.println(JSON.toJSONString(k)));
    }

    /**
     * standard查询策略
     */
    @Test
    public void queryRange() {
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>();
        //范围查询
        wrapper.between("cid", 916371711882956801L, 916371711916511233L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(k -> System.out.println(JSON.toJSONString(k)));
    }

    /**
     * 复杂分片策略，所有查询条件只能有一个统一的类型
     */
    @Test
    public void queryCourdeComplex() {
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>();
        wrapper.orderByDesc("user_id");
        wrapper.in("cid", 916371711882956801L, 916371711866179584L, 12487L);
        wrapper.between("user_id", 3L, 8L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(k -> System.out.println(JSON.toJSONString(k)));
    }

    /**
     * 强制路由策略，脱离sql自己指定分片策略
     */
    @Test
    public void queryCourseByHint() {
        HintManager hintManager = HintManager.getInstance();
        //注意这两个分片，dataSourceBaseShardingValue用于强制分库
        //用于查m1数据源
        hintManager.addDatabaseShardingValue("course", "1");
        //强制查course_1表
        hintManager.addTableShardingValue("course", "1");
        List<Course> courses = courseMapper.selectList(null);
        courses.forEach(k -> System.out.println(JSON.toJSONString(k)));
        //线程安全，用完关闭
        hintManager.close();
    }
}
