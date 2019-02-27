package com.lo.proxy.dynamic;


public class UserServiceImpl implements UserService {
    @Override
    public String test() {
        System.out.println("test in userServiceImpl");
        return "test";
    }
}
