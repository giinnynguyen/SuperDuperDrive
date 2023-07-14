package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
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
    public String fileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, RedirectAttributes redirectAttributes) throws IOException, SQLException {
        if (fileUpload.isEmpty()) {
            redirectAttributes.addAttribute("message", "emptyFile");
            return "redirect:/";
        }
        boolean isFileExist = this.fileUploadService.isFileExist(fileUpload.getOriginalFilename());
        if (isFileExist) {
            redirectAttributes.addAttribute("message", "fileExist");
        } else {
            Integer insertedId = this.fileUploadService.insertFileToDB(fileUpload);
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        this.fileUploadService.deleteFile(fileId);
        return "redirect:/";
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
