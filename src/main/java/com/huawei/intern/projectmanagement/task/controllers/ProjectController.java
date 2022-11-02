package com.huawei.intern.projectmanagement.task.controllers;

import com.huawei.intern.projectmanagement.task.models.Project;
import com.huawei.intern.projectmanagement.task.services.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Api(value = "Project Api Documantation")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/getall")
    @ApiOperation(value = "Get all project method")
    public List<Project>getTasks() throws Exception {
        return projectService.findAll();
    }

    @GetMapping("/getbyid/{projectId}")
    @ApiOperation(value = "Get tasks by project id method")
    public ResponseEntity<Project> getTaskById(@PathVariable("projectId") Long projectId) throws Exception {
        return new ResponseEntity<Project>(projectService.findById(projectId).orElse(null),HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create new project method")
    public Project createProject(@RequestBody Project project){

        return projectService.saveProject(project);
    }

    @GetMapping("/getbyid/{name}")
    @ApiOperation(value = "Get one project bt name method")
    public Project getProjectByName(@PathVariable("name") String name) throws Exception {
        return projectService.findByName(name);
    }

    @DeleteMapping("/delete/{projectId}")
    @ApiOperation(value = "Delete project method")
    public void deleteProject(@PathVariable("projectId")Long projetId) throws Exception {
        projectService.deleteById(projetId);
    }



}
