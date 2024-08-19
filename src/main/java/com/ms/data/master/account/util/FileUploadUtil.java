package com.ms.data.master.account.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtil {
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    public static String saveFileToLocalDirectory(MultipartFile file) throws IOException {
        // Ensure the directory exists
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Get the original filename and define the target path
        String originalFileName = file.getOriginalFilename();
        String fileName = originalFileName;
        Path targetPath = Paths.get(UPLOAD_DIR + fileName);

        // Check if a file with the same name already exists
        int count = 1;
        while (Files.exists(targetPath)) {
            // Append a number to the file name before the extension
            String name = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
            String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
            fileName = name + "_" + count + extension;
            targetPath = Paths.get(UPLOAD_DIR + fileName);
            count++;
        }

        // Save the file
        Files.copy(file.getInputStream(), targetPath);

        // Return the file URL relative to the static resource path
        return "/cafe-employee/api/v1/images/" + fileName;
    }
}