package com.study.blog.presentation.controller;

import com.study.blog.presentation.controller.request.CreateAdminRequest;
import com.study.blog.presentation.controller.request.SearchAdminRequest;
import com.study.blog.presentation.controller.request.UpdateAdminRequest;
import com.study.blog.presentation.controller.response.AdminListResponse;
import com.study.blog.business.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<Page<AdminListResponse>> searchPostList(
            @RequestParam(required = false) String searchEmail,
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false) Boolean searchStatus,
            Pageable pageable) {
        SearchAdminRequest searchAdminRequest = new SearchAdminRequest(searchEmail, searchName, searchStatus);

        Page<AdminListResponse> searchAdminList = adminService.searchAdminList(searchAdminRequest, pageable);

        return ResponseEntity.ok(searchAdminList);
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> registerAdmin(@Valid @RequestBody CreateAdminRequest createAdminRequest) {
        Long createdAdminId = adminService.registerAdmin(createAdminRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("createdAdminId", createdAdminId));
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Void> updateAdmin(
            @PathVariable("adminId") Long adminId,
            @RequestBody @Valid UpdateAdminRequest updateAdminRequest) {

        adminService.updateAdmin(adminId, updateAdminRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/status/{adminId}")
    public ResponseEntity<Void> switchAdminStatus(@PathVariable("adminId") Long adminId) {
        adminService.switchAdminStatus(adminId);

        return ResponseEntity.ok().build();
    }

}
