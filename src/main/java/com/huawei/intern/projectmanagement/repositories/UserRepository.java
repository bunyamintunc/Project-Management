package com.huawei.intern.projectmanagement.repositories;

import com.huawei.intern.projectmanagement.models.User;
import com.huawei.intern.projectmanagement.repositories.generics.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends GenericRepository<User> {

    public User findByUsername(String username);

}
