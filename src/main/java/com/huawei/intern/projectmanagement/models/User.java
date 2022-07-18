package com.huawei.intern.projectmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String surName;
    private String email;
    private String imageUrl;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;


}
