package com.study.blog.presentation.controller.request;

import com.study.blog.business.admin.data.AdminUpdateData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class AdminUpdateRequest {

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 6, message = "비밀번호는 최소 6자리 이상이어야 합니다.")
    private final String password;

    @NotBlank(message = "관리자명을 입력해 주세요.")
    private final String adminName;

    public AdminUpdateRequest(String password, String adminName) {
        this.password = password;
        this.adminName = adminName;
    }

    public AdminUpdateData toData() {
        return new AdminUpdateData(this.password, this.adminName);
    }
}
