package com.parinya.backend;

import com.parinya.backend.entity.Address;
import com.parinya.backend.entity.Social;
import com.parinya.backend.entity.User;
import com.parinya.backend.exception.BaseException;
import com.parinya.backend.service.AddressService;
import com.parinya.backend.service.SocialService;
import com.parinya.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Autowired
	private SocialService socialService;

	@Autowired
	private AddressService addressService;

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
	void testCreateSocial() throws BaseException {
		Optional<User> opt = userService.findByEmail(TeatCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		Social social = user.getSocial();
		Assertions.assertNull(social);

		social = socialService.create(
				user,
				SocialTestCreateData.facebook,
				SocialTestCreateData.line,
				SocialTestCreateData.instagram,
				SocialTestCreateData.tiktok
		);

		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());

	}

	@Order(3)
	@Test
	void testCreateAddress() throws BaseException {
		Optional<User> opt = userService.findByEmail(TeatCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		List<Address> addresses = user.getAddresses();
		Assertions.assertTrue(addresses.isEmpty());

		createAddress(user, AddressTestCreateData.line1, AddressTestCreateData.line2, AddressTestCreateData.zipcode);
		createAddress(user, AddressTestCreateData2.line1, AddressTestCreateData2.line2, AddressTestCreateData2.zipcode);
	}

	private void createAddress(User user, String line1, String line2, String zipcode) {
		Address address = addressService.create(
				user,
				line1,
				line2,
				zipcode
		);

		Assertions.assertNotNull(address);
		Assertions.assertEquals(line1, address.getLine1());
		Assertions.assertEquals(line2, address.getLine2());
		Assertions.assertEquals(zipcode, address.getZipcode());
	}

	@Order(9)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TeatCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		Social social = user.getSocial();
		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());

		List<Address> addresses = user.getAddresses();
		Assertions.assertFalse(addresses.isEmpty());
		Assertions.assertEquals(2, addresses.size());


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

	interface SocialTestCreateData {
		String facebook = "parinya";

		String line = "";

		String instagram = "";

		String tiktok = "";
	}

	interface AddressTestCreateData {
		String line1 = "15/1";

		String line2 = "loei";

		String zipcode = "42110";

	}

	interface AddressTestCreateData2 {
		String line1 = "15/122";

		String line2 = "loei22";

		String zipcode = "4211022";

	}
}
