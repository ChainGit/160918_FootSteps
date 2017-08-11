package com.chain.mybatis.day07;

import com.chain.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyTest {

    private String userMapper = "com.chain.mybatis.day07.UserMapper";

    private SqlSession session;

    @Before
    public void init() {
        session = MyBatisUtils.openSession();
    }

    @After
    public void destory() {
        session.commit();
        session.close();
    }

    /**
     * 一级缓存：
     * 1、关闭Session，重新开启新的Session
     * 2、使用session.clearCache();
     * 3、进行了CUD的操作
     */
    @Test
    public void testFirstLevelCache() {
        String getUserById = userMapper + ".getUserById";
        User user = session.selectOne(getUserById, 1);
        System.out.println(user);

        /*
        session.close();
        session = MyBatisUtils.openSession();
        */

        /*
        session.clearCache();
        */

        /*
        String updateUser = userMapper + ".updateUser";
        user.setAge(30);
        session.update(updateUser, user);
        */

        user = session.selectOne(getUserById, 1);
        System.out.println(user);
    }

    /**
     * 1、实体类需要是可序列化的
     * 2、配置文件中加入缓存标签
     */
    @Test
    public void testSecondLevelCache() {
        String getUserById = userMapper + ".getUserById";
        User user = session.selectOne(getUserById, 1);
        System.out.println(user);

        session.close();
        session = MyBatisUtils.openSession();

        user = session.selectOne(getUserById, 1);
        System.out.println(user);
    }
}
