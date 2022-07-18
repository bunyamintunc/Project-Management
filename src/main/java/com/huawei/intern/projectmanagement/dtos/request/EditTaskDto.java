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

    private String name;
    private String desription;
    private String ticketName;
    private String userName;

}
