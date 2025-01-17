package com.backend.services.aws;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IS3Service {
    String uploadFile(MultipartFile file) throws IOException;
    String getPresignedUrl(String fileName);
}
