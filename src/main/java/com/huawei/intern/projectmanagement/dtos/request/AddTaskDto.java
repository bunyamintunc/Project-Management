package com.huawei.intern.projectmanagement.dtos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddTaskDto {

    private String name;
    private String desription;
    private Long userId;
    private Long ticketId;
    private Long ProjectId;

}
