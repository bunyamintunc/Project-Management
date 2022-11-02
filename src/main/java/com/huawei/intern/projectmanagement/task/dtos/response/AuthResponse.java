package com.huawei.intern.projectmanagement.task.dtos.response;

import com.huawei.intern.projectmanagement.task.models.Role;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "Auth Response model documentation",description = "Model")
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
