package com.huawei.intern.projectmanagement.task.dtos.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Add Role model documentation",description = "Model")
public class AddRoleRequest{

    private Long userId;
    private Long roleId;

}