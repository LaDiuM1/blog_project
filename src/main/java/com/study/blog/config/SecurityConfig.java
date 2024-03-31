package com.study.blog.config;

import com.study.blog.security.JwtAuthenticationFilter;
import com.study.blog.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // JWT를 사용하므로 CSRF 보호를 비활성화합니다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않도록 설정
                .and()
                .authorizeRequests() // 요청에 대한 사용권한 체크
                .antMatchers("/api/auth/**").permitAll() // `/api/auth/**` 패턴의 요청은 인증 없이 접근 허용
                .anyRequest().authenticated() // 나머지 요청들은 모두 인증 필요
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class); // JWT 필터를 등록

        return http.build();
    }
}
