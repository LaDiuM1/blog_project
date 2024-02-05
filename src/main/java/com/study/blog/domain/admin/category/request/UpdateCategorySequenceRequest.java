package com.study.blog.domain.admin.category.request;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeSet;

@Getter
public class UpdateCategorySequenceRequest {

    LinkedHashSet<Long> idSet;

}
