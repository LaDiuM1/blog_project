package com.study.blog.controller.post.request;

import com.study.blog.controller.post.request.validate.TagNamesValid;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;

@Getter
public class UpdatePostRequest {
    @NotNull(message = "게시글 id값이 null입니다.")
    @Min(value = 1, message = "게시글 id는 정수 1 이상 요청바랍니다.")
    private Long id;
    
    @NotNull(message = "카테고리 id값이 null입니다.")
    @Min(value = 1, message = "카테고리 id는 정수 1 이상 요청바랍니다.")
    private Long categoryId;

    @NotBlank(message = "글 제목을 입력해 주세요.")
    @Size(max = 100, message = "글 제목은 100글자를 넘을 수 없습니다.")
    private String title;

    @NotBlank(message = "글 내용을 입력해 주세요.")
    private String content;

    @TagNamesValid(message = "태그는 공백으로만 이루어질 수 없습니다.")
    private HashSet<String> tagNames;
}
