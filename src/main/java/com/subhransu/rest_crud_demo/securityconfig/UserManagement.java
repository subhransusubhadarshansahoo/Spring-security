package com.subhransu.rest_crud_demo.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class UserManagement {



    

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//
//     UserDetails user1= User.builder()
//                .username("roshan")
//                .password("{noop}2302165")
//                .roles("EMPLOYEE").build();    //CONSTRUCTOR CHAINING
//
//        UserDetails user2= User.builder()
//                .username("subhra")
//                .password("{noop}2302163")
//                .roles("EMPLOYEE","MANAGER").build();
//
//
//        UserDetails user3= User.builder()
//                .username("ronak")
//                .password("{noop}2302140")
//                .roles("EMPLOYEE","MANAGER","ADMIN").build();
//
//
//        UserDetails user4= User.builder()
//                .username("santa")
//                .password("{noop}2302140")
//                .roles("EMPLOYEE","ADMIN").build();
//
//
////        Collection<UserDetails>collection=new ArrayList<>();
////
////        collection.add(user1);
//
//
//        ArrayList<UserDetails> list=new ArrayList<>();
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);
//
//        return new InMemoryUserDetailsManager(list);
//
//
//
//
//
//    }




    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){


        JdbcUserDetailsManager userDetailsManager=new JdbcUserDetailsManager(dataSource);


        userDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");



        return  userDetailsManager;


//        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        System.out.println("security filter is added successfully");
        System.out.println("this security filter is now handel by the spring boot ");


        http.authorizeHttpRequests(

                configurer->configurer
                        .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH,"/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")




        );


        http.httpBasic(Customizer.withDefaults());


        http.csrf(csrf->csrf.disable());  //disable the csrf protection


        return http.build();




    }


}
