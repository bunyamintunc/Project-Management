package com.huawei.intern.projectmanagement.bugtracking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huawei.intern.projectmanagement.core.Item;
import com.huawei.intern.projectmanagement.task.models.Ticket;
import com.huawei.intern.projectmanagement.task.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bug extends Item {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ticket ticket;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JsonIgnore
    private User user;


}
