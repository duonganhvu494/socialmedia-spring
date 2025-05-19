package com.vm.socialmedia.dto.request;

import com.vm.socialmedia.enums.Gender;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    private String username;
    private String fullname;
    private String profilePicture;
    private String backgroundPicture;
    private String about;
    private String phone;
    private Date birth;
    private Gender gender;
}
