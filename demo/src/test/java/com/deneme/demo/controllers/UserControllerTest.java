package com.deneme.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.deneme.demo.entities.User;
import com.deneme.demo.requests.SoftDeleteRequest;
import com.deneme.demo.services.UserService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserControllerTest {
		
		UserService userService = Mockito.mock(UserService.class);
		UserController userController;
		SoftDeleteRequest softDeleteRequest = Mockito.mock(SoftDeleteRequest.class);
		User user = new User();
		User user2 = new User();
		List<User> userList = new ArrayList<User>();
		@BeforeAll
	    public void setup() {
			userController = new UserController(userService);
			user.setId(38L);
			user.setTcNo(54546151222L);
			user.setDeleted(false);
			softDeleteRequest.setDeleted(true);
			
			userList.add(user);
			userList.add(user2);
		}
		
		@Test
		public void getOneUserTest() {
			when(userService.getOneUser(38L)).thenReturn(user);
			assertEquals(54546151222L,userController.getOneUser(38L).getTcNo());
			verify(userService).getOneUser(38L);
		}
		
		@Test
		public void softDeleteUserTest() {
			user.setDeleted(true);
			when(userService.softDeleteUser(38L,softDeleteRequest)).thenReturn(user);
			assertEquals(true,userController.softDeleteUser(38L,softDeleteRequest).isDeleted());
			verify(userService).softDeleteUser(38L, softDeleteRequest);
		}
		
		@Test
		public void getAllUserTest(){
			when(userService.getAllUsers()).thenReturn(Arrays.asList(user,user2));
			assertEquals(2,userController.getAllUser().size());
			verify(userService).getAllUsers();
		}
		
		@Test
		public void deleteUserTest() {
			userController.deleteUser(38L);
			verify(userService).deleteUser(38L);
		}
}
