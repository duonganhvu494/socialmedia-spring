package com.vm.socialmedia.dto.response;

import com.vm.socialmedia.enums.Gender;
import com.vm.socialmedia.enums.Role;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String fullname;
    private String profilePicture;
    private String backgroundPicture;
    private String about;
    private List<String> friendIds;

    private FriendRequests friendRequests;
    private List<SavedPost> savedPosts;

    private String email;
    private String phone;
    private Date birth;
    private Gender gender;
    private Role role;

    private Date createdAt;
    private Date updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FriendRequests {
        private List<String> sentUserIds;
        private List<String> receivedUserIds;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SavedPost {
        private String postId;
        private Date savedAt;
    }
}
