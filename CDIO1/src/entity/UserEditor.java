package entity;

import dal.UserDAO;
import dto.UserDTO;
import controller.UserEditorController;
import boundary.interfaces.IUserEditorBoundary;
import dto.InputException;
import dal.IUserDAO.DALException;

public class UserEditor implements IUserEditorBoundary {

	private UserDAO userDAO;
	private UserEditorController userEditorController;

	public UserEditor(UserDAO userDAO, UserEditorController userEditorController) {
		this.userDAO = userDAO;
		this.userEditorController = userEditorController;

	}

	public void changeUserName(UserDTO userDTO) {
		while (true) {

			try {
				System.out.println("Please list new username:");

				String changedUserName = tui.getString;

				userDTO.setUserName(changedUserName);

				System.out.println("Username updated to " + userDTO.getUserName());
				return;
			} catch (DALException e) {
				System.out.println("There exists no user with the specified user: " + userDTO);
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void changeUserIni(UserDTO userDTO) {
		while (true) {
			try {
				System.out.println("Please list new user Initials (2-4 characters long): ");

				String changedUserIni = tui.getString;

				userDTO.setIni(changedUserIni);

				System.out.println("Username updated to " + userDTO.getUserName());

			} catch (DALException e) {
				System.out.println("There exists no user with the specified user ID: " + userDTO);
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void changeUserCPR(int userId, String changedUserCPR) throws DALException, InputException {
		try {
			UserDTO user = userDAO.getUser(userId);

			user.setCpr(changedUserCPR);

			userDAO.updateUser(user);

			System.out.println("User CPR number changed to " + user.getCpr());
		} catch (DALException e) {
			System.out.println("THere exists no user with the specified user ID: " + userId);
		} catch (InputException e) {
			System.out.println("Input not recognised.");
		}
	}

	public void changeUserPassword(int userId, String ChangedUserPassword) {
		try {
			UserDTO user = userDAO.getUser(userId);

			user.setPassword(ChangedUserPassword);

			userDAO.updateUser(user);
		} catch (DALException e) {
			System.out.println("THere exists no user with the specified user ID: " + userId);
		} catch (InputException e) {
			System.out.println("Input not recognised.");
		}

	}

	public void removeUserRoles(int userId) throws DALException, InputException {
		try {
			UserDTO user = userDAO.getUser(userId);

			user.removeAllRoles();

			userDAO.updateUser(user);

			System.out.println("All of " + user.getUserName() + "'s roles have been removed.");
		} catch (DALException e) {
			System.out.println("There exists no user with the specified user ID: " + userId);
		}
	}

	public void removeOneUserRole(int userId, String role) throws DALException, InputException {
		try {
			UserDTO user = userDAO.getUser(userId);

			user.removeRole(role);

			userDAO.updateUser(user);

			System.out.println("Removed " + role + "from the list of " + userId + "'s roles.");
		} catch (DALException e) {
			System.out.println("There exists no user with the specified user ID: " + userId);
		} catch (InputException e) {
			System.out.println("Input not recognised.");
		}
	}

	public void addUserRole(int userId, String role) throws DALException, InputException {
		try {
			UserDTO user = userDAO.getUser(userId);

			user.addRole(role);

			userDAO.updateUser(user);

			System.out.println("Added " + role + " to the list of " + userId + "'s roles.");
		} catch (DALException e) {
			System.out.println("There exists no user with the specified user ID: " + userId);
		} catch (InputException e) {
			System.out.println("Input not recognised.");
		}
	}

	@Override
	public int getId() {
		while (true) {
			try {
				System.out.println("Please specify wished userId");

				UserDTO fetchedUser = new UserDTO();

				int userInput = tui.getInt();

				fetchedUser.isUserIDValid(userInput);

				return userInput;
			}

			catch (InputException e) {
				System.out.println("Input not recognised.");
			}
		}

	}

	@Override
	public UserDTO editUser(UserDTO userDTO) {

		System.out.println(
				"Please choose an option: \n" + "1: Change the username. \n" + "2: Change the user's initials. \n"
						+ "3: Change the user's password. \n" + "4: Manipulate user roles.");

		int userChoice = tui.getInt;

		switch (userChoice) {
		case 1:
			changeUserName(userDTO);
			break;

		case 2:
			changeUserIni(userDTO);
			break;

		case 3:

			break;

		case 4:

			break;

		case 5:

			break;
		}
		return null;
	}
}
