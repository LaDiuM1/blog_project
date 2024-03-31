package com.study.blog.security;

import com.study.blog.exception.JwtAuthenticationException;
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
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}") // 1h in milliseconds
    private long validityInMilliseconds;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(Authentication authentication) {
        // Authentication 객체의 권한 정보를 가져옵니다.
        String username = ((User) authentication.getPrincipal()).getUsername();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // 이 부분이 수정되었습니다.
                .collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        // 토큰에서 사용자 정보를 가져오는 로직을 구현합니다.
        // 이 예시에서는 단순화를 위해 실제 사용자 정보를 로드하지 않고 토큰에 포함된 정보를 사용합니다.
        UserDetails userDetails = new User(getUsername(token), "", getAuthorities(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public List<SimpleGrantedAuthority> getAuthorities(String token) {
        List<String> roleList = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("auth", List.class);
        return roleList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}