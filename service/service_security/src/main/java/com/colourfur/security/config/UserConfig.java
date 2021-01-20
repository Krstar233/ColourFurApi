package com.colourfur.security.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.colourfur.security.mapper")
@ServletComponentScan(basePackages = "com.colourfur")
public class UserConfig {
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }
}
