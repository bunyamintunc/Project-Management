package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import com.huawei.intern.projectmanagement.task.dtos.request.UserDto;
import com.huawei.intern.projectmanagement.task.models.Role;
import com.huawei.intern.projectmanagement.task.models.Ticket;
import com.huawei.intern.projectmanagement.task.models.User;
import com.huawei.intern.projectmanagement.task.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import  org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private GenericService<User> genericService;


    @Mock
    protected GenericRepository<User> genericRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.genericService = new GenericService<User>( genericRepository);
    }

    @Test
    public void it_shoud_create_user() throws Exception {
        User testUser = User.builder().password("abc").name("test").surName("tes1").id(1l).email("deneme@deneme")
                .username("testusername").tasks(new HashSet<>()).imageUrl("sdasda").build();

        Role testRole  = Role.builder().id(1l).name("test_role").build();

        User testUser2 = User.builder().password("abc").name("test").surName("tes1").id(1l).email("deneme@deneme")
                .username("testusername").tasks(new HashSet<>()).imageUrl("sdasda").roles(new HashSet<>()).build();
        testUser2.getRoles().add(testRole);

        when(userRepository.save(testUser)).thenReturn(testUser2);

        User result = userService.saveUser(testUser);

        then(result).isNotNull();
        then(result.getName()).isEqualTo(testUser2.getName());

    }

    @Test
    public void it_should_edit_user(){

        UserDto testEditUser = UserDto.builder().id(1l).email("bunaymin@bunaymin").imageUrl("dsasd")
                .password("123").username("bunaymin").build();

        User user = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        when(userRepository.findById(testEditUser.getId())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.editUser(testEditUser);

        then(result).isNotNull();
        then(result.getName()).isEqualTo(user.getName());
    }

    @Test
    public void it_should_get_all_user(){

        User user = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        List<User> listUser = new ArrayList<>();
        listUser.add(user);

        when(userRepository.findAll()).thenReturn(listUser);
        List<User> result = userService.getAllUsers();

        then(result.size()).isEqualTo(1);
    }


    @Test
    public void it_should_get_by_name(){
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        when(userRepository.findByUsername(testuser.getUsername())).thenReturn(testuser);

        User result = userService.getByName(testuser.getUsername());

        then(result).isNotNull();
        then(result.getName()).isEqualTo(testuser.getName());
    }

    @Test
    public void it_should_get_by_id(){
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(testuser));

        Optional<User> result = userService.getById(1L);

        then(result).isNotNull();
        then(result.get().getName()).isEqualTo(testuser.getName());
    }

    @Test
    public void it_should_getall() throws Exception {
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();
        List<User> userList = new ArrayList<User>();
        userList.add(testuser);

        when(genericRepository.findAll()).thenReturn(userList);

        List<User> result = userService.findAll();

        then(result).isNotNull();
        then(result.size()).isEqualTo(1);

    }

    @Test
    public void it_should_findbyid() throws Exception {
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        when(genericRepository.findById(1L)).thenReturn(Optional.ofNullable(testuser));
        Optional<User> result = userService.findById(1L);

        then(result).isNotNull();
        then(result.get().getName()).isEqualTo(testuser.getName());
    }

    @Test
    public void it_should_delete_by_id() throws Exception {
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        doNothing().when(genericRepository).deleteById(1L);

        userService.deleteById(1L);
    }

    @Test
    public void it_should_find_by_name() throws Exception {


    }




}