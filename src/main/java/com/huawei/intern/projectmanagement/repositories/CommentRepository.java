package com.huawei.intern.projectmanagement.repositories;

import com.huawei.intern.projectmanagement.models.Comment;
import com.huawei.intern.projectmanagement.repositories.generics.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends GenericRepository<Comment> {

    @Transactional
    public List<Comment> findByTask_id(Long taskId);
}
