package com.lo.springbootdemo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/test")
    public String userList2(Model model) throws Exception {
        model.addAttribute("hello","Hello, Spring Boot!");
        //model.addAttribute("userList", new List[]{});
        return "/test/test";
    }

    @GetMapping(value = "/hello")
    public String helloGet() {
        return "hello";
    }
}
