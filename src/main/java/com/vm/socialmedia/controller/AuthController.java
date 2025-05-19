package com.vm.socialmedia.controller;

import com.vm.socialmedia.dto.request.AuthRequest;
import com.vm.socialmedia.dto.request.UserCreateRequest;
import com.vm.socialmedia.dto.response.ApiResponse;
import com.vm.socialmedia.dto.response.AuthResponse;
import com.vm.socialmedia.dto.response.UserResponse;
import com.vm.socialmedia.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreateRequest userCreationRequest){
        return ApiResponse.<UserResponse>builder()
                .EC(0)
                .EM("Đăng ký thành công")
                .result(authService.createUser(userCreationRequest))
                .build();
    }

    @PostMapping("/sign-in")
    public ApiResponse<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.login(authRequest.getUsername(), authRequest.getPassword());

        return ApiResponse.<AuthResponse>builder()
                .EC(0)
                .EM("Đăng nhập thành công")
                .result(authResponse)
                .build();
    }

}
