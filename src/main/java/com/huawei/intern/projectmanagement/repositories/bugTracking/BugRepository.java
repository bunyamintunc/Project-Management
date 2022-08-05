package com.huawei.intern.projectmanagement.repositories.bugTracking;

import com.huawei.intern.projectmanagement.models.bugTracking.Bug;
import com.huawei.intern.projectmanagement.repositories.generics.GenericRepository;

import java.util.List;

public interface BugRepository extends GenericRepository<Bug> {

    List<Bug> findByUser_id(Long userId);
}
