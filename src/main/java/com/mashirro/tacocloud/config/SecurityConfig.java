package com.mashirro.tacocloud.config;

import com.mashirro.tacocloud.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private HikariDataSource dataSource;

    @Autowired
    private UserInfoService userInfoService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //AuthenticationManagerBuilder使用构造者风格的接口来构建认证细节
//        auth.inMemoryAuthentication()
//                .withUser("mashiro").password("{noop}123456").authorities("ROLE_USER")
//                .and()
//                .withUser("mashiro007").password("{noop}123456").authorities("ROLE_USER");
//        auth.jdbcAuthentication()
//                .dataSource(dataSource);
        auth.userDetailsService(userInfoService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/design", "/orders").hasRole("ROLE_USER")
//                .antMatchers("/", "/**").permitAll();
        http
            .authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll()")

            .and()
            .formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/authenticate")
                //.usernameParameter("user")
                //.passwordParameter("pwd")
                .defaultSuccessUrl("/design")

            .and()
            .logout()
                .logoutSuccessUrl("/")
        ;
    }
}
