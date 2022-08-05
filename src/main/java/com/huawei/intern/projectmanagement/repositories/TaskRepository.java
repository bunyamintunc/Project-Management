package com.huawei.intern.projectmanagement.repositories;

import com.huawei.intern.projectmanagement.models.Task;
import com.huawei.intern.projectmanagement.repositories.generics.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends GenericRepository<Task> {

    public Task findByName(String name);
    public List<Task> findByTickets_name(String name);

    public List<Task> findByProject_id(Long projectId);

    public List<Task> findByUser_id(Long userId);
}
