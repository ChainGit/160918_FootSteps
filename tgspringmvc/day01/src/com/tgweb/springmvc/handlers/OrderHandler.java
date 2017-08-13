package com.tgweb.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 模拟REST风格
 */
@RequestMapping("/day01")
@Controller
public class OrderHandler {

    private static final String SUCCESS = "success";

    //获得
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public String orderGet(@PathVariable("id") String id) {
        System.out.println("order GET: " + id);
        return SUCCESS;
    }

    //增加
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String orderPost() {
        System.out.println("order POST ");
        return SUCCESS;
    }

    //删除
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public String orderDelete(@PathVariable("id") String id) {
        System.out.println("order DELETE: " + id);
        return SUCCESS;
    }

    //更新
    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public String orderPut(@PathVariable("id") String id) {
        System.out.println("order PUT: " + id);
        return SUCCESS;
    }

}
