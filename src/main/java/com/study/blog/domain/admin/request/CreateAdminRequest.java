package com.study.blog.domain.admin.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CreateAdminRequest {

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 6, message = "비밀번호는 최소 6자리 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "관리자명을 입력해 주세요.")
    private String adminName;

    public CreateAdminRequest(String email, String password, String adminName) {
        this.email = email;
        this.password = password;
        this.adminName = adminName;
    }
}
