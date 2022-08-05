package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.dtos.request.AddRoleRequest;
import com.huawei.intern.projectmanagement.dtos.request.UserDto;
import com.huawei.intern.projectmanagement.models.User;
import com.huawei.intern.projectmanagement.services.UserService;
import com.huawei.intern.projectmanagement.services.generics.impl.GenericService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController  {

    private  final UserService userService;


    @GetMapping("/getall")
    public List<User> getUsers() throws Exception {
       return userService.findAll();

    }

    @PostMapping("/create")
    public User createUser(@RequestBody  User user){
        System.out.println("password" +user.getPassword());
        return userService.saveUser(user);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<User> listUsers(){
        return userService.getAllUsers();
    }


    @PatchMapping("/addrole")
    public void addRole(@RequestBody AddRoleRequest addRoleRequest) throws Exception {
        userService.addRoleToUser(addRoleRequest.getUserId(),addRoleRequest.getRoleId());
    }

    @GetMapping("/getbyid/{userId}")
    public  User getUserById(@PathVariable("userId") Long userId) throws Exception {
        return userService.findById(userId).get();
    }

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable("userId") Long userId) throws Exception {
        System.out.println("userId-----> "+ userId);
        userService.deleteById(userId);
    }

    @PutMapping("/edit")
    public User editUser(@RequestBody UserDto dto){
        return userService.editUser(dto);
    }



}
