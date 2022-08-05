package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.dtos.response.ResponseData;
import com.huawei.intern.projectmanagement.models.Comment;
import com.huawei.intern.projectmanagement.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;


    @PostMapping("/upload/{id}")
    public ResponseData uploadFile(@RequestParam("addFile")MultipartFile addFile, @PathVariable("id") Long id) throws Exception {
        String downloadUrl = "";
        Comment comment =  commentService.saveFile(addFile,id);

        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(comment.getId()))
                .toUriString();
        return new  ResponseData(comment.getName(),downloadUrl,addFile.getContentType(),addFile.getSize());
    }



    @GetMapping("/getbyid/{commentId}")
    public Comment  getComment(@PathVariable("commentId") Long commentId) throws Exception {
        return commentService.getFile(commentId);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadfile(@PathVariable("fileId") Long fileId) throws Exception {

        Comment comment  = commentService.findById(fileId).orElseThrow(()-> new Exception("comment does not found"));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(comment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"comment; filename=\""+ comment.getName() + "\"" )
                .body(new ByteArrayResource(comment.getData()));
    }

    @GetMapping("/task/{taskId}")
    public List<Comment> getCommentsForTask(@PathVariable("taskId")Long taskId){
        return commentService.getByTaskId(taskId);
    }



}
