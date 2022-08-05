package com.huawei.intern.projectmanagement.dtos.request;

import lombok.Data;

@Data
public class UserDto {

    Long id;
    String username;
    String imageUrl;
    String email;
    String password;
}
