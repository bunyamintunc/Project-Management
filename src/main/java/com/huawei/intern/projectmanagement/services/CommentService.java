package com.huawei.intern.projectmanagement.services;

import com.huawei.intern.projectmanagement.dtos.response.ResponseData;
import com.huawei.intern.projectmanagement.models.Comment;
import com.huawei.intern.projectmanagement.models.Task;
import com.huawei.intern.projectmanagement.models.bugTracking.Bug;
import com.huawei.intern.projectmanagement.repositories.CommentRepository;
import com.huawei.intern.projectmanagement.repositories.bugTracking.BugRepository;
import com.huawei.intern.projectmanagement.services.abstracts.ICommentService;
import com.huawei.intern.projectmanagement.services.bugTracking.BugService;
import com.huawei.intern.projectmanagement.services.generics.impl.GenericService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService  extends GenericService<Comment> implements ICommentService {

    private final CommentRepository fileRepository;
    private final TaskService taskService;
    private final BugService bugService;
    private  final BugRepository bugRepository;



    public Comment saveFile(MultipartFile addFile,Long id) throws Exception {
        String fileName = StringUtils.cleanPath(addFile.getOriginalFilename());
             Task task = taskService.findById(id).orElse(null);
             Comment saveFile = Comment.builder().name(fileName).fileType(addFile.getContentType()).data(addFile.getBytes()).task(task).build();
            return fileRepository.save(saveFile);

    }

    public Comment getFile(Long id) throws Exception {
        return fileRepository.findById(id).orElseThrow(()-> new Exception("Comment not found with Id"+ id));
    }

    @Transactional
    public List<Comment> getByTaskId(Long taskId){
        return  fileRepository.findByTask_id(taskId);
    }


}
