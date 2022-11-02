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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "Comment api model documentation",description = "Model")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id for  comment")
    private Long id;

    @ApiModelProperty(value = "name for  comment")
    private String name;

    @ApiModelProperty(value = "file name for  comment")
    private String fileType;

    @Lob
    @ApiModelProperty(value = "file data  for  comment")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "task_id",nullable = false)
    @JsonIgnore
    @ApiModelProperty(value = "task for  comment")
    private Task task;

}
