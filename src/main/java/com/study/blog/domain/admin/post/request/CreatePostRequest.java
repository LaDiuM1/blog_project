package com.study.blog.domain.admin.post.request;

import com.study.blog.domain.admin.post.request.validate.TagNameSetValid;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
public class CreatePostRequest {

    @NotBlank(message = "글 제목을 입력해 주세요.&20101")
    @Size(max = 100, message = "글 제목은 100글자를 넘을 수 없습니다.&20102")
    private String title;

    @NotBlank(message = "글 내용을 입력해 주세요.&20103")
    private String content;

    @TagNameSetValid(message = "태그는 공백으로만 이루어 질 수 없습니다.")
    private HashSet<String> tagNameSet;

}
