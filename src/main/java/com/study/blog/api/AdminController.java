package com.study.blog.api;

import com.study.blog.domain.admin.request.AdminLoginRequest;
import com.study.blog.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> createPost(@RequestBody @Valid AdminLoginRequest adminLoginRequest) {
        Object loginData = adminService.login(adminLoginRequest);

        return ResponseEntity.ok("loginTest");
    }

}
