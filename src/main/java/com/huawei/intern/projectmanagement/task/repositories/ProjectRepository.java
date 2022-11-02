package com.huawei.intern.projectmanagement.task.repositories;

import com.huawei.intern.projectmanagement.task.models.Project;
import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;

public interface ProjectRepository extends GenericRepository<Project> {

    public Project findByName(String name);
}
