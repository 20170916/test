package com.lo.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new MySpringBean();
    }

    @Override
    public Class<?> getObjectType() {
        return MySpringBean.class;
    }
}
