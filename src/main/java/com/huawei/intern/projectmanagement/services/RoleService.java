package com.huawei.intern.projectmanagement.services;

import com.huawei.intern.projectmanagement.models.Role;
import com.huawei.intern.projectmanagement.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles(){
        return  roleRepository.findAll();
    }

    public Role findByRoleName(String roleName){
        return roleRepository.findByName(roleName);
    }


}
