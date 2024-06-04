package com.Foodcourt.fc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    JwtRequestFilter jwtRequestFilter;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors();
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/every").permitAll()
                .antMatchers("/touristSpots").permitAll()
                .antMatchers("/save").permitAll()
                .antMatchers("/delete").permitAll()
                .antMatchers("/category").permitAll()
                //.antMatchers("/fetch").permitAll()
                .antMatchers("/modify").permitAll()
                .antMatchers("/mails").permitAll()
                .antMatchers("/bill").permitAll()
                .antMatchers("/reqotp").permitAll()
                .antMatchers("/orderready").permitAll()
                .antMatchers("/locations").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/hii").hasAnyAuthority("ADMIN")
                .antMatchers("/login").permitAll()
                //.antMatchers("/every").permitAll()
                .antMatchers("/touristSpots").permitAll()
                .antMatchers("/save").permitAll()
                .antMatchers("/delete").permitAll()
//                .antMatchers("/category").permitAll()
                .antMatchers("/fetch").permitAll()
                .antMatchers("/modify").permitAll()
                .antMatchers("/save-user").permitAll()
                .antMatchers("/save-order").permitAll()
                .antMatchers("/past-add").permitAll()
                .antMatchers("/past-fetch").permitAll()
                .antMatchers("/waiting-add").permitAll()
                .antMatchers("/waiting-fetch").permitAll()
                .antMatchers("/locations").hasAnyAuthority("ADMIN","USER")
//                .antMatchers("/save").hasAnyAuthority("ADMIN")
//                .antMatchers("/every").hasAnyAuthority("ADMIN")
//                .antMatchers("/delete").hasAnyAuthority("ADMIN")
                .antMatchers("/category").hasAnyAuthority("ADMIN")
//                .antMatchers("/fetch").hasAnyAuthority("ADMIN")
//                .antMatchers("/modify").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .logout().disable();
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
