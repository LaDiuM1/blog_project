package com.study.blog.api;

import com.study.blog.domain.admin.request.AdminLoginRequest;
import com.study.blog.domain.admin.request.CreateAdminRequest;
import com.study.blog.domain.admin.request.UpdateAdminRequest;
import com.study.blog.domain.admin.service.AdminService;
import com.study.blog.domain.post.request.CreatePostRequest;
import com.study.blog.domain.post.request.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> createPost(@RequestBody @Valid AdminLoginRequest adminLoginRequest) {
        Object loginData = adminService.login(adminLoginRequest);

        return ResponseEntity.ok("loginTest");
    }

    @PostMapping
    public ResponseEntity<Long> registerAdmin(@Valid @RequestBody CreateAdminRequest createAdminRequest) {
        Long createdPostId = adminService.registerAdmin(createAdminRequest);

        return new ResponseEntity<>(createdPostId, HttpStatus.CREATED);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Void> updateAdmin(
            @PathVariable("adminId") Long adminId,
            @RequestBody @Valid UpdateAdminRequest updateAdminRequest) {

        adminService.updateAdmin(adminId, updateAdminRequest);

        return ResponseEntity.ok().build();
    }

}
