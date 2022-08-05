package com.huawei.intern.projectmanagement.repositories;

import com.huawei.intern.projectmanagement.models.Role;
import com.huawei.intern.projectmanagement.repositories.generics.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends GenericRepository<Role> {

    public Role findByName(String name);
}
