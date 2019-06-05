package com.lo.aop;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.lo.aop")
@EnableAspectJAutoProxy
//@MapperScan("com.lo.dao")
//@PropertySource("classpath:jdbc.properties")
public class AppConfig {


}
