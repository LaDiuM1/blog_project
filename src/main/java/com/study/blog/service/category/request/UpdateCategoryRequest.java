package com.study.blog.service.category.request;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UpdateCategoryRequest {

    @NotNull(message = "카테고리 id 값이 null 입니다.")
    @Min(value = 1, message = "카테고리 id는 정수 1 이상 요청바랍니다.")
    private Long id;

    @NotBlank(message = "카테고리 이름을 입력해 주세요.")
    @Size(max = 100, message = "카테고리 이름은 100글자를 넘을 수 없습니다.")
    private String name;

    @NotBlank(message = "카테고리 설명을 입력해 주세요.")
    @Size(max = 255, message = "카테고리 설명은 255글자 이내로 입력해 주세요.")
    private String description;

    public UpdateCategoryRequest(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
