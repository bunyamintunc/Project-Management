package com.huawei.intern.projectmanagement.task.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huawei.intern.projectmanagement.task.models.Task;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Project api model documentation",description = "Model")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    @ApiModelProperty(value = "id for  project")
    private Long id;

    @ApiModelProperty(value = "name for  project")
    private String name;

    @ApiModelProperty(value = "description for  project")
    private String description;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    @JsonIgnore
    @ApiModelProperty(value = "task list for  project")
    private Set<Task> tasks;
}
