package com.deneme.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.deneme.demo.entities.User;
import com.deneme.demo.repos.UserRepository;
import com.deneme.demo.requests.LoginRequest;
import com.deneme.demo.requests.SoftDeleteRequest;
import com.deneme.demo.requests.UpdatePasswordRequest;
import com.deneme.demo.requests.UpdateUserRequest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserServiceTest {
	
	@InjectMocks
	UserService userService ;
	
	@Mock
	UserRepository userRepository;
	
	private SoftDeleteRequest softDeleteRequest = new SoftDeleteRequest();
	private LoginRequest loginRequest = new LoginRequest();
	private Optional<User> optionalUser;
	private User user = new User();
	private User user2 = new User();
	private UpdateUserRequest updateUserRequest = new UpdateUserRequest();
	private UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
	private List<User> userList = new ArrayList<User>();
	
	@BeforeAll
    public void setup() {
		user.setId(38L);
		user.setName("ad");
		user.setSurname("soyad");
		user.setTcNo(54546151222L);
		user.setHashPassword(PasswordHashing.hashPassword("deneme"));
		user.setDeleted(false);
		
		updateUserRequest.setName("isim");
		updateUserRequest.setSurname("soyisim");
		
		optionalUser = Optional.of(user);
		
		updatePasswordRequest.setOldPassword("deneme");
		updatePasswordRequest.setNewPassword("parola");
		
		softDeleteRequest.setDeleted(true);
		
		loginRequest.setTcNo(54546151222L);
		loginRequest.setHashPassword("deneme");
		
		userList.add(user);
		userList.add(user2);
	}
	
	@AfterEach
	public void tearDown() {
		loginRequest.setTcNo(54546151222L);
		loginRequest.setHashPassword("deneme");
		updatePasswordRequest.setOldPassword("deneme");
		user.setDeleted(false);
	}
	
	
	@Test
	public void getAllUserTest() {
		when(userRepository.findAll()).thenReturn(userList);
		assertEquals(user,userService.getAllUsers().get(0));
		verify(userRepository,times(1)).findAll();
	}
	
	@Test
	public void saveOneUserNullUserTest() {
		assertEquals(null,userService.saveOneUser(user2));
	}
	
	@Test
	public void saveOneUserTrueValueTest() {
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(54546151222L,userService.saveOneUser(user).getTcNo());
	}
	@Test
	public void saveOneUserExistsValueTest() {
		when(userRepository.existsByTcNo(user.getTcNo())).thenReturn(true);
		assertEquals(null,userService.saveOneUser(user));
		verify(userRepository,times(0)).save(user);
	}
	
	@Test
	public void getOneUserNullIdTest() {
		assertEquals(null,userService.getOneUser(null));
	}
	@Test
	public void getOneUserWrongValueTest() {
		assertEquals(null,userService.getOneUser(15145L));
	}
	@Test
	public void getOneUserTrueValueTest() {
		
		when(userRepository.findById(user.getId())).thenReturn(optionalUser);
		assertEquals(user,userService.getOneUser(optionalUser.get().getId()));
	}
	
	@Test
	public void deleteUserNullIdTest() {
		userService.deleteUser(null);
		verify(userRepository,times(0)).deleteById(user.getId());
	}
	@Test
	public void deleteUserWrongIdTest() {
		when(userRepository.findById(45L)).thenReturn(Optional.empty());
		userService.deleteUser(45L);
		verify(userRepository,times(0)).deleteById(45L);
	}
	@Test
	public void deleteUserTrueValueTest() {
		when(userRepository.findById(user.getId())).thenReturn(optionalUser);
		userService.deleteUser(user.getId());
		verify(userRepository,times(1)).deleteById(user.getId());
	}
	
	@Test
	public void softDeleteUserNullIdTest() {
		
		assertEquals(null,userService.softDeleteUser(user2.getId(),softDeleteRequest));
		verify(userRepository, times(0)).save(user2);
	}
	@Test
	public void softDeleteUserDoesntExistTest() {
		
		when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
		assertEquals(null,userService.softDeleteUser(user.getId(),softDeleteRequest));
		verify(userRepository, times(0)).save(user);
	}
	
	@Test
	public void softDeleteUserTrueValueTest() {
		
		when(userRepository.findById(optionalUser.get().getId())).thenReturn(optionalUser);
		when(userRepository.save(optionalUser.get())).thenReturn(optionalUser.get());
		assertEquals(true,userService.softDeleteUser(optionalUser.get().getId(),softDeleteRequest).isDeleted());
		verify(userRepository, times(1)).save(optionalUser.get());
	}
	
	@Test
	public void loginUserTrueValueTest() {
		when(userRepository.findByTcNo(loginRequest.getTcNo())).thenReturn(user);
		assertEquals(user,userService.loginUser(loginRequest));
		verify(userRepository, times(1)).findByTcNo(loginRequest.getTcNo());
	}
	
	@Test
	public void loginUserNullTcNoTest() {
		loginRequest.setTcNo(null);
		when(userRepository.findByTcNo(loginRequest.getTcNo())).thenReturn(null);
		assertEquals(null,userService.loginUser(loginRequest));
	}
	
	@Test
	public void loginUserNullPasswordTest() {
		loginRequest.setHashPassword(null);
		when(userRepository.findByTcNo(loginRequest.getTcNo())).thenReturn(user);
		assertEquals(null,userService.loginUser(loginRequest));
	}
	
	@Test
	public void loginUserWrongPasswordTest() {
		loginRequest.setHashPassword("dneme");
		when(userRepository.findByTcNo(loginRequest.getTcNo())).thenReturn(user);
		assertEquals(null,userService.loginUser(loginRequest));
	}
	@Test
	public void loginUserWrongTcNoTest() {
		loginRequest.setTcNo(1L);
		when(userRepository.findByTcNo(loginRequest.getTcNo())).thenReturn(null);
		assertEquals(null,userService.loginUser(loginRequest));
	}
	@Test
	public void loginUserDeletedUserTest() {
		user.setDeleted(true);
		when(userRepository.findByTcNo(loginRequest.getTcNo())).thenReturn(user);
		assertEquals(null,userService.loginUser(loginRequest));
	}
	
	@Test
	public void updateUserNullIdTest() {
		
		assertEquals(null,userService.updateUser(null,updateUserRequest));
		verify(userRepository, times(0)).save(user);
	}
	@Test
	public void updateUserWrongIdTest() {
		
		when(userRepository.findById(1L)).thenReturn(Optional.empty());
		assertEquals(null,userService.updateUser(user.getId(),updateUserRequest));
		verify(userRepository, times(0)).save(user);
	}
	@Test
	public void updateUserTrueValueTest() {
		
		when(userRepository.findById(optionalUser.get().getId())).thenReturn(optionalUser);
		when(userRepository.save(optionalUser.get())).thenReturn(optionalUser.get());
		assertEquals("isim",userService.updateUser(optionalUser.get().getId(),updateUserRequest).getName());
		verify(userRepository, times(1)).save(optionalUser.get());
	}
	
	@Test
	public void updatePasswordNullIdTest() {
		
		assertEquals(null,userService.updatePassword(null,updatePasswordRequest));
		verify(userRepository, times(0)).save(user);
	}
	
	@Test
	public void updatePasswordWrongIdTest() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());
		assertEquals(null,userService.updatePassword(user.getId(),updatePasswordRequest));
		verify(userRepository, times(0)).save(user);
	}
	
	@Test
	public void updatePasswordWrongPasswordTest() {
		updatePasswordRequest.setOldPassword("hatalıParola");
		when(userRepository.findById(optionalUser.get().getId())).thenReturn(optionalUser);
		assertEquals(null,userService.updatePassword(optionalUser.get().getId(),updatePasswordRequest));
		verify(userRepository, times(0)).save(user);
	}
	
	@Test
	public void updatePasswordTrueValueTest() {
		
		when(userRepository.findById(optionalUser.get().getId())).thenReturn(optionalUser);
		when(userRepository.save(optionalUser.get())).thenReturn(optionalUser.get());
		assertEquals(PasswordHashing.hashPassword("parola"),userService.updatePassword(optionalUser.get().getId(),updatePasswordRequest).getHashPassword());
		verify(userRepository, times(1)).save(optionalUser.get());
	}
	
}
