package com.tgweb.springmvc.common.converters;

import com.chain.utils.StringUtils;
import com.tgweb.springmvc.entities.Department;
import com.tgweb.springmvc.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


//自定义类型转换器
@Component
public class StringToEmployeeConverter implements Converter<String, Employee> {

    private Logger logger = LoggerFactory.getLogger(StringToEmployeeConverter.class);

    @Override
    public Employee convert(String s) {
        if (StringUtils.isEmpty(s))
            throw new RuntimeException("empty string");
        String[] parts = s.split("#");
        if (parts.length == 3) {
            String sid = parts[0];
            String sname = parts[1];
            String sdepartmentid = parts[2];
            int id = Integer.parseInt(sid);
            int departmentid = Integer.parseInt(sdepartmentid);
            Department department = new Department();
            department.setId(departmentid);
            Employee employee = new Employee(id, sname, department);
            return employee;
        }
        return null;
    }
}
