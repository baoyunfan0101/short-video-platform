import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { login } from "../api/authApi";
import type { User } from "../types/auth";

function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [currentUser, setCurrentUser] = useState<User | null>(null);

  useEffect(() => {
    const savedUser = localStorage.getItem("user");
    if (savedUser) {
      setCurrentUser(JSON.parse(savedUser));
    }
  }, []);

  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const data = await login({ email, password });
      setMessage(data.message);
      setCurrentUser(data.user);
      localStorage.setItem("user", JSON.stringify(data.user));
      navigate("/");
    } catch (error: any) {
      console.error(error);

      if (error.response) {
        setMessage(`Login failed: ${error.response.status}`);
      } else {
        setMessage("Login failed: network error");
      }
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    setCurrentUser(null);
    setMessage("Logged out");
  };

  return (
    <div style={{ padding: "2rem" }}>
      <h1>Login</h1>

      <div style={{ marginBottom: "1rem" }}>
        <input
          type="email"
          placeholder="Enter email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>

      <div style={{ marginBottom: "1rem" }}>
        <input
          type="password"
          placeholder="Enter password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>

      <button onClick={handleLogin}>Login</button>

      {currentUser && (
        <button onClick={handleLogout} style={{ marginLeft: "1rem" }}>
          Logout
        </button>
      )}

      <p>{message}</p>

      {currentUser && (
        <div style={{ marginTop: "1rem" }}>
          <h2>Current User</h2>
          <p>ID: {currentUser.id}</p>
          <p>Username: {currentUser.username}</p>
          <p>Email: {currentUser.email}</p>
        </div>
      )}

      <p style={{ marginTop: "1rem" }}>
        No account? <Link to="/register">Go to Register</Link>
      </p>
    </div>
  );
}

export default LoginPage;