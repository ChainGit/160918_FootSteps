package com.tgweb.springmvc.dao.impl;

import com.tgweb.springmvc.dao.DepartmentDao;
import com.tgweb.springmvc.entities.Department;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {

    public static List<Department> departments;

    //模拟数据
    static {
        departments = new ArrayList<>();
        departments.add(new Department(1001, "市场部"));
        departments.add(new Department(1002, "技术部"));
        departments.add(new Department(1003, "产品部"));
        departments.add(new Department(1004, "人事部"));
    }

    @Override
    public Department getById(int id) {
        int num = 0;
        int index = -1;
        for (int i = 0; i < departments.size(); i++) {
            Department d = departments.get(i);
            if (d.getId() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            return departments.get(index);
        }
        return null;
    }

    @Override
    public List<Department> getAll() {
        return departments;
    }
}
