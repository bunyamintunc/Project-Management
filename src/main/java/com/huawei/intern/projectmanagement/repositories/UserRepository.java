package com.huawei.intern.projectmanagement.repositories;

import com.huawei.intern.projectmanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUserName(String userName);
}
