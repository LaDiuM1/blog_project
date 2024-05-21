package com.study.blog.presentation.controller;

import com.study.blog.business.admin.AdminService;
import com.study.blog.business.admin.dto.AdminListDto;
import com.study.blog.presentation.controller.request.AdminCreateRequest;
import com.study.blog.presentation.controller.request.AdminSearchRequest;
import com.study.blog.presentation.controller.request.AdminUpdateRequest;
import com.study.blog.presentation.controller.response.CreatedResponse;
import com.study.blog.presentation.controller.response.SuccessfulResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<AdminListDto>> searchAdminList(@ModelAttribute AdminSearchRequest request,
                                                              Pageable pageable) {
        Page<AdminListDto> searchAdminList = adminService.searchAdminList(request.toData(), pageable);

        return SuccessfulResponse.response(searchAdminList);
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> registerAdmin(@RequestBody @Valid AdminCreateRequest adminCreateRequest) {
        Long createdAdminId = adminService.registerAdmin(adminCreateRequest.toData());

        return CreatedResponse.response("Admin", createdAdminId);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Void> updateAdmin(@PathVariable("adminId") Long adminId,
                                            @RequestBody @Valid AdminUpdateRequest adminUpdateRequest) {

        adminService.updateAdmin(adminId, adminUpdateRequest.toData());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/status/{adminId}")
    public ResponseEntity<Void> switchAdminStatus(@PathVariable("adminId") Long adminId) {
        adminService.switchAdminStatus(adminId);

        return ResponseEntity.ok().build();
    }

}
