package com.openclassrooms.Project6.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/resources/**").permitAll().anyRequest().permitAll()
                    /*.anyRequest().authenticated()*/
                .and()
                    .formLogin()

                    //just changed "/login" from "/"
                        .loginPage("/")
                        .permitAll()

                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll();

                /*.authorizeRequests()
                .antMatchers("/css/**", "/products").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")

                .and()
                .formLogin().loginPage("/login").failureUrl("/login-error")

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")*/
    }
}
