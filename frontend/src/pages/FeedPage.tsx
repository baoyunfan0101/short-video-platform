import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { fetchFeed } from "../api/videoApi";
import type { Video } from "../types/video";

function FeedPage() {
  const [videos, setVideos] = useState<Video[]>([]);
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadFeed = async () => {
      try {
        const data = await fetchFeed();
        setVideos(data.videos);
        setMessage(data.message);
      } catch (error) {
        console.error(error);
        setMessage("Failed to load feed");
      } finally {
        setLoading(false);
      }
    };

    loadFeed();
  }, []);

  return (
    <div style={{ padding: "2rem" }}>
      <h1>Feed</h1>
      <p>{message}</p>

      <p>
        <Link to="/login">Go to Login</Link> |{" "}
        <Link to="/register">Go to Register</Link> |{" "}
        <Link to="/upload">Go to Upload</Link>
      </p>

      {loading && <p>Loading...</p>}

      {!loading && videos.length === 0 && <p>No videos found.</p>}

      {!loading &&
        videos.map((video) => (
          <div
            key={video.id}
            style={{
              border: "1px solid #ccc",
              padding: "1rem",
              marginBottom: "1rem",
            }}
          >
            <p><strong>ID:</strong> {video.id}</p>
            <p><strong>User ID:</strong> {video.userId}</p>
            <p><strong>Description:</strong> {video.description || "No description"}</p>
            <p><strong>Video URL:</strong> {video.videoUrl}</p>
            <p><strong>Thumbnail URL:</strong> {video.thumbnailUrl || "No thumbnail"}</p>
            <p><strong>Created At:</strong> {video.createdAt}</p>
            <p>
              <strong>Stats:</strong> likes {video.likeCount}, views {video.viewCount},
              comments {video.commentCount}, shares {video.shareCount}
            </p>
          </div>
        ))}
    </div>
  );
}

export default FeedPage;