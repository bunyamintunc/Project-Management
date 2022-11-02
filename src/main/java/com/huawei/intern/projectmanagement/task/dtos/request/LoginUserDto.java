package com.huawei.intern.projectmanagement.task.dtos.request;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Login  User model documentation",description = "Model")
public class LoginUserDto {

    String username;
    String password;

}
