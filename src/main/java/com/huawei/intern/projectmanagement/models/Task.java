package com.huawei.intern.projectmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huawei.intern.projectmanagement.core.Item;
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
public class Task extends Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    @JsonIgnore
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comment> comments;


}
