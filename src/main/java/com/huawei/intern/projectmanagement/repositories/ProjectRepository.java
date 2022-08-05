package com.huawei.intern.projectmanagement.repositories;

import com.huawei.intern.projectmanagement.models.Project;
import com.huawei.intern.projectmanagement.repositories.generics.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends GenericRepository<Project> {

    public Project findByName(String name);
}
