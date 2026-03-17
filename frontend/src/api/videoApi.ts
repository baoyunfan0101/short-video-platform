import { apiClient } from "./client";
import type { FeedResponse } from "../types/video";

export const fetchFeed = async () => {
  const res = await apiClient.get<FeedResponse>("/feed");
  return res.data;
};

export const uploadAndCreateVideo = async (
  file: File,
  data: {
    userId: string;
    thumbnailUrl?: string;
    description?: string;
  }
) => {
  const formData = new FormData();
  formData.append("file", file);
  formData.append("data", JSON.stringify(data));

  const res = await apiClient.post("/videos/upload-and-create", formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });

  return res.data;
};