package com.study.shardingsphereboot;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.shardingsphereboot.dao.CourseMapper;
import com.study.shardingsphereboot.model.Course;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingSphereBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShardingSphereBootApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void contextLoads() {
        System.out.println("1121");
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

}
