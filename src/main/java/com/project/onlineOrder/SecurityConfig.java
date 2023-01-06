package com.project.onlineOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@EnableWebSecurity //when spring constructs, configure it
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public SecurityConfig(DataSource dataSource,PasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.passwordEncoder  = passwordEncoder;
    }

    @Override //authority
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf()
                .disable()
                .formLogin()
                .failureForwardUrl("/login?error=true");
        http
                .authorizeRequests()
                .antMatchers("/order/*", "/cart", "/checkout").hasAuthority("ROLE_USER") //set up in CustomerDao
                .anyRequest().permitAll();

    }

    @Override //authentication, match between db info and login info
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .jdbcAuthentication() //filter will connect to the database
                .dataSource(dataSource) //pass the data source
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("SELECT email, password, enabled FROM customer WHERE email=?") //FROM primary key
                .authoritiesByUsernameQuery("SELECT email, authority FROM authority WHERE email=?");

    }

}

