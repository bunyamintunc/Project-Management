package com.huawei.intern.projectmanagement.task.controllers;

import com.huawei.intern.projectmanagement.task.dtos.request.AddRoleRequest;
import com.huawei.intern.projectmanagement.task.dtos.request.UserDto;
import com.huawei.intern.projectmanagement.task.models.User;
import com.huawei.intern.projectmanagement.task.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Api(value = "User Api Documantation")
public class UserController  {

    private  final UserService userService;


    @GetMapping("/getall")
    @ApiOperation(value = "Get all users method")
    public List<User> getUsers() throws Exception {
       return userService.findAll();

    }

    @PostMapping("/create")
    @ApiOperation(value = "Create new user method")
    public User createUser(@RequestBody  User user){
        System.out.println("password" +user.getPassword());
        return userService.saveUser(user);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Get all users method")
    public List<User> listUsers(){
        return userService.getAllUsers();
    }


    @PatchMapping("/addrole")
    @ApiOperation(value = "adding role for users method")
    public void addRole(@RequestBody AddRoleRequest addRoleRequest) throws Exception {
        userService.addRoleToUser(addRoleRequest.getUserId(),addRoleRequest.getRoleId());
    }

    @GetMapping("/getbyid/{userId}")
    @ApiOperation(value = "Get one user method")
    public  User getUserById(@PathVariable("userId") Long userId) throws Exception {
        return userService.findById(userId).get();
    }

    @DeleteMapping("/delete/{userId}")
    @ApiOperation(value = "Delete user by id method")
    public void delete(@PathVariable("userId") Long userId) throws Exception {
        System.out.println("userId-----> "+ userId);
        userService.deleteById(userId);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "edit user method")
    public User editUser(@RequestBody UserDto dto){
        return userService.editUser(dto);
    }



}
