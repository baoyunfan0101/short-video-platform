import { apiClient } from "./client";
import type {
  AuthResponse,
  LoginRequest,
  RegisterRequest,
} from "../types/auth";

export const login = async (payload: LoginRequest) => {
  const res = await apiClient.post<AuthResponse>("/auth/login", payload);
  return res.data;
};

export const register = async (payload: RegisterRequest) => {
  const res = await apiClient.post<AuthResponse>("/auth/register", payload);
  return res.data;
};