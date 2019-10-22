package com.example.demo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entities.Task;
import com.example.demo.entities.User;
import com.example.demo.services.TaskService;
import com.example.demo.services.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NhSystemApplicationTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@Before
	public void initDb() {
		{
		User newUser = new User("testUser@mail.com", "testUser", "123456");
		userService.createUser(newUser);
		}
		{
			User newUser = new User("testAdmin@mail.com", "testAdmin", "123456");
			userService.createUser(newUser);
		}
		
		Task userTask = new Task("16/09/2019", "15:00", "16:30", "yOU NEED TO WORK TODAY");
		User user = userService.findOne("testUser@mail.com");
		taskService.addTask(user, userTask);
	}
	
	@Test
	public void testUser() {
		User user = userService.findOne("testUser@mail.com");
		assertNotNull(user);
		User admin = userService.findOne("testAdmin@mail.com");
		assertEquals(admin.getEmail(), "testAdmin@mail.com");
	}
	
	@Test
	public void testTask() {
		User user = userService.findOne("testUser@mail.com");
		List<Task> task = taskService.findUserTask(user);
		assertNotNull(task);
	}

}
