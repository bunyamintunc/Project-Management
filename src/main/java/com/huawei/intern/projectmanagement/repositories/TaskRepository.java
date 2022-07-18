package com.huawei.intern.projectmanagement.repositories;

import com.huawei.intern.projectmanagement.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public Task findByName(String name);
    public List<Task> findByTickets_name(String name);
}
