package com.tgweb.ssh.dao.impl;

import com.tgweb.ssh.dao.EmployeeDao;
import com.tgweb.ssh.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Chain on 2017/7/17.
 */
@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Employee> getAllEmployees() {
        String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department";
        List<Employee> lst = getCurrentSession().createQuery(hql).list();
        return lst;
    }

    @Override
    public boolean deleteEmployeeById(Long id) {
        String hql = "DELETE FROM Employee e WHERE e.id = :id";
        int num = getCurrentSession().createQuery(hql).setLong("id", id).executeUpdate();
        return num != 0;
    }

    @Override
    public boolean saveOrUpdate(Employee employee) {
        getCurrentSession().saveOrUpdate(employee);
        return true;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department WHERE e.id = :id";
        return (Employee) getCurrentSession().createQuery(hql).setLong("id", id).uniqueResult();
    }

    @Override
    public Employee getEmployeeByName(String name) {
        String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department WHERE e.employeeName = :employeeName";
        return (Employee) getCurrentSession().createQuery(hql).setString("employeeName", name).uniqueResult();
    }

    @Override
    public Employee getEmployeeByEId(String eId) {
        String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department WHERE e.employeeId = :employeeId";
        return (Employee) getCurrentSession().createQuery(hql).setString("employeeId", eId).uniqueResult();
    }
}
