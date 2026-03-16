package com.yunfan.backend.video;

import com.yunfan.backend.video.dto.VideoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FeedController {

    private final VideoService videoService;

    public FeedController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/feed")
    public Map<String, Object> getFeed() {
        List<VideoResponse> videos = videoService.getAllVideos();

        return Map.of(
                "message", "Feed fetched successfully",
                "videos", videos
        );
    }
}