package com.lo.proxy.dynamic;


import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyUserServiceInvocationHandler implements InvocationHandler {
    Object target;

    public Object bind(Object target){
        this.target=target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("power before invoke");
        final Object ret = method.invoke(target, args);
        System.out.println("power after invoke");
        return ret;
    }

    public static void main(String[] args) throws IOException {
        UserService userService= (UserService) new MyUserServiceInvocationHandler().bind(new UserServiceImpl());
        userService.test();
        System.out.println(System.getProperty("user.dir"));
        final byte[] classBytes = ProxyGenerator.generateProxyClass("$Proxy0", UserServiceImpl.class.getInterfaces());
        FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"/UserServiceImpl.class");
        fos.write(classBytes);
        fos.flush();
    }
}
