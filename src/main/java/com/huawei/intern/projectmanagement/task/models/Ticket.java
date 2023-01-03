package com.huawei.intern.projectmanagement.task.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Ticket api model documentation",description = "Model")
@EqualsAndHashCode
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "uniqe id for  ticket")
    private Long id;

    @ApiModelProperty(value = "name for  ticket")
    private String name;

    @ApiModelProperty(value = "description  for  ticket")
    private String description;

}
