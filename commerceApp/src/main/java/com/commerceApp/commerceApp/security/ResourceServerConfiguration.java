package com.commerceApp.commerceApp.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import static jdk.vm.ci.aarch64.AArch64.v2;


@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    AppUserDetailsService userDetailsService;

    public ResourceServerConfiguration() {
        super();
    }

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }
    private static final String[] AUTH_WHITELIST={
     "/v2/api-docs",
            "/swagger/resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/admin/home").hasAnyRole("ADMIN")
                .antMatchers("/seller/home").hasAnyRole("ADMIN","SELLER")
                .antMatchers("/customer/home").hasAnyRole("ADMIN","CUSTOMER")
                .antMatchers("/activate/customer").anonymous()
                .antMatchers("/activate/{id}").hasAnyRole("ADMIN")
                .antMatchers("/deactivate/{id}").hasAnyRole("ADMIN")
                .antMatchers("/register/*").anonymous()
                .antMatchers("/resend-activation-link/customer").anonymous()
                .antMatchers("/customers").hasAnyRole("ADMIN")
                .antMatchers("/sellers").hasAnyRole("ADMIN")
                .antMatchers("/forgot-password", "/reset-password", "/change-password").hasAnyRole("CUSTOMER", "SELLER")
                .antMatchers("/customer/profile").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/seller/profile").hasAnyRole("SELLER", "ADMIN")
                .antMatchers("/customer/addresses/*").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/seller/addresses/*").hasAnyRole("SELLER", "ADMIN")
                .antMatchers("/doLogout").hasAnyRole("ADMIN","CUSTOMER","SELLER")
                .antMatchers("categories", "category/{id}").hasAnyRole("ADMIN")
                .antMatchers("/categories/customer").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/categories/seller").hasAnyRole("SELLER","ADMIN")
                .antMatchers("/seller/**").hasAnyRole("SELLER","ADMIN")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/product/deactivate/{id}").hasAnyRole("ADMIN")
                .antMatchers("/product/activate/{id}").hasAnyRole("ADMIN")

                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable();
    }
}