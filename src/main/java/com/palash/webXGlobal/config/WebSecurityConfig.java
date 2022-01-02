package com.palash.webXGlobal.config;

/**
 * @author Majbah Uddin [majbahbuet08@gmail.com]
 * 3:02 PM 19 December,2018
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String dateFormat = "yyyy-MM-dd";
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        System.out.println("configAuthentication  : ++");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    protected void configure(HttpSecurity http) throws Exception {

        // The pages does not require login
        http.authorizeRequests()
                .antMatchers("/static/**", "/bootstrap-513/**", "/plugins/**").permitAll()
                .antMatchers("/", "/home", "/about", "/error", "/createAccount", "/fragments/**", "/selected", "/createUser").permitAll()
                .anyRequest().authenticated();

                //login
                /*Login page*/
        http.authorizeRequests().and().formLogin().loginPage("/login").permitAll()
                //.loginProcessingUrl("/j_spring_security_check") // Submit URL
                .usernameParameter("uname")
                .passwordParameter("pswd")
                .defaultSuccessUrl("/welcome")
                .and()

                //logout
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll().logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .and()
                //handlig exception
                .exceptionHandling().accessDeniedPage("/error/403")
                .and()//protect from custom link .disable() link
                .csrf();


        // config for showing iframe,embed from origin site
        http.headers()
                .frameOptions().sameOrigin();

    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
