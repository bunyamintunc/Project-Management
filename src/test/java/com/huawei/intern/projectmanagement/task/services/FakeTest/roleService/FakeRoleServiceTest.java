package com.huawei.intern.projectmanagement.task.services.FakeTest.roleService;

import com.huawei.intern.projectmanagement.task.models.Role;
import com.huawei.intern.projectmanagement.task.services.RoleService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FakeRoleServiceTest {
    FakeRoleService fakeRoleService = new FakeRoleService();
    RoleService roleService = new RoleService(fakeRoleService);
    @Test
    public void it_shoul_create_role(){


        Role role = Role.builder().id(1l).name("abc").build();
        assertEquals(role, roleService.saveRole(role) );
    }

    @Test
    public void it_should_get_by_name(){
        Role role = Role.builder().id(1l).name("abc").build();

        roleService.saveRole(role);

        Role result  = roleService.findByRoleName("abc");
        assertEquals(role,result);
        assertEquals(role.getId(),result.getId());
    }
}
