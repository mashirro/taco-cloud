package com.mashirro.tacocloud.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HikariDataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //AuthenticationManagerBuilder使用构造者风格的接口来构建认证细节
//        auth.inMemoryAuthentication()
//                .withUser("mashiro").password("{noop}123456").authorities("ROLE_USER")
//                .and()
//                .withUser("mashiro007").password("{noop}123456").authorities("ROLE_USER");
//        auth.jdbcAuthentication()
//                .dataSource(dataSource);
    }
}
