package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import com.huawei.intern.projectmanagement.task.models.Comment;
import com.huawei.intern.projectmanagement.task.models.Project;
import com.huawei.intern.projectmanagement.task.models.Task;
import com.huawei.intern.projectmanagement.task.models.User;
import com.huawei.intern.projectmanagement.task.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;


import static org.mockito.Mockito.*;



import java.util.HashSet;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository fileRepository;

    @Mock
    private UserService userService;

    @Mock
    private TaskService taskService;

    @Mock
    private GenericService<Comment> genericService;


    @Mock
    protected GenericRepository<Comment> genericRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.genericService = new GenericService<Comment>( genericRepository);
    }

    @Test
    public void it_should_create_comment() throws Exception {
        Task testTask= Task.builder().id(1l).user(new User()).project(new Project()).tickets(new HashSet<>())
                .comments(new HashSet<>()).build();

        Comment comment = Comment.builder().id(1L).name("acb").data(null).fileType("dads").build();

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        List<MultipartFile> list = new ArrayList<>();
        list.add(file);
        when(taskService.findById(1L)).thenReturn(Optional.ofNullable(testTask));
        commentService.saveFile(list,1L);
    }

    @Test
    public void  it_should_get_comment_by_id() throws Exception {
        Task testTask= Task.builder().id(1l).user(new User()).project(new Project()).tickets(new HashSet<>())
                .comments(new HashSet<>()).build();

        Comment comment = Comment.builder().id(1L).name("acb").task(testTask).data(null).fileType("dads").build();


        when(fileRepository.findById(1L)).thenReturn(Optional.ofNullable(comment));

        Comment result = commentService.getFile(1L);

        then(result.getName()).isEqualTo(comment.getName());
    }

    @Test
    public void it_should_get_commentlist_by_taskid(){
        Task testTask= Task.builder().id(1l).user(new User()).project(new Project()).tickets(new HashSet<>())
                .comments(new HashSet<>()).build();

        Comment comment = Comment.builder().id(1L).name("acb").task(testTask).data(null).fileType("dads").build();

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);

        when(fileRepository.findByTask_id(any())).thenReturn(commentList);

        List<Comment> result = commentService.getByTaskId(1L);

        then(result.size()).isEqualTo(1);
    }

    @Test
    public void it_should_create_comment_function() throws Exception {
        Task testTask= Task.builder().id(1l).user(new User()).project(new Project()).tickets(new HashSet<>())
                .comments(new HashSet<>()).build();

        Comment comment = Comment.builder().id(1L).name("hello.txt").task(testTask).data(null).fileType("dads").build();

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );


        when(taskService.findById(any())).thenReturn(Optional.ofNullable(testTask));

        Comment result = commentService.createComment(file,1L);

        then(result).isNotNull();
        then(result.getName()).isEqualTo(comment.getName());
    }

    @Test
    public void it_should_getall() throws Exception {
        Task testTask= Task.builder().id(1l).user(new User()).project(new Project()).tickets(new HashSet<>())
                .comments(new HashSet<>()).build();

        Comment comment = Comment.builder().id(1L).name("hello.txt").task(testTask).data(null).fileType("dads").build();

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        when(genericRepository.findAll()).thenReturn(commentList);

        List<Comment> result = commentService.findAll();

        then(result.size()).isEqualTo(1);
    }

    @Test
    public void it_should_findbyid() throws Exception {
        Task testTask= Task.builder().id(1l).user(new User()).project(new Project()).tickets(new HashSet<>())
                .comments(new HashSet<>()).build();

        Comment comment = Comment.builder().id(1L).name("hello.txt").task(testTask).data(null).fileType("dads").build();

        when(genericRepository.findById(any())).thenReturn(Optional.ofNullable(comment));

        Optional<Comment> result = commentService.findById(1L);

        then(result.get().getName()).isEqualTo(comment.getName());
    }

    @Test
    public void it_should_delete_by_id() throws Exception {

        doNothing().when(genericRepository).deleteById(1L);
        commentService.deleteById(1L);
    }

    @Test
    public void it_should_find_by_name() throws Exception {
        Task testTask= Task.builder().id(1l).user(new User()).project(new Project()).tickets(new HashSet<>())
                .comments(new HashSet<>()).build();

        Comment comment = Comment.builder().id(1L).name("hello.txt").task(testTask).data(null).fileType("dads").build();

        when(genericRepository.findByName(any())).thenReturn(comment);

        Comment result = commentService.findByName("any");

        then(result.getName()).isEqualTo(comment.getName());
    }


}