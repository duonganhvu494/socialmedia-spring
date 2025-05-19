package com.vm.socialmedia.model;

import com.vm.socialmedia.enums.Gender;
import com.vm.socialmedia.enums.Role;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private String username;
    private String fullname;
    private String password;
    private String profilePicture;
    private String backgroundPicture;
    private String about;
    private List<String> friendIds;

    @Builder.Default
    private FriendRequests friendRequests = new FriendRequests();

    @Builder.Default
    private List<SavedPost> savedPosts = new ArrayList<>();

    private String email;
    private String phone;
    private Date birth;
    private Gender gender;
    private Role role;

    @CreatedDate
    @Field("created_at")
    private Date createdAt;

    @LastModifiedDate
    @Field("updated_at")
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
        private Date savedAt = new Date();
    }
}
