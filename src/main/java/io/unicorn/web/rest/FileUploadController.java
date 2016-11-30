package io.unicorn.web.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.unicorn.domain.FileMetadata;
import io.unicorn.domain.UploadResult;
import io.unicorn.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private final FileService fileService;

    @Autowired
    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "/upload", httpMethod = "POST", tags = "uploaders", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping
    public ResponseEntity<UploadResult> handleFileUpload(
            @ApiParam @RequestPart MultipartFile file,
            @ApiParam @RequestPart(required = false) FileMetadata fileMetadata) throws IOException {
        return ResponseEntity.ok(fileService.storeAndReturnResult(file, fileMetadata));
    }
}
