package com.study.blog.domain.admin.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class UpdateAdminRequest {

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 6, message = "비밀번호는 최소 6자리 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "관리자명을 입력해 주세요.")
    private String adminName;

    public UpdateAdminRequest(String password, String adminName) {
        this.password = password;
        this.adminName = adminName;
    }
}
