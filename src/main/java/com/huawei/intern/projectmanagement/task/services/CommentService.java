package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.task.models.Comment;
import com.huawei.intern.projectmanagement.task.models.Task;
import com.huawei.intern.projectmanagement.task.repositories.CommentRepository;
import com.huawei.intern.projectmanagement.bugtracking.repositories.BugRepository;
import com.huawei.intern.projectmanagement.task.services.abstracts.ICommentService;
import com.huawei.intern.projectmanagement.bugtracking.services.BugService;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@AllArgsConstructor
public class CommentService  extends GenericService<Comment> implements ICommentService {

    private final CommentRepository fileRepository;
    private  final TaskService taskService;
    private final BugService bugService;
    private  final BugRepository bugRepository;



    public void saveFile(List<MultipartFile> addFile,Long id) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Comment>> comments = new ArrayList<Future<Comment>>();
        Comment comm;
        for(MultipartFile file : addFile){
            comments.add(executor.submit(()->createComment(file,id)));

        }
        for (Future<Comment> future : comments){
           comm =   fileRepository.save(future.get());
        }
        executor.shutdown();
    }

    public Comment getFile(Long id) throws Exception {
        return fileRepository.findById(id).orElseThrow(()-> new Exception("Comment not found with Id"+ id));
    }

    @Transactional
    public List<Comment> getByTaskId(Long taskId){
        return  fileRepository.findByTask_id(taskId);
    }


    public   Comment createComment(MultipartFile addFile,Long id) throws Exception {
        String fileName = StringUtils.cleanPath(addFile.getOriginalFilename());
        Task task = taskService.findById(id).orElse(null);
        Comment saveFile = Comment.builder().name(fileName).fileType(addFile.getContentType()).data(addFile.getBytes()).task(task).build();
        return saveFile;
    };
}


