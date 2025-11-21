import React from "react";
import { Button, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <div style={{ textAlign: "center", marginTop: "80px" }}>
      <Typography variant="h4" gutterBottom>
        Welcome to Smart Expense Tracker 🎯
      </Typography>
      <Typography variant="subtitle1" gutterBottom>
        Manage your expenses and track your finances easily!
      </Typography>

      <Button
        variant="contained"
        style={{ marginTop: "20px" }}
        onClick={handleLogout}
      >
        Logout
      </Button>
    </div>
  );
};

export default Dashboard;
