package com.huawei.intern.projectmanagement.dtos.request;

import lombok.Data;

@Data
public class AddBug {

    String name;
    String description;
    Long userId;
    String status;

}
