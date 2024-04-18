package com.study.blog.api;

import com.study.blog.domain.admin.request.SearchAdminRequest;
import com.study.blog.domain.admin.request.CreateAdminRequest;
import com.study.blog.domain.admin.request.UpdateAdminRequest;
import com.study.blog.domain.admin.response.AdminListResponse;
import com.study.blog.domain.admin.service.AdminService;
import com.study.blog.domain.post.response.PostListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/admins")
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

}
