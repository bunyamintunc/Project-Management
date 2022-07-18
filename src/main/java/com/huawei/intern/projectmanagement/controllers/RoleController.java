package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.models.Role;
import com.huawei.intern.projectmanagement.models.User;
import com.huawei.intern.projectmanagement.services.RoleService;
import com.huawei.intern.projectmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private  final RoleService roleService;

    @PostMapping
    public Role createRole(Role role){
        return roleService.saveRole(role);
    }

    @GetMapping
    public List<Role> listRole(){
        return roleService.getAllRoles();
    }
}
