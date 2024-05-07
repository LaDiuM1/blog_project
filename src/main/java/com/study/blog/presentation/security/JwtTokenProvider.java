package com.study.blog.presentation.security;

import com.study.blog.business.user.CustomUserDetails;
import com.study.blog.support.exception.JwtAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds;

    @PostConstruct  // 해당 클래스의 의존성 주입 시 실행
    protected void init() {
        // 비밀키를 Base64로 인코딩
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 사용자 인증 정보로 JWT 토큰 생성
    public String createToken(Authentication authentication) {
        String username = ((CustomUserDetails) authentication.getPrincipal()).getUsername(); // 인증으로부터 사용자 이름 추출

        Claims claims = Jwts.claims().setSubject(username); // 토큰에 메타데이터 주입, 사용자 이름 설정, jwt 의 subject 필드로 등록
        // 메타데이터에 auth 를 키로하여 권한 정보 설정, 현재 프로젝트는 단일 권한이나 추후 권한이 추가 될 수 있으므로 stream 으로 처리
        claims.put("auth", authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        // 현재 시간 기준으로 인증 만료 시간 설정
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        // 각 정보를 Jwt 필드에 매칭하여 빌드 후 반환
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰으로 인증 정보를 반환하는 메서드
    public Authentication getAuthentication(String token) {
        // 토큰에서 사용자 이름과 권한을 추출하는 메서드를 이용, UserDetails 객체 생성
        UserDetails userDetails = new User(getUsername(token), "", getAuthorities(token));
        // 생성된 UserDetails 객체로 인증 객체를 생성하여 반환
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    // 토큰에서 사용자 이름 반환하는 메서드
    public String getUsername(String token) {
        // 토큰을 비밀키를 이용하여 해싱 후 생성 시 subject 에 등록된 유저 이름을 반환
        // 비밀키 검증 예외는 토큰에서 인증정보를 추출하기 전에 수행되므로 여기서는 생략
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    // 토큰에서 권한을 분리하여 List<SimpleGrantedAuthority>로 반환하는 메서드
    public List<SimpleGrantedAuthority> getAuthorities(String token) {
        // 토큰을 비밀키를 이용하여 해싱 후 권한 정보를 List 문자열 형태로 추출
        // 비밀키 검증 예외는 토큰에서 인증정보를 추출하기 전에 수행되므로 여기서는 생략
        List<String> roleList = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("auth", List.class);
        // 문자열 형태의 권한을 SimpleGrantedAuthority 생성자로 전달하여 SimpleGrantedAuthority 생성 후 stream 으로 List 생성
        return roleList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // 토큰 유효성 검증 메서드
    public boolean validateToken(String token) {
        try {
            // 토큰에 비밀키를 설정, parseClaimsJws 로 해싱 시도 후 비밀키 검증 실패 시 발생되는 JwtException 예외 처리
            // 검증 완료 시 jws의 메타데이터를 claims 형태로 저장
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            /*
            메타데이터에서 인증 유효 시간을 추출하여 현재 시간보다 이전인지 반환, 부정 연산자를 사용하여
            이전일 경우 true 이후면 false 로 유효성 여부 반환
            */
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException e) {
            throw new JwtAuthenticationException("유효하지 않은 JWT 토큰입니다.");
        }
    }
}
