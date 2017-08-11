package com.chain.mybatis.day04;

import com.chain.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyTest {

    private String orderMapper = "com.chain.mybatis.day04.OrderMapper";

    private SqlSession session;

    @Before
    public void before() {
        session = MyBatisUtils.openSession();
    }

    @After
    public void after() {
        session.commit();
        session.close();
    }

    @Test
    public void testGetOrder() {
        String getOrderById = orderMapper + ".getOrderById";
        Order order = session.selectOne(getOrderById, 1);
        System.out.println(order);
    }

    @Test
    public void testGetOrder2() {
        String getOrderById2 = orderMapper + ".getOrderById2";
        Order order = session.selectOne(getOrderById2, 1);
        System.out.println(order);
    }
}
