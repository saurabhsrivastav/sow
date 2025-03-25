package com.ems.sow.dto;

import com.ems.sow.model.User;
import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String customerId;
    private String role;

    public User toUser(byte[] image) {
        return User.builder()
                .name(name)
                .userName(email)
                .password(password)
                .customerId(customerId)
                .role(role)
                .image(image)
                .build();
    }
}
