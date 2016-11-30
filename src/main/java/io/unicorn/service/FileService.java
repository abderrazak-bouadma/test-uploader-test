package io.unicorn.service;

import io.unicorn.config.UploadConfigurationProperties;
import io.unicorn.domain.FileMetadata;
import io.unicorn.domain.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.Instant;

@Service
public class FileService {

    private final UploadConfigurationProperties uploadConfigurationProperties;

    @Autowired
    public FileService(UploadConfigurationProperties uploadConfigurationProperties) {
        this.uploadConfigurationProperties = uploadConfigurationProperties;
    }

    public UploadResult storeAndReturnResult(MultipartFile file, FileMetadata fileMetadata) throws IOException {

        // meta data
        UploadResult uploadResult = new UploadResult();
        uploadResult.setStorageInstant(Instant.now());
        uploadResult.setFileSize(file.getSize());
        uploadResult.setFileType(file.getContentType());

        // binary storage
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(buildOutputStreamFileName(file.getOriginalFilename())));
        InputStream inputStream = file.getInputStream();
        int read;
        byte[] buffer = new byte[1024];
        while ((read = inputStream.read(buffer)) != -1) {
            bufferedOutputStream.write(read);
        }
        inputStream.close();
        bufferedOutputStream.flush();
        bufferedOutputStream.close();

        //
        return uploadResult;
    }

    private String buildOutputStreamFileName(String fileName) {
        return uploadConfigurationProperties.getStorageLocation().concat(File.separator).concat(fileName);
    }
}
