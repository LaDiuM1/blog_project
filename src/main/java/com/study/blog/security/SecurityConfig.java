package com.study.blog.security;

import com.study.blog.security.jwt.JwtAuthenticationFilter;
import com.study.blog.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity  // configuration 내장
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        /*
        사용자 정의 인증을 구성하기 위해 AuthenticationManager 의 구성 도구인
        AuthenticationManagerBuilder 사용.
        시큐리티 컨텍스트에서 사용중인 AuthenticationManagerBuilder 공유 객체를 반환 받아
        build 하여 AuthenticationManager 반환하여 빈 등록
         */
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll();
//        http
//                .csrf().disable()   // csrf 공격 비활성화, csrf 공격은 쿠키의 sessionId를 위조하는 공격으로 쿠키 자체를 검증하는 jwt 에서는 해당 공격 원천적 불가
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 토큰 인증 방식을 사용함으로써 세션 관리 기능 비활성화
//                .and()
//                .authorizeRequests(auth -> auth
//                        .antMatchers("/api/auth").permitAll()  // 해당 요청 경로는 비인가 접근 허용
//                        .anyRequest().authenticated()) // 그 외 모든 요청은 인증 처리 활성화
////                        .anyRequest().permitAll())
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//                // 시큐리티 필터 체인에서 UsernamePasswordAuthenticationFilter 처리 이전에 jwt 인증 필터 추가
//                // 인증 필터에 jwt 토큰 공급자 클래스를 매개변수로 전달하여 토큰 인증 처리를 필터와 분리

        return http.build();
    }

}
