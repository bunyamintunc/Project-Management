package com.huawei.intern.projectmanagement.task.dtos.response;

import com.huawei.intern.projectmanagement.task.dtos.response.UserResponse;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "Task Response model documentation",description = "Model")
public class TaskResponse {

    private Long id;
    private String name;
    private String desription;
    private boolean isClose;
    private String status;

    UserResponse userResponse;
}
