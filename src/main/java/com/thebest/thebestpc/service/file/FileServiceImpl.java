package com.thebest.thebestpc.service.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public void saveFile(MultipartFile file) {
        saveFile("uploads/", file);
    }

    @Override
    public void saveFile(String uploadPath, MultipartFile file) {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            file.transferTo(Path.of(uploadPath, file.getOriginalFilename()));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public byte[] getFile(String fileName) {
        return new byte[0];
    }

    @Override
    public void deleteFile(String fileName) {

    }
}
