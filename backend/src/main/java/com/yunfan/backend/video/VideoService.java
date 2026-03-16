package com.yunfan.backend.video;

import com.yunfan.backend.video.dto.CreateVideoRequest;
import com.yunfan.backend.video.dto.CreateVideoUploadRequest;
import com.yunfan.backend.video.dto.LocalVideoUploadResponse;
import com.yunfan.backend.video.dto.VideoResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
public class VideoService {

    private static final String UPLOAD_DIR = "uploads";
    private static final String GLOBAL_FEED_CACHE_KEY = "feed:global";

    private final VideoRepository videoRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public VideoService(VideoRepository videoRepository, RedisTemplate<String, Object> redisTemplate) {
        this.videoRepository = videoRepository;
        this.redisTemplate = redisTemplate;
    }

    public VideoResponse createVideo(CreateVideoRequest request) {
        Video video = new Video();
        video.setUserId(request.getUserId());
        video.setVideoUrl(request.getVideoUrl());
        video.setThumbnailUrl(request.getThumbnailUrl());
        video.setDescription(request.getDescription());

        Video savedVideo = videoRepository.save(video);
        evictFeedCache();

        return toResponse(savedVideo);
    }

    public LocalVideoUploadResponse uploadVideoFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = file.getOriginalFilename();
        String safeFilename = UUID.randomUUID() + "_" + originalFilename;
        Path filePath = uploadPath.resolve(safeFilename);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return new LocalVideoUploadResponse(
                safeFilename,
                filePath.toString(),
                file.getSize()
        );
    }

    public VideoResponse uploadAndCreateVideo(MultipartFile file, CreateVideoUploadRequest request) throws IOException {
        LocalVideoUploadResponse uploadResult = uploadVideoFile(file);

        Video video = new Video();
        video.setUserId(request.getUserId());
        video.setVideoUrl(uploadResult.getFilePath());
        video.setThumbnailUrl(request.getThumbnailUrl());
        video.setDescription(request.getDescription());

        Video savedVideo = videoRepository.save(video);
        evictFeedCache();

        return toResponse(savedVideo);
    }

    @SuppressWarnings("unchecked")
    public List<VideoResponse> getAllVideos() {
        Object cached = redisTemplate.opsForValue().get(GLOBAL_FEED_CACHE_KEY);

        if (cached != null) {
            return (List<VideoResponse>) cached;
        }

        List<VideoResponse> videos = videoRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toResponse)
                .toList();

        redisTemplate.opsForValue().set(GLOBAL_FEED_CACHE_KEY, videos, Duration.ofMinutes(5));

        return videos;
    }

    private void evictFeedCache() {
        redisTemplate.delete(GLOBAL_FEED_CACHE_KEY);
    }

    private VideoResponse toResponse(Video video) {
        return new VideoResponse(
                video.getId(),
                video.getUserId(),
                video.getVideoUrl(),
                video.getThumbnailUrl(),
                video.getDescription(),
                video.getCreatedAt(),
                video.getLikeCount(),
                video.getViewCount(),
                video.getCommentCount(),
                video.getShareCount()
        );
    }
}