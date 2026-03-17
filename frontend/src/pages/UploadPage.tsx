import { useState } from "react";
import { Link } from "react-router-dom";
import { uploadAndCreateVideo } from "../api/videoApi";
import type { User } from "../types/auth";

function UploadPage() {
  const [file, setFile] = useState<File | null>(null);
  const [thumbnailUrl, setThumbnailUrl] = useState("");
  const [description, setDescription] = useState("");
  const [message, setMessage] = useState("");

  const savedUser = localStorage.getItem("user");
  const currentUser: User | null = savedUser ? JSON.parse(savedUser) : null;

  const handleUpload = async () => {
    if (!currentUser) {
      setMessage("Please login first");
      return;
    }

    if (!file) {
      setMessage("Please select a video file");
      return;
    }

    try {
      await uploadAndCreateVideo(file, {
        userId: currentUser.id,
        thumbnailUrl,
        description,
      });

      setMessage("Video uploaded successfully");
      setFile(null);
      setThumbnailUrl("");
      setDescription("");
    } catch (error: any) {
      console.error(error);

      if (error.response) {
        setMessage(`Upload failed: ${error.response.status}`);
      } else {
        setMessage("Upload failed: network error");
      }
    }
  };

  return (
    <div style={{ padding: "2rem" }}>
      <h1>Upload Video</h1>

      <p>
        <Link to="/">Go to Feed</Link> |{" "}
        <Link to="/login">Go to Login</Link> |{" "}
        <Link to="/register">Go to Register</Link>
      </p>

      {!currentUser && <p>You are not logged in.</p>}

      {currentUser && (
        <p>
          Current user: {currentUser.username} ({currentUser.email})
        </p>
      )}

      <div style={{ marginBottom: "1rem" }}>
        <input
          type="file"
          accept="video/*"
          onChange={(e) => setFile(e.target.files?.[0] || null)}
        />
      </div>

      <div style={{ marginBottom: "1rem" }}>
        <input
          type="text"
          placeholder="Thumbnail URL (optional)"
          value={thumbnailUrl}
          onChange={(e) => setThumbnailUrl(e.target.value)}
        />
      </div>

      <div style={{ marginBottom: "1rem" }}>
        <input
          type="text"
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </div>

      <button onClick={handleUpload}>Upload</button>

      <p>{message}</p>
    </div>
  );
}

export default UploadPage;