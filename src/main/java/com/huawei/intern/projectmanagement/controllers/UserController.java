package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.models.User;
import com.huawei.intern.projectmanagement.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private  final UserService userService;

    @PostMapping
    public User createUser(@RequestBody  User user){
        return userService.saveUser(user);
    }

    @GetMapping

    public List<User> listUsers(){
        return userService.getAllUsers();
    }


    @PatchMapping
    public void addRole(@RequestBody AddRoleRequest addRoleRequest){
        userService.addRoleToUser(addRoleRequest.getUserName(),addRoleRequest.getRoleName());
    }

    @Data
     class AddRoleRequest{

        private String userName;
        private String roleName;

    }
}
