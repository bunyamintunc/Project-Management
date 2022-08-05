package com.huawei.intern.projectmanagement.services;

import com.huawei.intern.projectmanagement.models.Role;
import com.huawei.intern.projectmanagement.repositories.RoleRepository;
import com.huawei.intern.projectmanagement.services.abstracts.IRoleService;
import com.huawei.intern.projectmanagement.services.generics.impl.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService  extends GenericService<Role> implements IRoleService {

    private final RoleRepository roleRepository;


    @Transactional
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }


    public Role findByRoleName(String roleName){
        return roleRepository.findByName(roleName);
    }



}
