package com.chain.mybatis.day02;

import com.chain.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MyTest {

    private String userMapper = "com.chain.mybatis.day02.UserMapper";

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
    public void testAdd() {
        String addUser = userMapper + ".addUser";
        User user = new User("小红", 23);
        int num = session.insert(addUser, user);
        System.out.println(num);
    }

    @Test
    public void testDelete() {
        String deleteUser = userMapper + ".deleteUser";
        User user = new User(1);
        int num = session.delete(deleteUser, user);
        System.out.println(num);

        String deleteUserById = userMapper + ".deleteUserById";
        num = session.delete(deleteUserById, 2);
        System.out.println(num);
    }

    @Test
    public void testUpdate() {
        String getUserById = userMapper + ".getUserById";
        User user = session.selectOne(getUserById, 10);
        System.out.println(user);
        String updateUser = userMapper + ".updateUser";
        user.setName("ABC123");
        session.update(updateUser, user);
        System.out.println(user);
    }

    @Test
    public void testGetAll() {
        String getAllUsers = userMapper + ".getAllUsers";
        List<User> lst = session.selectList(getAllUsers);
        System.out.println(lst);
    }


}
