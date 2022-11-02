package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.task.models.Project;
import com.huawei.intern.projectmanagement.task.repositories.ProjectRepository;
import com.huawei.intern.projectmanagement.task.services.abstracts.IProjectService;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class ProjectService extends GenericService<Project> implements IProjectService {

    private  ProjectRepository projectRepository;




    public Project saveProject(Project project){
        System.out.println("service ---->"+project.getName());
        Project project1 = Project.builder().name(project.getName()).description(project.getDescription()).tasks(new HashSet<>()).build();
        return projectRepository.save(project1);
    }


}
