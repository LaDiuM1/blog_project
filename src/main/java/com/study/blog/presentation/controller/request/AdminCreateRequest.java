package com.study.blog.presentation.controller.request;

import com.study.blog.business.admin.data.AdminCreateData;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class AdminCreateRequest {

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식이 아닙니다.")
    private final String email;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 6, message = "비밀번호는 최소 6자리 이상이어야 합니다.")
    private final String password;

    @NotBlank(message = "관리자명을 입력해 주세요.")
    private final String adminName;

    public AdminCreateRequest(String email, String password, String adminName) {
        this.email = email;
        this.password = password;
        this.adminName = adminName;
    }

    public AdminCreateData toData(){
        return new AdminCreateData(this.email, this.password, this.adminName);
    }
}
