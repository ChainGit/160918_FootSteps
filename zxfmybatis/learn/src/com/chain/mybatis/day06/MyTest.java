package com.chain.mybatis.day06;

import com.chain.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {

    private String userMapper = "com.chain.mybatis.day06.UserMapper";

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

    @Test
    public void testGetUsers() {
        UserFilter filter = new UserFilter(null, 20, 22);
//        UserFilter filter = new UserFilter("%o%", 20, 22);
        String getUsersByFiler = userMapper + ".getUsersByFilter";
        List<User> lst = session.selectList(getUsersByFiler, filter);
        System.out.println(lst);
    }

    @Test
    public void testCountUser() {
        String countUsersByType = userMapper + ".countUsersByType";
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("s_type", 1);
//        paramMap.put("s_type", 0);
        paramMap.put("s_amount", -1);
        session.selectOne(countUsersByType, paramMap);
        System.out.println("amount: " + paramMap.get("s_amount"));
    }
}
