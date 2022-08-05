package com.huawei.intern.projectmanagement.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huawei.intern.projectmanagement.models.Project;
import com.huawei.intern.projectmanagement.models.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class TaskResponse {

    private Long id;
    private String name;
    private String desription;
    private boolean isClose;
    private String status;

    UserResponse userResponse;
}
