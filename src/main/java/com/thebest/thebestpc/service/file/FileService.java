package com.thebest.thebestpc.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void saveFile(MultipartFile file);

    void saveFile(String uploadPath, MultipartFile file);

    byte[] getFile(String fileName);

    void deleteFile(String fileName);

}
