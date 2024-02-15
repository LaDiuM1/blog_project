package com.study.blog.domain.admin.category.request;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class UpdateCategoryStatusRequest {

    @NotNull(message = "카테고리 id값이 null입니다.")
    @Min(value = 1, message = "카테고리 id는 정수 1 이상 요청바랍니다.")
    private Long id;

    @NotNull(message = "카테고리 상태값이 null입니다.")
    private Boolean status;
}
