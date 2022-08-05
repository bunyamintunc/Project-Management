package com.huawei.intern.projectmanagement.dtos.response;

import com.huawei.intern.projectmanagement.models.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class AuthResponse {
    String message;
    Long userId;
    String username;
    String name;
    String surname;
    String email;
    String imageUrl;
    List<Role> roles;
}
