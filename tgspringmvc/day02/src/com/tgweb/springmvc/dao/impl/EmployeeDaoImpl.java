package com.tgweb.springmvc.dao.impl;

import com.tgweb.springmvc.dao.DepartmentDao;
import com.tgweb.springmvc.dao.EmployeeDao;
import com.tgweb.springmvc.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

    public static List<Employee> employees;

    public static DepartmentDao departmentDao;

    static {
        departmentDao = new DepartmentDaoImpl();
        employees = new ArrayList<>();
        employees.add(new Employee(901, "小明", departmentDao.getById(1001)));
        employees.add(new Employee(902, "小红", departmentDao.getById(1004)));
        employees.add(new Employee(903, "小芳", departmentDao.getById(1002)));
        employees.add(new Employee(904, "小刚", null));
        employees.add(new Employee(905, "小强", departmentDao.getById(1003)));
    }

    @Override
    public Employee getById(int id) {
        int num = 0;
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            Employee e = employees.get(i);
            if (e.getId() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            return employees.get(index);
        }
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }

    @Override
    public int deleteById(int id) {
        int num = 0;
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            Employee e = employees.get(i);
            if (e.getId() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            employees.remove(index);
            num += 1;
        }
        return num;
    }

    @Override
    public int add(Employee e) {
        if (e == null)
            return 0;
        e.setId((int) (Math.random() * 90 + 910));
        e.setDepartment(departmentDao.getById(e.getDepartment().getId()));
        employees.add(e);
        return 1;
    }

    @Override
    public int update(Employee e) {
        int num = 0;
        if (e == null)
            return num;
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            Employee t = employees.get(i);
            if (t.getId() == e.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            e.setDepartment(departmentDao.getById(e.getDepartment().getId()));
            employees.set(index, e);
            num += 1;
        }
        return num;
    }

}
