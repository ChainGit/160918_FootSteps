package com.tgweb.springmvc.test;

import com.tgweb.springmvc.dao.DepartmentDao;
import com.tgweb.springmvc.dao.impl.DepartmentDaoImpl;
import org.junit.Test;

public class DepartmentDaoTest {

    @Test
    public void test(){
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        System.out.println(departmentDao.getAll());
        System.out.println(departmentDao.getById(1001));
    }
}
