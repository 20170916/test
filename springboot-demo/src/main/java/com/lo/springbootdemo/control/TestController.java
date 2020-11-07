package com.lo.springbootdemo.control;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {


    @Value("${server.port}")
    private String port;


    @GetMapping(value = "/getPort")
    public String getPort(){
        return port;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody MyRequestBody responseBody) throws InterruptedException {
        Thread.sleep(300);
        return responseBody;
    }

    @RequestMapping(value = "/post/xml", method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody User responseBody) throws InterruptedException {
        Thread.sleep(300);
        return responseBody;
    }
    @Data
    private static class User{
        String id;
        String name;
    }

    @RequestMapping(value = "/postWithUrlParam", method = RequestMethod.POST)
    @ResponseBody
    public String postWithUrlParam(@RequestParam String param, @RequestBody MyRequestBody responseBody) {
        return param;
    }

    @Data
    private static class MyRequestBody{
        String bodyParam;
    }
}
