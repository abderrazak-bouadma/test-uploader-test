package io.unicorn.domain;

import java.time.Instant;

/**
 * Created by abderrazakbouadma on 29/11/2016.
 */
public class UploadResult {

    private long fileSize;
    private String fileType;
    private Instant storageInstant;


    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Instant getStorageInstant() {
        return storageInstant;
    }

    public void setStorageInstant(Instant storageInstant) {
        this.storageInstant = storageInstant;
    }
}
