package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Task;
import com.example.demo.entities.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByUser(User user);
	
	

}
