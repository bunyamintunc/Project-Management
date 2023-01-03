package com.huawei.intern.projectmanagement.task.dtos.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Ticket  for Task model documentation",description = "adding ticket for task models")
public class TicketForTaskDto {

    private Long  taskId;
    private Long ticketId;
}
