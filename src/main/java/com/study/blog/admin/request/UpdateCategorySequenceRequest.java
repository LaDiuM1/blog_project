package com.study.blog.admin.request;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Getter
public class UpdateCategorySequenceRequest {

    @NotNull(message = "카테고리 id값이 null입니다.&20201")
    @Min(value = 1, message = "카테고리 id는 정수 1 이상 요청바랍니다.&20202")
    private Long id;

    @NotNull(message = "카테고리 순서 번호가 null입니다.&20203")
    @Min(value = 1, message = "카테고리 순서 번호는 정수 1 이상 요청바랍니다.&20204")
    private Integer sequence;

}
