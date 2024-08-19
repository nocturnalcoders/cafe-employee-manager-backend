package com.ms.data.master.account.controller;

import com.ms.data.master.account.util.FileUploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/uploadLogo")
    public String uploadLogo(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file and return the URL
            return FileUploadUtil.saveFileToLocalDirectory(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file. Please try again!", e);
        }
    }
}