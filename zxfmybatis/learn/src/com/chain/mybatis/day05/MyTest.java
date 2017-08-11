package com.chain.mybatis.day05;

import com.chain.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyTest {

    private String shcoolMapper = "com.chain.mybatis.day05.SchoolMapper";

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
    public void testGetClass() {
        String getClassById = shcoolMapper + ".getClassById";
        Class class1 = session.selectOne(getClassById, 1);
        Teacher teacher1 = class1.getTeacher();
        System.out.println(class1);
        System.out.println(teacher1);
    }

    @Test
    public void testGetClass2() {
        String getClassById2 = shcoolMapper + ".getClassById2";
        Class class1 = session.selectOne(getClassById2, 1);
        Teacher teacher1 = class1.getTeacher();
        System.out.println(class1);
        System.out.println(teacher1);
    }

    @Test
    public void testGetClass3() {
        String getClassById3 = shcoolMapper + ".getClassById3";
        Class class1 = session.selectOne(getClassById3, 1);
        System.out.println(class1);
        System.out.println(class1.getTeacher());
        System.out.println(class1.getClass());
        System.out.println(class1.getStudents());
    }

    @Test
    public void testGetClass4() {
        String getClassById4 = shcoolMapper + ".getClassById4";
        Class class1 = session.selectOne(getClassById4, 1);
        System.out.println(class1);
        System.out.println(class1.getTeacher());
        System.out.println(class1.getClass());
        System.out.println(class1.getStudents());
    }


}
