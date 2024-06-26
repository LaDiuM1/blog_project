package com.study.blog.presentation.controller.request;

import com.study.blog.business.post.data.PostCreateData;
import com.study.blog.presentation.controller.request.validate.TagNamesValid;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;

@Getter
@AllArgsConstructor
public class PostCreateRequest {

    @NotNull(message = "카테고리 id 값이 null 입니다.")
    @Min(value = 1, message = "카테고리 id는 정수 1 이상 요청바랍니다.")
    private Long categoryId;

    @NotBlank(message = "글 제목을 입력해 주세요.")
    @Size(max = 100, message = "글 제목은 100글자를 넘을 수 없습니다.")
    private String title;

    @NotBlank(message = "글 내용을 입력해 주세요.")
    private String content;

    @TagNamesValid(message = "태그는 공백으로만 이루어질 수 없습니다.")
    private HashSet<String> tagNames;

    public PostCreateData toData(){
        return new PostCreateData(
                this.categoryId,
                this.title,
                this.content,
                this.tagNames
        );
    }

}
