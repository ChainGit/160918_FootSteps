package com.tgweb.springmvc.handlers;

import com.tgweb.springmvc.entities.Score;
import com.tgweb.springmvc.entities.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Map;

//将model放在session中
@SessionAttributes("d_date")
//可以修饰在类上
@RequestMapping("day01")
@Controller
public class HelloWorldHandler {

    private static final String SUCCESS = "success";

    //ModelAndView->View->ViewResolver
    @RequestMapping("/helloworld")
    public String helloWorld() {
        System.out.println("hello world");
        return SUCCESS;
    }

    //Ant风格的URL
    @RequestMapping(value = "/*/testpost", method = RequestMethod.POST, params = {"username=admin", "password!=123"}, headers = {"Accept-Language=zh-CN,zh;q=0.8"})
    public String testPost() {
        System.out.println("test post method");
        return SUCCESS;
    }

    @RequestMapping("/testvariable/{id}")
    public String testVariable(@PathVariable("id") String id) {
        System.out.println("test variable " + id);
        return SUCCESS;
    }

    @RequestMapping("/testrequestparams")
    public String testRequestParams(@RequestParam("username") String username,
                                    @RequestParam(value = "password", required = false, defaultValue = "123") String password) {
        System.out.println("test Request Params: " + username + " " + password);
        return SUCCESS;
    }

    @RequestMapping("/testrequestheader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String requestHeader) {
        System.out.println("test Request Header: " + requestHeader);
        return SUCCESS;
    }

    @RequestMapping("/testcookievalue")
    public String testRequestCookieValue(@CookieValue("JSESSIONID") String cookieValue) {
        System.out.println("test Cookie Value: " + cookieValue);
        return SUCCESS;
    }

    @RequestMapping(value = "/testpojoparam", method = RequestMethod.POST)
    public String testPojoParam(Student student) {
        System.out.println(student);
        return SUCCESS;
    }

    @RequestMapping("/testnativeservletapi")
    public String testNativeServletApi(ServletRequest request1, ServletRequest request2,
                                       ServletResponse response, Writer out) throws IOException {
        out.write("native api");
        String addr1 = request1.getRemoteAddr();
        String addr2 = request2.getRemoteAddr();
        System.out.println(addr1);
        System.out.println(addr2);
        System.out.println(request1 == request2);
        return null;
    }

    /**
     * 均会转成ModelAndView返回
     *
     * @return
     */
    @RequestMapping("/testmodelandview")
    public ModelAndView testModelAndWiew() {
        String dateView = "dateview";
        ModelAndView mv = new ModelAndView(dateView);
        Date date = new Date();
        mv.addObject("s_date", date);
        return mv;
    }

    @RequestMapping("/testmodelmap")
    public String testModelMap(Map<String, Object> modelMap) {
        String dateView = "dateview";
        modelMap.put("m_date", new Date());
        return dateView;
    }

    @RequestMapping("/testmodel")
    public String testModel(Model model) {
        String dateView = "dateview";
        model.addAttribute("d_date", new Date());
        return dateView;
    }

    //类似Struts2的paramsPrepareParams拦截器
    @ModelAttribute
    public Student getStudent(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
        System.out.println("getStudent");
        if (id != null) {
            //模拟从数据库中获取数据
            Student student = new Student(123, "jack", 22, new Score(84, 59, 76));
            return student;
        }
        return null;
    }

    // @ModelAttribute
    public void getStudent(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
                           Map<String, Object> modelMap) {
        System.out.println("getStudent");
        if (id != null) {
            //模拟从数据库中获取数据
            Student student = new Student(123, "jack", 22, new Score(84, 59, 76));
            modelMap.put("student", student);
        }
    }

    @RequestMapping(value = "/testmodelattribute", method = RequestMethod.POST)
    public String testModelAttribute(Student student) {
        System.out.println("testModelAttribute student: " + student);
        return SUCCESS;
    }

    @RequestMapping("/testjstlview")
    public String testJstlView() {
        String dateView = "dateview";
        return dateView;
    }

    @RequestMapping("/testmyview")
    public String testMyView() {
        return "myView";
    }

    @RequestMapping("/testredirect")
    public String testRedirect() {
        return "redirect:/index.jsp";
    }

}