package com.lo.spring.importBeanDefinitionRegistrar;

import com.lo.dao.UserDao;
import com.lo.spring.factorybean.MySpringBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class MyUserDaoFactoryBean implements FactoryBean {
    @Override
    public Object getObject()  {
        final UserDaoInvocationHandler userDaoInvocationHandler = new UserDaoInvocationHandler();
        Class[] classes=new Class[]{UserDao.class};
        return Proxy.newProxyInstance(UserDao.class.getClassLoader(), classes, userDaoInvocationHandler);


    }

    @Override
    public Class<?> getObjectType() {
        return MySpringBean.class;
    }
}
