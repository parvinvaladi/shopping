package com.shopping.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        return http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.DELETE,"/v1/product/delete","/v1/category/delete").hasRole("USER")
//                .antMatchers(HttpMethod.POST,"/v1/product/","/v1/category/").permitAll()
//                .antMatchers("/","/**").permitAll()
//                .and().build();
//    }
}
