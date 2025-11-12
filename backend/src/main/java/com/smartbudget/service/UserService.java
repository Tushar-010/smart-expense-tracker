package com.smartbudget.service;

import java.util.List;
import java.util.Optional;

import com.smartbudget.entity.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public List<User> getAllUsers();
	
	public Optional<User> getUserById(Long id);
	
	public void deleteUser(Long id);
}
