package com.huawei.intern.projectmanagement.repositories.generics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T> extends JpaRepository<T,Long> {
     public T findByName(String name);
}
