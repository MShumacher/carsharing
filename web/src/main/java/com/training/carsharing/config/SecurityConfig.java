package com.training.carsharing.config;

import com.training.carsharing.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security-config.xml analogue
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private CustomAuthenticationProvider authenticationProvider;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(11);
//    }
//
//    @Autowired
//    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder){
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
////                .anyRequest().authenticated() //all requests will checked
//                .and()
//                .formLogin().loginPage("/login.html").permitAll().usernameParameter("j_username")
//                .passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check").failureUrl("/login.html?error=true")
//                .and()
//                .httpBasic()
//                .and()
//                .authorizeRequests().antMatchers("/security/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                .and()
//                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/")
//                .and()
//                .rememberMe().key("myKey").tokenValiditySeconds(300)
//                .and()
//                .csrf().disable();
//
//    }
}
