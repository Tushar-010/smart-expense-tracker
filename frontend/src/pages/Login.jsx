import React, { useState } from "react";
import { TextField, Button, Card, CardContent, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post("/auth/login", { email, password });
      const token = response.data.token;

      localStorage.setItem("token", token);
      navigate("/dashboard");
    } catch (err) {
      setError("Invalid email or password");
    }
  };

  return (
    <div className="login-container" style={{ display: "flex", justifyContent: "center", marginTop: "80px" }}>
      <Card style={{ width: "420px", padding: "20px" }}>
        <CardContent>
          <Typography variant="h5" align="center" gutterBottom>
            Smart Expense Tracker - Login
          </Typography>

          <form onSubmit={handleLogin}>
            <TextField
              label="Email"
              type="email"
              fullWidth
              margin="normal"
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />

            <TextField
              label="Password"
              type="password"
              fullWidth
              margin="normal"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />

            {error && <Typography color="error">{error}</Typography>}

            <Button type="submit" fullWidth variant="contained" style={{ marginTop: "20px" }}>
              Login
            </Button>
          </form>

          <Typography align="center" style={{ marginTop: "15px", cursor: "pointer" }}
            onClick={() => navigate("/register")}
          >
            Don't have an account? Register
          </Typography>
        </CardContent>
      </Card>
    </div>
  );
};

export default Login;
