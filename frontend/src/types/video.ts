export interface Video {
  id: string;
  userId: string;
  videoUrl: string;
  thumbnailUrl?: string | null;
  description?: string | null;
  createdAt: string;
  likeCount: number;
  viewCount: number;
  commentCount: number;
  shareCount: number;
}

export interface FeedResponse {
  message: string;
  videos: Video[];
}