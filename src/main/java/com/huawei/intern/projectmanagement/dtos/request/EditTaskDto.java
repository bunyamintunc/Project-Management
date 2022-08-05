package com.huawei.intern.projectmanagement.dtos.request;

import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.models.User;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Data
public class EditTaskDto {

    private Long taskId;
    private String desription;
    private Long ticketId;
    private Long userId;

}
