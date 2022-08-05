package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.models.Role;
import com.huawei.intern.projectmanagement.models.User;
import com.huawei.intern.projectmanagement.services.RoleService;
import com.huawei.intern.projectmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getall")
    public ResponseEntity<List<Role>> getRoles() throws Exception {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getbyid/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable("roleId") Long roleId) throws Exception {
        return  new ResponseEntity<>(roleService.findById(roleId).orElse(null),HttpStatus.OK);
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable("name") String name) throws Exception {
        return new ResponseEntity<>(roleService.findByName(name),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roleId}")
    public void deleteRole(@PathVariable("roleId") Long roleId) throws Exception {
        roleService.deleteById(roleId);
    }
}
