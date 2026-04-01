//package com.dev.blogging.config;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configurable
//@EnableWebSecurity
//public class SecurityConfig{
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf ->csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//            		.anyRequest().authenticated()
//            )
//            .httpBasic(Customizer.withDefaults());
//                
//
//        return http.build();
//    }
//
//	
//}
