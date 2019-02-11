package com.lo.mybatis.imitate.mapperscan;

import com.lo.mybatis.imitate.mapperscan.dao.User2Dao;
import com.lo.mybatis.imitate.mapperscan.dao.UserDao;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Class[] classes=new Class[]{UserDao.class, User2Dao.class};
        for(Class clazz:classes){
            final BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
            beanDefinition.setBeanClass(MyMapperFactoryBean.class);
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(clazz.getName());
            registry.registerBeanDefinition(clazz.getSimpleName(),beanDefinition);
        }
        //获取bd，Spring中是通过scan扫描后将类转成bd的
        //这里手动获取bd




    }

}
