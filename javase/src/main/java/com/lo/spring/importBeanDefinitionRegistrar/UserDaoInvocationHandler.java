package com.lo.spring.importBeanDefinitionRegistrar;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserDaoInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)  {
        System.out.println("user dao sql--");
        final String sql = method.getDeclaredAnnotation(Select.class).value()[0];
        System.out.println(sql);
        return null;
    }
}
