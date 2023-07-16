package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.utils.ERROR;
import com.udacity.jwdnd.course1.cloudstorage.utils.SUCCESS;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/file")
public class FileUploadController {
    private FileUploadService fileUploadService;
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, RedirectAttributes redirectAttributes) {
        if (fileUpload.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", ERROR.FILE_EMPTY.toString());
            return "redirect:/#nav-files";
        }
        boolean isFileExist = this.fileUploadService.isFileExist(fileUpload.getOriginalFilename());
        if (isFileExist) {
            redirectAttributes.addFlashAttribute("message", ERROR.FILE_EXISTS.toString());
        } else {
            try {
                Integer insertedId = this.fileUploadService.insertFileToDB(fileUpload);
                redirectAttributes.addFlashAttribute("message", SUCCESS.FILE_UPLOADED.toString());
            } catch (SQLException e) {
                redirectAttributes.addFlashAttribute("message", ERROR.SOMETHING_WRONG.toString());
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("message", ERROR.SOMETHING_WRONG.toString());
            }
        }

        return "redirect:/#nav-files";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, RedirectAttributes redirectAttributes) {
        this.fileUploadService.deleteFile(fileId);
        redirectAttributes.addFlashAttribute("message", SUCCESS.FILE_DELETED.toString());
        return "redirect:/#nav-files";
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<Resource> viewFile(@PathVariable Integer fileId, Model model) {
        File file = this.fileUploadService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getFileData()));
    }
}
