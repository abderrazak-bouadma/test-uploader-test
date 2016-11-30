package io.unicorn.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private final FileService fileService;

    @Autowired
    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/2")
    public ResponseEntity<UploadResult> upload(MultipartHttpServletRequest servletRequest) throws IOException, ServletException {
        MultipartFile file = servletRequest.getFile("file");
        String[] fileMetadatas = servletRequest.getParameterValues("fileMetadata");
        ObjectMapper objectMapper = new ObjectMapper();
        FileMetadata fileMetadata = objectMapper.readValue(fileMetadatas[0], FileMetadata.class);
        return ResponseEntity.ok(fileService.storeAndReturnResult(file, fileMetadata));
    }

    @ApiOperation(value = "/upload", httpMethod = "POST", tags = "uploaders", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(consumes = {"multipart/mixed","multipart/form-data","application/octet-stream"})
    public ResponseEntity<UploadResult> handleFileUpload(
            @ApiParam @RequestPart MultipartFile file,
            @ApiParam @RequestPart(required = false) FileMetadata fileMetadata) throws IOException {
        return ResponseEntity.ok(fileService.storeAndReturnResult(file, fileMetadata));
    }
}
