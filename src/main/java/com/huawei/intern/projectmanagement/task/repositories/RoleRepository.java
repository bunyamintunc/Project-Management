package com.huawei.intern.projectmanagement.task.repositories;

import com.huawei.intern.projectmanagement.task.models.Role;
import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;

public interface RoleRepository extends GenericRepository<Role> {

    public Role findByName(String name);
}
