package com.huawei.intern.projectmanagement.repositories;

import com.huawei.intern.projectmanagement.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    public Role findByName(String name);
}
