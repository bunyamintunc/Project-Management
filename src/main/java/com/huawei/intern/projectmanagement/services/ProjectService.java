package com.huawei.intern.projectmanagement.services;

import com.huawei.intern.projectmanagement.models.Project;
import com.huawei.intern.projectmanagement.repositories.ProjectRepository;
import com.huawei.intern.projectmanagement.services.abstracts.IProjectService;
import com.huawei.intern.projectmanagement.services.generics.impl.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ProjectService extends GenericService<Project> implements IProjectService {

    private final ProjectRepository projectRepository;
    private final  UserService userService;
    private TicketService ticketService;



    public Project saveProject(Project project){
        System.out.println("service ---->"+project.getName());
        Project project1 = Project.builder().name(project.getName()).description(project.getDescription()).tasks(new HashSet<>()).build();
        return projectRepository.save(project1);
    }


}
