package com.huawei.intern.projectmanagement.task.controllers;

import com.huawei.intern.projectmanagement.task.dtos.response.ResponseData;
import com.huawei.intern.projectmanagement.task.models.Comment;
import com.huawei.intern.projectmanagement.task.services.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
@Api(value = "Comment Api Documantation")
public class CommentController {

    private CommentService commentService;


    @PostMapping("/upload/{id}")
    @ApiOperation(value = "Upload  file  method")
    public ResponseData uploadFile(@RequestParam("addFile") List<MultipartFile> addFile, @PathVariable("id") Long id) throws Exception {
        String downloadUrl = "";

        commentService.saveFile(addFile,id);

        /*
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(comment.getId()))
                .toUriString();

        return new  ResponseData(comment.getName(),downloadUrl,addFile.get(0).getContentType(),addFile.get(0).getSize());


         */
        return null;
    }




    @GetMapping("/getbyid/{commentId}")
    @ApiOperation(value = "Get one comment by id method")
    public Comment  getComment(@PathVariable("commentId") Long commentId) throws Exception {
        return commentService.getFile(commentId);
    }

    @GetMapping("/download/{fileId}")
    @ApiOperation(value = "Download file  method")
    public ResponseEntity<Resource> downloadfile(@PathVariable("fileId") Long fileId) throws Exception {

        Comment comment  = commentService.findById(fileId).orElseThrow(()-> new Exception("comment does not found"));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(comment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"comment; filename=\""+ comment.getName() + "\"" )
                .body(new ByteArrayResource(comment.getData()));
    }

    @GetMapping("/task/{taskId}")
    @ApiOperation(value = "Get comments by task id  method")
    public List<Comment> getCommentsForTask(@PathVariable("taskId")Long taskId){
        return commentService.getByTaskId(taskId);
    }



}
