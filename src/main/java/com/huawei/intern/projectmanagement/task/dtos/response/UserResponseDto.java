package com.huawei.intern.projectmanagement.task.dtos.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "User Response Dto model documentation",description = "Model")
public class UserResponseDto {

    Long id;
    String name;
    String surName;
    String imageUrl;
}
