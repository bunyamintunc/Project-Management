package com.huawei.intern.projectmanagement.security.controller;

import com.huawei.intern.projectmanagement.task.dtos.request.LoginUserDto;
import com.huawei.intern.projectmanagement.task.dtos.response.AuthResponse;
import com.huawei.intern.projectmanagement.task.models.Role;
import com.huawei.intern.projectmanagement.task.models.User;
import com.huawei.intern.projectmanagement.security.JwtTokenProvider;
import com.huawei.intern.projectmanagement.task.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
@Api(value = "Role Api Documantation")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "User login method")
    public AuthResponse Login(@RequestBody LoginUserDto userDto){
        List<Role> roles = new ArrayList<>();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken  = jwtTokenProvider.generateJwtToken(auth);
        User user = userService.getByName(userDto.getUsername());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Bearer "+jwtToken);
        authResponse.setUserId(user.getId());
        authResponse.setEmail(user.getEmail());
        authResponse.setSurname(user.getSurName());
        authResponse.setUsername(user.getUsername());
        authResponse.setName(user.getName());
        //login olan kullanıcının rollerini dönüyoruz.
        for( Role role : user.getRoles()){
            roles.add(role);
        }
        authResponse.setRoles(roles);
        return authResponse;

    }
}
