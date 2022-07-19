package com.huawei.intern.projectmanagement.services;

import com.huawei.intern.projectmanagement.models.Comment;
import com.huawei.intern.projectmanagement.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor

public class CommentService {

    private final CommentRepository fileRepository;

    public Comment saveFile(MultipartFile addFile) throws Exception {
        String fileName = StringUtils.cleanPath(addFile.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw  new Exception("Filename contains invalid path sequence" + fileName);
            }

            Comment saveFile = Comment.builder().name(fileName).fileType(addFile.getContentType()).data(addFile.getBytes()).build();
            return fileRepository.save(saveFile);

        }catch (Exception e){
            throw  new Exception("Could not save file "+fileName);
        }
    }

    public Comment getFile(Long id) throws Exception {
        return fileRepository.findById(id).orElseThrow(()-> new Exception("Comment not found with Id"+ id));
    }

}
