package com.huawei.intern.projectmanagement.task.repositories;

import com.huawei.intern.projectmanagement.task.models.Comment;
import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends GenericRepository<Comment> {

    @Transactional
    public List<Comment> findByTask_id(Long taskId);
}
