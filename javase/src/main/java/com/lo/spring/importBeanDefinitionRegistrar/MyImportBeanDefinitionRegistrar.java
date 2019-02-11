package com.lo.spring.importBeanDefinitionRegistrar;

import com.lo.dao.UserDao;
import com.lo.spring.factorybean.MyFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //获取代理对象，并放入ioc容器中
        /*final UserDaoInvocationHandler userDaoInvocationHandler = new UserDaoInvocationHandler();
        Class[] classes=new Class[]{UserDao.class};
        final UserDao o = (UserDao) Proxy.newProxyInstance(UserDao.class.getClassLoader(), classes, userDaoInvocationHandler);
        System.out.println(o.getClass());
        try {
            final UserDao userDao = o.getClass().newInstance();
            System.out.println(userDao);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/


        //获取bd，Spring中是通过scan扫描后将类转成bd的
        //这里手动获取bd
        final BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MyFactoryBean.class);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        registry.registerBeanDefinition("registerUserDao",beanDefinition);



    }

    public static class UserDaoInvocationHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("user dao sql");
            return null;
        }
    }
}
