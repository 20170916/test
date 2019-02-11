package com.lo.mybatis.imitate.mapperscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lo.mybatis")
@MyEnableAnno//@MapperScan("com.lo.dao")
public class Appconfig {
}
