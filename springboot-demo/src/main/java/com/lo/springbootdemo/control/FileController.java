package com.lo.springbootdemo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping("/file")
public class FileController {


    @RequestMapping("/test")
    public String userList2(Model model) throws Exception {
        model.addAttribute("hello","Hello, Spring Boot!");
        //model.addAttribute("userList", new List[]{});
        return "/test/test";
    }

    @RequestMapping(value = "/hello")
    public String helloGet() {
        return "hello";
    }


}
