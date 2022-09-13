package com.dailymeows.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.dailymeows.models.LoginUser;
import com.dailymeows.models.User;
import com.dailymeows.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User register(User newUser, BindingResult result) {
		Optional<User> optionalUser = userRepository.findByEmail(newUser.getEmail());
		if (optionalUser.isPresent()) {
			result.rejectValue("email", "Unique", "An Account with this Email already exists. Log in or use a different Email.");
		}
//		if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
//			result.rejectValue("confirmPassword", "Matches", "Password Confirmation must match Password.");
//		}
		if (result.hasErrors()) {
			return null;
		}
		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPassword);
		return userRepository.save(newUser);
	}
	
	public User login(LoginUser newLogin, BindingResult result) {
		Optional<User> optionalUser = userRepository.findByEmail(newLogin.getEmail());
		if(!optionalUser.isPresent()) {
			result.rejectValue("email", "Matches", "Invalid log in.");
			return null;
		}
		User user = optionalUser.get();
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid log in.");
		}
		if (result.hasErrors()) {
			return null;
		}
		return user;
	}
	
	public User findById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}
	
	public User findByEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}
	
	public User getById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}
	
}