package com.yunfan.backend.video;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunfan.backend.video.dto.CreateVideoRequest;
import com.yunfan.backend.video.dto.CreateVideoUploadRequest;
import com.yunfan.backend.video.dto.LocalVideoUploadResponse;
import com.yunfan.backend.video.dto.VideoResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;
    private final ObjectMapper objectMapper;

    public VideoController(VideoService videoService, ObjectMapper objectMapper) {
        this.videoService = videoService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public Map<String, Object> createVideo(@Valid @RequestBody CreateVideoRequest request) {
        VideoResponse video = videoService.createVideo(request);

        return Map.of(
                "message", "Video created successfully",
                "video", video
        );
    }

    @GetMapping
    public Map<String, Object> getAllVideos() {
        List<VideoResponse> videos = videoService.getAllVideos();

        return Map.of(
                "message", "Videos fetched successfully",
                "videos", videos
        );
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        LocalVideoUploadResponse result = videoService.uploadVideoFile(file);

        return Map.of(
                "message", "File uploaded successfully",
                "file", result
        );
    }

    @PostMapping(value = "/upload-and-create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadAndCreateVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("data") String data
    ) throws IOException {
        CreateVideoUploadRequest request = objectMapper.readValue(data, CreateVideoUploadRequest.class);
        VideoResponse video = videoService.uploadAndCreateVideo(file, request);

        return Map.of(
                "message", "Video uploaded and created successfully",
                "video", video
        );
    }
}