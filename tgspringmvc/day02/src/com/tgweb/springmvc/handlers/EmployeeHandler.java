package com.tgweb.springmvc.handlers;

import com.tgweb.springmvc.dao.DepartmentDao;
import com.tgweb.springmvc.dao.EmployeeDao;
import com.tgweb.springmvc.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("day02")
@Controller
public class EmployeeHandler {

    private static Logger logger = LoggerFactory.getLogger(EmployeeHandler.class);

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
    public String addEmployee(ServletRequest request, @Valid Employee e, BindingResult bindingResult, Map<String, Object> modelMap) {
        if (bindingResult.getErrorCount() > 0) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            logger.warn("error happened: " + (fieldErrors.stream().map(FieldError::getDefaultMessage)).collect(Collectors.toList()));
            modelMap.put("exception", fieldErrors);
            return ERROR;
        }
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
    public String editEmployee(ServletRequest request, Map<String, Object> modelMap, @Valid Employee e, Errors errors) {
        if (errors.hasErrors()) {
            modelMap.put("departments", departmentDao.getAll());
            modelMap.put("employee", e);
            return "edit";
        }
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

    //可以对DateBinder做一些配置
    //@InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("name");
    }

    @ResponseBody
    @RequestMapping("/testjson")
    public List<Employee> testjson() {
        return employeeDao.getAll();
    }


    @RequestMapping("/testi18n")
    public String testI18NView() {
        return "i18n";
    }
}
