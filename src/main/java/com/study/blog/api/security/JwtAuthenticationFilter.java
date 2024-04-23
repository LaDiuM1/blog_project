package com.study.blog.api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);    // 요청에서 jwt 토큰 분리하는 메서드 수행
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) { // 토큰이 텍스트를 포함해야 하며 토큰 유효 메서드 반환값이 true 일 경우 인증 처리
                Authentication authentication = tokenProvider.getAuthentication(jwt);   // 토큰으로부터 인증 정보 생성
                SecurityContextHolder.getContext().setAuthentication(authentication);   // 인증 정보를 시큐리티 컨텍스트에 설정하여 인증 처리
            }
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();   // 예외 발생 시 컨텍스트 클리어
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());   // 예외 응답 반환
            return; // 처리 중단
        }
        // 현재 필터가 정상적으로 수행됐을 경우 다음 스프링 필터 체인에 http 정보 매개변수로 전달
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");    // 헤더 Authorization 키의 값 추출
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {    // Authorization 추출 값이 텍스트를 포함해야하며 Bearer로 시작하는 경우 추출 시도
            return bearerToken.substring(7);    // 토큰 추출 후 반환
        }
        return null;    // 토큰 추출 실패 시 null 반환
    }
}
