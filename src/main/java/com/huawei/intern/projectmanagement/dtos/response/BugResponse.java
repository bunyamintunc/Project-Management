package com.huawei.intern.projectmanagement.dtos.response;

import com.huawei.intern.projectmanagement.models.Comment;
import com.huawei.intern.projectmanagement.models.Ticket;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class BugResponse {

    String name;
    String description;
    UserResponse userResponse;
    String status;
    boolean isClose;
    Ticket ticket;
}
