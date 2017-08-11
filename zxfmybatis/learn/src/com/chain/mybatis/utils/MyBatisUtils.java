package com.chain.mybatis.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtils {

    private static SqlSessionFactory factory = null;

    static {
        String conf = "mybatis.xml";
        InputStream inputStream = MyBatisUtils.class.getClassLoader().getResourceAsStream(conf);
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession openSession() {
        return factory.openSession();
    }

    public static SqlSession openSession(boolean autoCommit) {
        return factory.openSession(autoCommit);
    }
}
