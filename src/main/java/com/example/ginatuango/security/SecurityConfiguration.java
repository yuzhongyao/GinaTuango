package com.example.ginatuango.security;


import com.example.ginatuango.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN");
            authorizationManagerRequestMatcherRegistry.requestMatchers(new AntPathRequestMatcher("/user/**")).hasRole("USER");
            authorizationManagerRequestMatcherRegistry.requestMatchers(new AntPathRequestMatcher("/admin")).hasRole("ADMIN");
            authorizationManagerRequestMatcherRegistry.requestMatchers(new AntPathRequestMatcher("/user")).hasRole("USER");

        });
        http.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.successForwardUrl("/admin");
        });
        super.configure(http);
        setLoginView(http, LoginView.class);

    }







}
