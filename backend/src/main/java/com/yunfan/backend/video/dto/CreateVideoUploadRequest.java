package com.yunfan.backend.video.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CreateVideoUploadRequest {

    @NotNull
    private UUID userId;

    private String thumbnailUrl;

    private String description;

    public CreateVideoUploadRequest() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}