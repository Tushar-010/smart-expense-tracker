import React, { useState } from "react";
import { TextField, Button, Card, CardContent, Typography } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import api from "../api/axios";
import financeImg from "../assets/finance_illustration.png";

const Register = () => {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      await api.post("/auth/register", { name, email, password });
      navigate("/"); // redirect to login
    } catch (err) {
      setError("Registration failed. Try another email.");
    }
  };

  return (
    <div style={{ display: "flex", height: "100vh", alignItems: "center", justifyContent: "center" }}>
      <img src={financeImg} alt="Finance" style={{ width: "40%", marginRight: "40px" }} />
      <Card style={{ width: 380, padding: 20 }}>
        <CardContent>
          <Typography variant="h5" align="center" gutterBottom>
            Create Account
          </Typography>

          <form onSubmit={handleRegister}>
            <TextField label="Full Name" fullWidth margin="normal"
              value={name} onChange={(e) => setName(e.target.value)} />

            <TextField label="Email" fullWidth margin="normal"
              value={email} onChange={(e) => setEmail(e.target.value)} />

            <TextField label="Password" type="password" fullWidth margin="normal"
              value={password} onChange={(e) => setPassword(e.target.value)} />

            {error && <p style={{ color: "red" }}>{error}</p>}

            <Button type="submit" variant="contained" fullWidth style={{ marginTop: 15 }}>
              Register
            </Button>
          </form>
          <Typography style={{ marginTop: 15 }} align="center">
            Already have an account? <Link to="/">Login</Link>
          </Typography>
        </CardContent>
      </Card>
    </div>
  );
};

export default Register;
