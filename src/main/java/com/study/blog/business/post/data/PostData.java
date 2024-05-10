package com.study.blog.business.post.data;

import com.study.blog.presentation.controller.request.validate.TagNamesValid;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;

@Getter
public class PostData {
    private Long categoryId;
    private String title;
    private String content;
    private HashSet<String> tagNames;

    public PostData(Long categoryId, String title, String content, HashSet<String> tagNames) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.tagNames = tagNames;
    }
}
