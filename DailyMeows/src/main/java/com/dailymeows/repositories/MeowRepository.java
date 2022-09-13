package com.dailymeows.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dailymeows.models.Meow;
import com.dailymeows.models.User;

@Repository
public interface MeowRepository extends CrudRepository<Meow, Long> {
	List<Meow> findAll();
	
	List<Meow> findAllByUser(User user);
	
}