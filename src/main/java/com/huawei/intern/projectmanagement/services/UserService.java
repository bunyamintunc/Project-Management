package com.huawei.intern.projectmanagement.services;

import com.huawei.intern.projectmanagement.dtos.request.UserDto;
import com.huawei.intern.projectmanagement.models.Role;
import com.huawei.intern.projectmanagement.models.User;
import com.huawei.intern.projectmanagement.repositories.UserRepository;
import com.huawei.intern.projectmanagement.services.generics.impl.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService extends GenericService<User>  {

    private final UserRepository userRepository;
    private final RoleService roleService;

    private  final PasswordEncoder passwordEncoder;




    @Transactional
    public User saveUser(User user){
        System.out.println("password" +user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setTasks(new HashSet<>());
        return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void addRoleToUser(Long userId,long roleId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        Role role =  roleService.findById(roleId).orElse(null);
        if(user != null){
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    public User getByName(String name){
        return userRepository.findByUsername(name);
    }

    public Optional<User> getById(Long userId){
       return  userRepository.findById(userId);
    }


    @Override
    public List<User> findAll() throws Exception {
        return super.findAll();
    }

    public User editUser(UserDto dto) {
        User user = userRepository.findById(dto.getId()).orElse(null);
        if(user != null){
            user.setUsername(dto.getUsername());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setEmail(dto.getEmail());
            user.setImageUrl(dto.getImageUrl());
            userRepository.save(user);
        }
        return null;
    }
}
