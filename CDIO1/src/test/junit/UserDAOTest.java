package test.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccessObjects.IUserDAO.DALException;
import dataAccessObjects.UserDAO;
import dataTransferObjects.UserDTO;

public class UserDAOTest {

	UserDAO data;

	@Before
	public void setUp() throws Exception {
		data = new UserDAO(null);
	}

	@After
	public void tearDown() throws Exception {
		data = null;
	}

	@Test
	public void testGetUser() {
		UserDTO user = new UserDTO();
		user.setUserId(11);
		user.setCpr("1111111118");
		user.setPassword("6Fy2g3sg43");
		user.setIni("ini");
		user.setUserName("JUfoegew ew");
		try {
			data.createUser(user);
		} catch (DALException e) {
			e.printStackTrace();
			fail("User wasn't created in the data.");
		}

		try {
			data.getUser(11);
		} catch (DALException e) {
			fail("Couldn't find user in data, but the user should be there.");
		}
		try {
			data.getUser(10);
			fail("Found a user that shouldn't exist in the data.");
		} catch (DALException e) {

		}

	}

	@Test
	public void testGetUserList() {
		ArrayList<UserDTO> userList = createData();
		addDataToUserDAO(createData(), data);

		List<UserDTO> dataUserList = null;
		try {
			dataUserList = data.getUserList();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("");
		}

		for (int i = 0; i < userList.size(); i++) {
			assertTrue("The following users were not the same:\n" + userList.get(i) + "\n" + dataUserList.get(i),
					dataUserList.get(i).equals(userList.get(i)));
		}

	}

	@Test
	public void testCreateUser() {
		UserDTO user = new UserDTO();
		user.setUserId(11);
		user.setCpr("1111111118");
		user.setPassword("6Fy2g3sg43");
		user.setIni("ini");
		user.setUserName("JUfoegew ew");
		try {
			data.createUser(user);
		} catch (DALException e) {
			fail("User could not be created.");
		}
	}

	@Test
	public void testDeleteUser() {
		addDataToUserDAO(createData(), data);

		try {
			data.deleteUser(11);
		} catch (DALException e) {
			fail("Failed to delete user 11, who should exist.");
		}

		try {
			data.deleteUser(13);
			fail("Deleted user 13, who should not exist.");
		} catch (DALException e) {

		}

	}

	@Test
	public void testUpdateUser() {
		addDataToUserDAO(createData(), data);

		UserDTO user = null;

		try {
			user = data.getUser(11);
		} catch (DALException e) {
			fail("Failed to load userid 11 that should have existed.");
		}

		user.setCpr("4346454443");

		try {
			data.updateUser(user);
		} catch (DALException e) {
			fail("Dal exception when trying to update user.");
		}

		try {
			assertTrue("The following users were not the same:\n" + data.getUser(11) + "\n" + user,
					data.getUser(11).equals(user));
		} catch (DALException e) {
			fail("User with id 11 coudl not be found.");
		}

	}

	@Test
	public void testLoadUsers() {
		ArrayList<UserDTO> users = createData();

		try {
			data.saveUsers(users);
		} catch (DALException e) {
			fail("The file couldn't be saved.");
		}

		ArrayList<UserDTO> usersLoadData = null;
		try {
			usersLoadData = data.loadUsers();

		} catch (DALException e) {
			fail("The file couldn't be loaded.");
		}

		for (int i = 0; i < users.size(); i++) {
			assertTrue("The following users were not the same:\n" + users.get(i) + "\n" + usersLoadData.get(i),
					usersLoadData.get(i).equals(users.get(i)));
		}

	}

	@Test
	public void testSaveUsers() {
		ArrayList<UserDTO> users = createData();

		try {
			data.saveUsers(users);
		} catch (DALException e) {
			fail("The file couldn't be saved.");
		}
	}

	private void addDataToUserDAO(ArrayList<UserDTO> inputData, UserDAO data) {
		for (int i = 0; i < inputData.size(); i++) {
			try {
				data.createUser(inputData.get(i));
			} catch (DALException e) {
				fail("Couldt add input data to UserDAO");
				e.printStackTrace();
			}
		}

	}

	private ArrayList<UserDTO> createData() {
		UserDTO user1 = new UserDTO();
		user1.setUserId(11);
		user1.setCpr("1111111118");
		user1.setPassword("6Fy2g3sg43");
		user1.setIni("ini");
		user1.setUserName("JUfoegew ew");
		user1.addRole("Admin");

		UserDTO user2 = new UserDTO();
		user2.setUserId(54);
		user2.setCpr("1243111118");
		user2.setPassword("G4gEf24gq3f13f23");
		user2.setIni("in2");
		user2.setUserName("qgqegqeg");
		user2.addRole("Operator");

		UserDTO user3 = new UserDTO();
		user3.setUserId(99);
		user3.setCpr("1243111618");
		user3.setPassword("gEf2413f23");
		user3.setIni("jjf");
		user3.setUserName("jeej teej ertj");
		user3.addRole("Operator");

		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		users.add(user1);
		users.add(user2);
		users.add(user3);

		return users;
	}

}
