package com.parinya.backend;

import com.parinya.backend.entity.User;
import com.parinya.backend.exception.BaseException;
import com.parinya.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Order(1)
	@Test
	void testCreate() throws BaseException {
		User user = userService.create(
				TeatCreateData.email,
				TeatCreateData.password,
				TeatCreateData.name
		);

		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getName());

		Assertions.assertEquals(TeatCreateData.email, user.getEmail());

		boolean isMatched = userService.matchPassword(TeatCreateData.password, user.getPassword());
		Assertions.assertTrue(isMatched);

		Assertions.assertEquals(TeatCreateData.name, user.getName());
	}

	@Order(2)
	@Test
	void testUpdate() throws BaseException {
		Optional<User> opt = userService.findByEmail(TeatCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		User updatedUser = userService.updateName(user.getId(), TeatUpdateData.name);

		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(TeatUpdateData.name, updatedUser.getName());
	}

	@Order(3)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TeatCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		userService.deleteById(user.getId());

		Optional<User> optDelete = userService.findByEmail(TeatCreateData.email);
		Assertions.assertTrue(optDelete.isEmpty());
	}

	interface TeatCreateData {
		String email = "parinya@gmail.com";
		String password = "1234";
		String name = "parinya Yakanta";
	}

	interface TeatUpdateData {
		String name = "Yakanta Parinya";
	}

}
