package com.huawei.intern.projectmanagement.task.dtos.request;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel(value = "Edit Task model documentation",description = "Model")
@Builder
public class EditTaskDto {

    private Long taskId;
    private String desription;
    private Long ticketId;
    private Long userId;

}
