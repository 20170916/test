package com.lo.spring.config;

import com.lo.spring.pojo.Entitlement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lo.spring")
public class AppConfig {
    @Bean(name="entitlement")
    public Entitlement entitlement() {
        Entitlement ent= new Entitlement();
        ent.setName("Entitlement");
        ent.setTime(1);
        return ent;
    }

    @Bean(name="entitlement2")
    public Entitlement entitlement2() {
        Entitlement ent= new Entitlement();
        ent.setName("Entitlement2");
        ent.setTime(2);
        return ent;
    }
}
