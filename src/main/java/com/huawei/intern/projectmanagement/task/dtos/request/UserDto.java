package com.huawei.intern.projectmanagement.task.dtos.request;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "User Request model documentation",description = "Model")
@Builder
@EqualsAndHashCode
public class UserDto {

    Long id;
    String username;
    String imageUrl;
    String email;
    String password;
}
