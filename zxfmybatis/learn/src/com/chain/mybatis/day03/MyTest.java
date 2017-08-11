package com.chain.mybatis.day03;

import com.chain.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MyTest {

    private SqlSession session;

    private UserMapper userMapper;

    @Before
    public void before() {
        session = MyBatisUtils.openSession();
        userMapper = session.getMapper(UserMapper.class);
    }

    @After
    public void after() {
        session.commit();
        session.close();
    }

    @Test
    public void testGet() {
        User user = userMapper.getById(1);
        System.out.println(user);
    }

    @Test
    public void testGetAll() {
        List<User> lst = userMapper.getAll();
        System.out.println(lst);
    }

    @Test
    public void testAdd() {
        User user = new User("小红", 22);
        int num = userMapper.add(user);
        System.out.println(num);
    }

    @Test
    public void testDelete() {
        User user = new User(1);
        int num = userMapper.delete(user);
        System.out.println(num);
    }

    @Test
    public void testUpdate() {
        User user = userMapper.getById(2);
        System.out.println(user);
        user.setName("小小刚");
        userMapper.update(user);
        System.out.println(user);
    }


}
