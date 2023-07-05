package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@Service
public class FileUploadService {

    private FileMapper fileMapper;
    private UserService userService;

    public FileUploadService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public Integer insertFileToDB(MultipartFile fileUpload) throws IOException, SQLException {
        //get file information
        InputStream file = fileUpload.getInputStream();
        String fileName = fileUpload.getOriginalFilename();
        String contentType = fileUpload.getContentType();
        String fileSize = Long.toString(fileUpload.getSize());
        byte[] fileBytes = fileUpload.getBytes();

        //create file object
        File inserted = new File(null, fileName, contentType, fileSize, this.userService.getLoginUserId(), fileBytes);
        return this.fileMapper.insertFile(inserted);
    }

    public File[] getFiles() {
        return this.fileMapper.getFiles(this.userService.getLoginUserId());
    }

    public Integer deleteFile(Integer fileId) {
        return this.fileMapper.deleteFile(fileId);
    }

    public File getFile(Integer fileId) {
        return this.fileMapper.getFile(fileId);
    }

    public boolean isFileExist(String originalFilename) {
        return this.fileMapper.getFileByName(originalFilename) != null;
    }
}
