package com.study.blog.business.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority /* 시큐리티 권한 부여 클래스 상속 */{
    ADMIN,    // 컨텐츠 소유 및 관리 권한
    MODERATOR,// 컨텐츠 모니터링 권한 ( 댓글 삭제, 컨텐츠 상태 변경 )
    USER,     // 일반 사용자 권한 (컨텐츠 읽기 및 댓글 작성)
    GUEST     // 손님 권한 (only read 또는 회원가입 페이지 유도)
    ;

    @Override
    public String getAuthority() {
        return name(); // Enum 필드를 권한명으로 반환
    }
}
