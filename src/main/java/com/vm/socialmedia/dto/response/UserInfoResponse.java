package com.vm.socialmedia.dto.response;

import com.vm.socialmedia.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {
    private String id;
    private String username;
    private String email;
    private Role role;
}
