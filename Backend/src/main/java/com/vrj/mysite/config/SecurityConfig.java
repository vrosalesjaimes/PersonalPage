package com.vrj.mysite.config;

import com.vrj.mysite.security.jwt.JwtAuthenticationFilter;
import com.vrj.mysite.security.jwt.JwtAuthorizationFilter;
import com.vrj.mysite.security.jwt.JwtUtils;
import com.vrj.mysite.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthorizationFilter authorizationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/users/create-user").permitAll();
                    auth.requestMatchers("/articles/{id-idiom}/card-view").permitAll();
                    auth.requestMatchers("/articles/{id}").permitAll();
                    auth.requestMatchers("/articles/search/title/{idiom-id}").permitAll();
                    auth.requestMatchers("/articles/search/authorName/{idiom-id}").permitAll();
                    auth.requestMatchers("/articles/search/tagName/{idiom-id}").permitAll();
                    auth.requestMatchers("/about/get/{idiom-id}").permitAll();
                    auth.requestMatchers("/certifications/{idiom-id}/all-by-idiom").permitAll();
                    auth.requestMatchers("/certifications/search/title/{idiom-id}").permitAll();
                    auth.requestMatchers("/certifications/search/tagName/{idiom-id}").permitAll();
                    auth.requestMatchers("/educations/idiom/{id}").permitAll();
                    auth.requestMatchers("/personal-projects/{id}").permitAll();
                    auth.requestMatchers("/personal-projects/by-idiom/{idiomId}").permitAll();
                    auth.requestMatchers("/personal-projects/search/title").permitAll();
                    auth.requestMatchers("/personal-projects/search/tag").permitAll();
                    auth.requestMatchers("/personal-projects/search/author").permitAll();
                    auth.requestMatchers("/posts/{id}").permitAll();
                    auth.requestMatchers("/posts/by-idiom/{idiomId}").permitAll();
                    auth.requestMatchers("/posts/search/title").permitAll();
                    auth.requestMatchers("/posts/search/tag").permitAll();
                    auth.requestMatchers("/scholar-projects/{id}").permitAll();
                    auth.requestMatchers("/scholar-projects/by-idiom/{idiomId}").permitAll();
                    auth.requestMatchers("/scholar-projects/search/title").permitAll();
                    auth.requestMatchers("/scholar-projects/search/tag").permitAll();
                    auth.requestMatchers("/work-experiences/by-idiom/{idiomId}").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
