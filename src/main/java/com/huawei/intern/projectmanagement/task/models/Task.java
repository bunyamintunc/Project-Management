package com.huawei.intern.projectmanagement.task.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huawei.intern.projectmanagement.core.Item;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(value = "Task api model documentation",description = "Model")
@EqualsAndHashCode
public class Task extends Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id for  task")
    private Long id;



    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    @JsonIgnore
    @ApiModelProperty(value = "project  for  task")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @ApiModelProperty(value = "user for  task")
    User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @ApiModelProperty(value = "ticket list  for  task")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    @JsonIgnore
    @ApiModelProperty(value = "comment list for  task")
    private Set<Comment> comments;


}
