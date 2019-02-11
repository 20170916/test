package com.lo.mybatis.imitate.mapperscan;

import com.lo.spring.factorybean.MySpringBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

//@Component
public class MyMapperFactoryBean implements FactoryBean {

    Class mapperInterface;

    public MyMapperFactoryBean(Class mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object getObject()  {
        final UserDaoInvocationHandler userDaoInvocationHandler = new UserDaoInvocationHandler();
        Class[] classes=new Class[]{mapperInterface};
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), classes, userDaoInvocationHandler);


    }

    @Override
    public Class<?> getObjectType() {
        return MySpringBean.class;
    }
}
