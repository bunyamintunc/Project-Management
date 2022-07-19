package com.huawei.intern.projectmanagement.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String desription;
    private String imageUrl;

    @ManyToOne
    User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    private Set<Comment> comments;

}
