package com.vm.socialmedia.mapper;


import com.vm.socialmedia.dto.request.UserCreateRequest;
import com.vm.socialmedia.dto.request.UserUpdateRequest;
import com.vm.socialmedia.dto.response.UserResponse;
import com.vm.socialmedia.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    default UserResponse toUserResponse(User user) {
        if (user == null) return null;

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullname(user.getFullname())
                .profilePicture(user.getProfilePicture())
                .backgroundPicture(user.getBackgroundPicture())
                .about(user.getAbout())
                .friendIds(user.getFriendIds())
                .friendRequests(
                        new UserResponse.FriendRequests(
                                user.getFriendRequests().getSentUserIds(),
                                user.getFriendRequests().getReceivedUserIds()
                        )
                )
                .savedPosts(
                        user.getSavedPosts().stream()
                                .map(p -> new UserResponse.SavedPost(p.getPostId(), p.getSavedAt()))
                                .toList()
                )
                .email(user.getEmail())
                .phone(user.getPhone())
                .birth(user.getBirth())
                .gender(user.getGender())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

}
