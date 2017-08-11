package com.chain.sm.test;

import com.chain.sm.entities.User;
import com.chain.sm.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    private ApplicationContext ctx;

    {
        ctx = new ClassPathXmlApplicationContext("res/spring-applicationContext.xml");
    }

    private UserMapper userMapper;

    @Before
    public void init() {
        userMapper = ctx.getBean(UserMapper.class);
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setName("小张");
        user.setAge(22);
        userMapper.add(user);
    }

    @Test
    public void testUpdate() {
        User user = userMapper.getById(1);
        user.setName("小小明");
        userMapper.update(user);
    }

    @Test
    public void testDelete() {
        userMapper.deleteById(1);
    }

    @Test
    public void testGetAll() {
        List<User> lst = userMapper.getAll();
        System.out.println(lst);
    }
}
