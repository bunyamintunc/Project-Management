package com.huawei.intern.projectmanagement.bugtracking.dtos.response;

import com.huawei.intern.projectmanagement.task.dtos.response.UserResponse;
import com.huawei.intern.projectmanagement.task.models.Ticket;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "Bug Response model documentation",description = "Model")
public class BugResponse {

    @ApiModelProperty(value = "name for  Bug")
    String name;

    @ApiModelProperty(value = "description for  Bug")
    String description;

    @ApiModelProperty(value = "user response for  Bug")
    UserResponse userResponse;

    @ApiModelProperty(value = "status  for  Bug")
    String status;

    @ApiModelProperty(value = "status code  for  Bug")
    boolean isClose;

    @ApiModelProperty(value = "ticket for  Bug")
    Ticket ticket;
}
