package com.huawei.intern.projectmanagement.bugtracking.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "Add bug model documentation",description = "Model")

public class AddBug {

    @ApiModelProperty(value = "name for  Bug")
    String name;

    @ApiModelProperty(value = "description  for  Bug")
    String description;

    @ApiModelProperty(value = "user id  for  Bug ")
    Long userId;

    @ApiModelProperty(value = "status for  Bug")
    String status;

}
