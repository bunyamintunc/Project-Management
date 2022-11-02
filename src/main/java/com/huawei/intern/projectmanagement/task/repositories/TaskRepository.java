package com.huawei.intern.projectmanagement.task.repositories;

import com.huawei.intern.projectmanagement.task.models.Task;
import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;

import java.util.List;

public interface TaskRepository extends GenericRepository<Task> {

    public Task findByName(String name);
    public List<Task> findByTickets_name(String name);

    public List<Task> findByProject_id(Long projectId);

    public List<Task> findByUser_id(Long userId);
}
