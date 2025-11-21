import React, { useState } from "react";
import {
  TextField,
  Button,
  Card,
  CardContent,
  Typography,
  Box
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
  e.preventDefault();

  try {
    const response = await api.post("/auth/login", {
      email,
      password,
    });

    const token = response.data.token; // no JSON.parse

    localStorage.setItem("token", token);

    alert("Login Successful!");
    navigate("/dashboard");
  } catch (err) {
    alert("Invalid Credentials");
  }
};

  return (
    <Box
      sx={{
        width: "100vw",
        height: "100vh",
        background: "linear-gradient(135deg, #5B86E5, #36D1DC)",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Card
        sx={{
          width: 400,
          borderRadius: "20px",
          boxShadow: "0px 8px 25px rgba(0,0,0,0.2)",
          backdropFilter: "blur(10px)",
        }}
      >
        <CardContent sx={{ p: 4 }}>
          <Typography variant="h4" fontWeight="bold" align="center" gutterBottom>
            Smart Expense Tracker
          </Typography>
          <Typography variant="subtitle1" align="center" gutterBottom>
            Login to Continue
          </Typography>

          <TextField
            label="Email"
            fullWidth
            margin="normal"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <TextField
            label="Password"
            type="password"
            fullWidth
            margin="normal"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <Button
            fullWidth
            variant="contained"
            size="large"
            sx={{ mt: 2, borderRadius: "10px" }}
            onClick={handleLogin}
          >
            Login
          </Button>

          <Typography align="center" sx={{ mt: 2 }}>
            Don’t have an account?{" "}
            <span
              style={{ color: "#1565C0", cursor: "pointer" }}
              onClick={() => navigate("/register")}
            >
              Register
            </span>
          </Typography>
        </CardContent>
      </Card>
    </Box>
  );
};

export default Login;
