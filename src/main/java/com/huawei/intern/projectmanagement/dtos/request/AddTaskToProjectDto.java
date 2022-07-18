package com.huawei.intern.projectmanagement.dtos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddTaskToProjectDto {

    private String projectName;
    private String name;
    private String desription;
    private String imageUrl;
    private String userName;
    private  String ticket;
}
