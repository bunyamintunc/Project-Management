package com.huawei.intern.projectmanagement.task.dtos.response;

import com.huawei.intern.projectmanagement.task.dtos.response.UserResponseDto;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "Task Response Dto model documentation",description = "Model")
public class TaskResponseDto {

    Long id;
    String name;
    String desription;
    boolean isClose;
    UserResponseDto user;
}
