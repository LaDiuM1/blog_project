package com.study.blog.api;

import com.study.blog.domain.user.request.LoginRequest;
import com.study.blog.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> createPost(@RequestBody @Valid LoginRequest loginRequest) {
        Object loginData = adminService.login(loginRequest);

        return ResponseEntity.ok("loginTest");
    }


}
