package com.study.blog.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 자식 클래스에 컴포넌트 등록
@EntityListeners( AuditingEntityListener.class )
public class BaseTime {

    @CreatedDate
    protected LocalDateTime createDate;
    @LastModifiedDate
    protected LocalDateTime updateDate;
}