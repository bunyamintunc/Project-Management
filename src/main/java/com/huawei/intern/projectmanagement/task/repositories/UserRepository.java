package com.huawei.intern.projectmanagement.task.repositories;

import com.huawei.intern.projectmanagement.task.models.User;
import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;

public interface UserRepository extends GenericRepository<User> {

    public User findByUsername(String username);

}
