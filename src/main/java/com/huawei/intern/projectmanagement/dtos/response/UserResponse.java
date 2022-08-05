package com.huawei.intern.projectmanagement.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    Long id;
    String username;
    String imageUrl;
    String email;
}
