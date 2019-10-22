package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void createUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}
	
	public void createAdmin(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("ADMIN");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}
	
	public User findOne(String email) {
		return userRepository.findById(email).get();
	}

	public boolean isUserPresent(String email) {
		
		Optional<User> u = userRepository.findById(email);
		if (u.isPresent()) {
			return true;
		}
		return false;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> findByName(String name) {
		
		return userRepository.findByNameLike("%"+name+"%");
	}
}
