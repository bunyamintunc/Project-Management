package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.task.models.Role;
import com.huawei.intern.projectmanagement.task.repositories.RoleRepository;
import com.huawei.intern.projectmanagement.task.services.abstracts.IRoleService;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class RoleService  extends GenericService<Role> implements IRoleService {

    private  RoleRepository roleRepository;



    @Transactional
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }


    public Role findByRoleName(String roleName){
        return roleRepository.findByName(roleName);
    }



}
