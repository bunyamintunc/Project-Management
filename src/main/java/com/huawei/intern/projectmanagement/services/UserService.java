package com.huawei.intern.projectmanagement.services;

import com.huawei.intern.projectmanagement.models.Role;
import com.huawei.intern.projectmanagement.models.User;
import com.huawei.intern.projectmanagement.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void addRoleToUser(String userName,String roleName){
        User user = userRepository.findByUserName(userName);
        Role role =  roleService.findByRoleName(roleName);
        if(user != null){
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    public User getByName(String name){
        return userRepository.findByUserName(name);
    }
}
