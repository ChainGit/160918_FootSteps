package com.tgweb.springmvc.handlers;

import com.tgweb.springmvc.dao.DepartmentDao;
import com.tgweb.springmvc.dao.EmployeeDao;
import com.tgweb.springmvc.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import java.util.Map;

@RequestMapping("day02")
@Controller
public class EmployeeHandler {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";


    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/list")
    public String listView(Map<String, Object> modelMap) {
        modelMap.put("employees", employeeDao.getAll());
        return "list";
    }

    @RequestMapping(value = "/add")
    public String addView(Map<String, Object> modelMap) {
        modelMap.put("departments", departmentDao.getAll());
        modelMap.put("employee", new Employee());
        return "edit";
    }

    @RequestMapping(value = "/edit")
    public String editView(@RequestParam("id") int id, Map<String, Object> modelMap) {
        modelMap.put("departments", departmentDao.getAll());
        modelMap.put("employee", employeeDao.getById(id));
        return "edit";
    }

    //增加
    @RequestMapping(value = "/e", method = RequestMethod.POST)
    public String addEmployee(ServletRequest request, Employee e) {
        employeeDao.add(e);
        return "redirect:" + request.getServletContext().getContextPath() + "/day02/list";
    }

    //删除
    @RequestMapping(value = "/e/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(ServletRequest request, @PathVariable("id") int id) {
        employeeDao.deleteById(id);
        return "redirect:" + request.getServletContext().getContextPath() + "/day02/list";
    }

    //更新
    @RequestMapping(value = "/e", method = RequestMethod.PUT)
    public String editEmployee(ServletRequest request, Employee e) {
        employeeDao.update(e);
        return "redirect:" + request.getServletContext().getContextPath() + "/day02/list";
    }

    //获得
    @RequestMapping(value = "/e/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable("id") int id, Map<String, Object> modelMap) {
        modelMap.put("employee", employeeDao.getById(id));
        return SUCCESS;
    }

    @RequestMapping(value = "/add2")
    public String addView2() {
        return "add2";
    }

    //String转Employee
    @RequestMapping(value = "/e2", method = RequestMethod.POST)
    public String addEmployee2(ServletRequest request, @RequestParam("employee") Employee e) {
        employeeDao.add(e);
        return "redirect:" + request.getServletContext().getContextPath() + "/day02/list";
    }
}
