package com.example.taskmanagementsystems.impl.security;

import com.example.taskmanagementsystems.impl.security.jwt.JwtAuthenticationEntryPoint;
import com.example.taskmanagementsystems.impl.security.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final UserDetailsServiceImpl userDetailsService;

  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  private final JwtTokenFilter jwtTokenFilter;

  private final PasswordEncoder passwordEncoder;

//  @Bean
//  public DaoAuthenticationProvider authenticationProvider() {
//    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//    authProvider.setUserDetailsService(userDetailsService);
//    authProvider.setPasswordEncoder(passwordEncoder);
//    return authProvider;
//  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(auth ->
            auth.requestMatchers("/api/user-service/auth/**").permitAll()
                .requestMatchers("/api/user-service/registration/user").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**")
                .permitAll()
                .anyRequest().authenticated())
        .exceptionHandling(configurer ->
            configurer.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(httpSecuritySessionManagementConfigurer ->
            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(provider)
        .build();
  }

}
