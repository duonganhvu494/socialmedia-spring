package com.vm.socialmedia.service;

import com.vm.socialmedia.dto.request.UserCreateRequest;
import com.vm.socialmedia.dto.response.AuthResponse;
import com.vm.socialmedia.dto.response.UserInfoResponse;
import com.vm.socialmedia.dto.response.UserResponse;
import com.vm.socialmedia.exception.AppException;
import com.vm.socialmedia.exception.ErrorCode;
import com.vm.socialmedia.mapper.UserMapper;
import com.vm.socialmedia.model.User;
import com.vm.socialmedia.repository.UserRepository;
import com.vm.socialmedia.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private final JwtUtil jwtUtil;

    public UserResponse createUser(UserCreateRequest UserCreateData){
        if (userRepository.existsByUsername(UserCreateData.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(UserCreateData);
        user.setPassword(passwordEncoder.encode(UserCreateData.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public AuthResponse login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NON_EXISTED));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .authenticated(true)
                .accessToken(accessToken)
                .user(UserInfoResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .build();
    }
}
