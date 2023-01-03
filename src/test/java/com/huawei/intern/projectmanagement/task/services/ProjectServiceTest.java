package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import com.huawei.intern.projectmanagement.task.models.Project;
import com.huawei.intern.projectmanagement.task.models.Role;
import com.huawei.intern.projectmanagement.task.models.Ticket;
import com.huawei.intern.projectmanagement.task.repositories.ProjectRepository;
import com.huawei.intern.projectmanagement.task.repositories.RoleRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import  org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private GenericService<Project> genericService;


    @Mock
    protected GenericRepository<Project> genericRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.genericService = new GenericService<Project>( genericRepository);

    }

    @Test
    public  void it_should_create_project(){
        Project testProject = Project.builder().id(1l).tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

       when(projectRepository.save(any())).thenReturn(testProject);
       Project result = projectService.saveProject(testProject);


       then(result.getId()).isEqualTo(testProject.getId());
    }

    @Test
    public void it_should_getall() throws Exception {
        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();
        List<Project> projectList = new ArrayList<>();
        projectList.add(testProject);

        when(genericRepository.findAll()).thenReturn(projectList);

        List<Project> result = projectService.findAll();

        then(result.size()).isEqualTo(1);
    }

    @Test
    public void it_should_findbyid() throws Exception {
        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

        when(genericRepository.findById(1L)).thenReturn(Optional.ofNullable(testProject));

        Optional<Project> result = projectService.findById(1L);

        then(result).isNotNull();
        then(result.get().getName()).isEqualTo(testProject.getName());
    }

    @Test
    public void it_should_delete_by_id() throws Exception {

        doNothing().when(genericRepository).deleteById(1L);
        projectService.deleteById(1L);
    }

    @Test
    public void it_should_find_by_name() throws Exception {
        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

        when(genericRepository.findByName(any())).thenReturn(testProject);

        Project result = projectService.findByName("deneme");

        then(result).isNotNull();
        then(result.getName()).isEqualTo(testProject.getName());
    }

}