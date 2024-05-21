package com.study.blog.presentation.controller.request;

import com.study.blog.business.category.data.CategoryUpdateData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CategoryUpdateRequest {

    @NotBlank(message = "카테고리 이름을 입력해 주세요.")
    @Size(max = 100, message = "카테고리 이름은 100글자를 넘을 수 없습니다.")
    private final String name;

    @NotBlank(message = "카테고리 설명을 입력해 주세요.")
    @Size(max = 255, message = "카테고리 설명은 255글자 이내로 입력해 주세요.")
    private final String description;

    public CategoryUpdateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryUpdateData toData() {
        return new CategoryUpdateData(
                this.name,
                this.description
        );
    }
}
