package com.huawei.intern.projectmanagement.task.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
@Builder
@EqualsAndHashCode
@ApiModel(value = "User api model documentation",description = "Model")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @ApiModelProperty(value = "User objesi için benzersiz id")
    private Long id;
    @ApiModelProperty(value = "User objesi kullanici adi")
    private String username;
    @ApiModelProperty(value = "User objesi ad")
    private String name;
    @ApiModelProperty(value = "User objesi soyadi")
    private String surName;
    @ApiModelProperty(value = "User objesi için email adresi")
    private String email;
    @ApiModelProperty(value = "User objesi için fotoğraf url'si")
    private String imageUrl;
    @ApiModelProperty(value = "User objesi için şifre")
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    @ApiModelProperty(value = "User objesine atanan task listesi")
    private Set<Task> tasks ;
    @ManyToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "User için rol listesi")
    Set<Role> roles;
}
