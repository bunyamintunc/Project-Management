package com.huawei.intern.projectmanagement.task.controllers;

import com.huawei.intern.projectmanagement.task.models.Role;
import com.huawei.intern.projectmanagement.task.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Api(value = "Role Api Documantation")
public class RoleController {

    private  final RoleService roleService;

    @PostMapping
    @ApiOperation(value = "Create new role method")
    public Role createRole(Role role){
        return roleService.saveRole(role);
    }

    @GetMapping("/getall")
    @ApiOperation(value = "Get all role method")
    public ResponseEntity<List<Role>> getRoles() throws Exception {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getbyid/{roleId}")
    @ApiOperation(value = "Get one role by id method")
    public ResponseEntity<Role> getRoleById(@PathVariable("roleId") Long roleId) throws Exception {
        return  new ResponseEntity<>(roleService.findById(roleId).orElse(null),HttpStatus.OK);
    }

    @GetMapping("/getbyname/{name}")
    @ApiOperation(value = "Get role by role name method")
    public ResponseEntity<Role> getRoleByName(@PathVariable("name") String name) throws Exception {
        return new ResponseEntity<>(roleService.findByName(name),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roleId}")
    @ApiOperation(value = "Delete ricket by id  method")
    public void deleteRole(@PathVariable("roleId") Long roleId) throws Exception {
        roleService.deleteById(roleId);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
