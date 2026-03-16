package com.yunfan.backend.video.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class VideoResponse {

    private UUID id;
    private UUID userId;
    private String videoUrl;
    private String thumbnailUrl;
    private String description;
    private LocalDateTime createdAt;
    private Long likeCount;
    private Long viewCount;
    private Long commentCount;
    private Long shareCount;

    public VideoResponse() {
    }

    public VideoResponse(UUID id, UUID userId, String videoUrl, String thumbnailUrl,
                         String description, LocalDateTime createdAt,
                         Long likeCount, Long viewCount, Long commentCount, Long shareCount) {
        this.id = id;
        this.userId = userId;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.shareCount = shareCount;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public Long getShareCount() {
        return shareCount;
    }
}