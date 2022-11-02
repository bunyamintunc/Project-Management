package com.huawei.intern.projectmanagement.task.dtos.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "User Response model documentation",description = "Model")
public class UserResponse {

    Long id;
    String username;
    String imageUrl;
    String email;
}
