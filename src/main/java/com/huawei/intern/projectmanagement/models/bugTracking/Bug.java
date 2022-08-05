package com.huawei.intern.projectmanagement.models.bugTracking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huawei.intern.projectmanagement.core.Item;
import com.huawei.intern.projectmanagement.models.Comment;
import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
