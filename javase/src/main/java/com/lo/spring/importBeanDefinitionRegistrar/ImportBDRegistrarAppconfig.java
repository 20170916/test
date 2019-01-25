package com.lo.spring.importBeanDefinitionRegistrar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lo")
//@MapperScan("com.lo.dao")
public class ImportBDRegistrarAppconfig {
}
