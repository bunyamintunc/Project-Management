package com.huawei.intern.projectmanagement.task.dtos.request;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@ApiModel(value = "Add  Task model documentation",description = "Model")
@EqualsAndHashCode
public class AddTaskDto {

    private String name;
    private String desription;
    private Long userId;
    private Long ticketId;
    private Long ProjectId;

}
