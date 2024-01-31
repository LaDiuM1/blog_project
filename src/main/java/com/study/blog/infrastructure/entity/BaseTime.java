package com.study.blog.infrastructure.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 생성 시간, 수정 시간 상속
@EntityListeners( AuditingEntityListener.class )
public class BaseTime {

    @CreatedDate
    protected LocalDateTime createDate;
    @LastModifiedDate
    protected LocalDateTime updateDate;
}