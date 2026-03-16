package com.yunfan.backend.video.dto;

public class LocalVideoUploadResponse {

    private String fileName;
    private String filePath;
    private long fileSize;

    public LocalVideoUploadResponse() {
    }

    public LocalVideoUploadResponse(String fileName, String filePath, long fileSize) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }
}