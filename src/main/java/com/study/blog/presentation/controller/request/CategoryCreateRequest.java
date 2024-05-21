package com.study.blog.presentation.controller.request;

import com.study.blog.business.category.data.CategoryCreateData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CategoryCreateRequest {

    @NotBlank(message = "카테고리 이름을 입력해 주세요.")
    @Size(max = 100, message = "카테고리 이름은 100글자를 넘을 수 없습니다.")
    private final String name;

    @NotBlank(message = "카테고리 설명을 입력해 주세요.")
    @Size(max = 255, message = "카테고리 설명은 255글자 이내로 입력해 주세요.")
    private final String description;

    public CategoryCreateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryCreateData toData(){
        return new CategoryCreateData(this.name, this.description);
    }
}
