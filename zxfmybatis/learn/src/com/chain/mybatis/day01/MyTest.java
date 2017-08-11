package com.chain.mybatis.day01;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class MyTest {

    @Test
    public void test() {
        String conf = "mybatis.xml";
        InputStream inputStream = MyTest.class.getClassLoader().getResourceAsStream(conf);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        String getUserById = "com.chain.mybatis.day01.UserMapper" + ".getUserById";
        User user = session.selectOne(getUserById, 1);
        System.out.println(user);
    }
}
