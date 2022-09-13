package com.dailymeows.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailymeows.models.Meow;
import com.dailymeows.models.User;
import com.dailymeows.repositories.MeowRepository;

@Service
public class MeowService {
	@Autowired
	private MeowRepository meowRepository;
	
	public void createMeow(
			String headline,
			String location,
			String image,
			String details,
			User user) {
		Meow meow = new Meow(headline, location, image, details, user);
		meowRepository.save(meow);
	}
	
	public List<Meow> allMeows() {
		return meowRepository.findAll();
	}
	
	public List<Meow> getAll() {
		return meowRepository.findAll();
	}
	
	public Meow findMeow(Long id) {
		Optional<Meow> optionalMeow = meowRepository.findById(id);
		if (optionalMeow.isPresent()) {
			return optionalMeow.get();
		} else {
			return null;
		}
	}
	
	public Meow updateMeow(Meow meow) {
		return meowRepository.save(meow);
	}
	
	public void deleteMeow(Long id) {
		meowRepository.deleteById(id);
	}
	
	
	
}