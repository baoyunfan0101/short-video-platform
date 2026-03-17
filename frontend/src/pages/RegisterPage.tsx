import { useState } from "react";
import { Link } from "react-router-dom";
import { register } from "../api/authApi";
import type { User } from "../types/auth";

function RegisterPage() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [currentUser, setCurrentUser] = useState<User | null>(null);

  const handleRegister = async () => {
    try {
      const data = await register({ username, email, password });
      setMessage(data.message);
      setCurrentUser(data.user);
    } catch (error: any) {
      console.error(error);

      if (error.response) {
        setMessage(`Register failed: ${error.response.status}`);
      } else {
        setMessage("Register failed: network error");
      }
    }
  };

  return (
    <div style={{ padding: "2rem" }}>
      <h1>Register</h1>

      <div style={{ marginBottom: "1rem" }}>
        <input
          type="text"
          placeholder="Enter username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </div>

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

      <button onClick={handleRegister}>Register</button>

      <p>{message}</p>

      {currentUser && (
        <div style={{ marginTop: "1rem" }}>
          <h2>Registered User</h2>
          <p>ID: {currentUser.id}</p>
          <p>Username: {currentUser.username}</p>
          <p>Email: {currentUser.email}</p>
        </div>
      )}

      <p style={{ marginTop: "1rem" }}>
        Already have an account? <Link to="/login">Go to Login</Link>
      </p>
    </div>
  );
}

export default RegisterPage;