package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.models.Comment;
import com.huawei.intern.projectmanagement.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService fileService;

    @PostMapping("/upload")
    public Comment uploadFile(@RequestParam("addFile")MultipartFile addFile) throws Exception {
        System.out.println(addFile.getOriginalFilename());
      return  fileService.saveFile(addFile);
    }
}
