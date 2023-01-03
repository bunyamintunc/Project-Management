package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import com.huawei.intern.projectmanagement.task.models.Role;
import com.huawei.intern.projectmanagement.task.models.Task;
import com.huawei.intern.projectmanagement.task.repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import  org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.net.PortUnreachableException;
import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RoleServiceTest {
    @InjectMocks
    private RoleService roleService;
    @Mock
    private RoleRepository roleRepository;


    @Mock
    private GenericService<Role> genericService;


    @Mock
    protected GenericRepository<Role> genericRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.genericService = new GenericService<Role>( genericRepository);
    }

    @Test
    public void it_should_list_role() throws Exception{

       Role tesRole = Role.builder().name("test_role").build();
       List<Role> roles = new ArrayList<>();
       roles.add(tesRole);

       when(genericRepository.findAll()).thenReturn(roles);
       List<Role> result = roleService.findAll();

       then(result.size()).isEqualTo(1);

    }

    @Test
    public void it_should_save_role() throws Exception{

        Role testRole = new Role();
        testRole.setName("test_role");

        when(roleRepository.save(any())).thenReturn(testRole);

        Role result = roleService.saveRole(testRole);

        then(result).isNotNull();
        then(result.getName()).isEqualTo("test_role");

    }

    @Test
    public void it_should_get_role_by_roleName() throws Exception {


        Role testRole = Role.builder().name("test_role").id(1l).build();

        when(roleRepository.findByName(any())).thenReturn(testRole);

        Role result = roleService.findByRoleName("test_role");

        then(result).isNotNull();
        then(result.getName()).isEqualTo("test_role");

    }

    @Test
    public void it_should_get_by_id() throws Exception {
        Role testRole = Role.builder().name("test_role").id(1l).build();

        when(genericRepository.findById(any())).thenReturn(Optional.ofNullable(testRole));

        Optional<Role> result = roleService.findById(1L);

        then(result).isNotNull();
        then(result.get().getName()).isEqualTo(testRole.getName());

    }

    @Test
    public void it_should_get_by_name() throws Exception {
        Role testRole = Role.builder().name("test_role").id(1l).build();

        when(genericRepository.findByName(any())).thenReturn(testRole);

        Role result = roleService.findByName("adsasd");

        then(result).isNotNull();
        then(result.getName()).isEqualTo(testRole.getName());
    }

    @Test
    public void it_should_delete() throws Exception {

        doNothing().when(genericRepository).deleteById(any());
        roleService.deleteById(1L);
    }

}