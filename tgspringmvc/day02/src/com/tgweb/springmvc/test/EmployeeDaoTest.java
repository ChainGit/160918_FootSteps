package com.tgweb.springmvc.test;

import com.tgweb.springmvc.dao.EmployeeDao;
import com.tgweb.springmvc.dao.impl.EmployeeDaoImpl;
import com.tgweb.springmvc.entities.Employee;
import org.junit.Test;

public class EmployeeDaoTest {

    @Test
    public void test() {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        System.out.println(employeeDao.getAll());
        System.out.println(employeeDao.getById(901));
        System.out.println(employeeDao.add(new Employee(906, "小赵", null)));
        System.out.println(employeeDao.deleteById(902));
        Employee e = employeeDao.getById(903);
        e.setName("小小芳");
        System.out.println(employeeDao.update(e));
        System.out.println(employeeDao.getAll());
    }
}
