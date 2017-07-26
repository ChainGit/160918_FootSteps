package com.tgweb.ssh.dao.impl;

import com.tgweb.ssh.dao.DepartmentDao;
import com.tgweb.ssh.entities.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Chain on 2017/7/17.
 */
@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private SessionFactory sessionFactory;


    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Department> getAllDepartments() {
        String hql = "SELECT new Department(d.id,d.departmentId,d.departmentName) FROM Department d";
        return getCurrentSession().createQuery(hql).list();
    }
}

