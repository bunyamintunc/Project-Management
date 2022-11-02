package com.huawei.intern.projectmanagement.bugtracking.repositories;

import com.huawei.intern.projectmanagement.bugtracking.models.Bug;
import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;

import java.util.List;

public interface BugRepository extends GenericRepository<Bug> {

    List<Bug> findByUser_id(Long userId);
}
