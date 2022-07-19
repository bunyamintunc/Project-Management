package com.huawei.intern.projectmanagement.repositories;

import com.huawei.intern.projectmanagement.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
