package entity;

import dal.UserDAO;
import dto.UserDTO;
import controller.UserEditorController;
import dto.InputException;
import dal.IUserDAO.DALException;

public class UserEditor {

	private UserDAO userDAO;
	private UserEditorController userEditorController;

	public UserEditor(UserDAO userDAO, UserEditorController userEditorController) {
		this.userDAO = userDAO;
		this.userEditorController = userEditorController;

	}

	private void changeUserName(int userId, String changedUserName) throws DALException, InputException {
		try {
			UserDTO user = userDAO.getUser(userId);

			user.setUserName(changedUserName);

			userDAO.updateUser(user);

			System.out.println("Username updated to " + user.getUserName());

		} catch (DALException e) {
			System.out.println("There exists no user with the specified user ID: " + userId);
		} catch (InputException e) {
			System.out.println("Input not recognised.");
		}
	}

	private void changeUserIni(int userId, String changedUserIni) throws DALException, InputException {
		try {
			UserDTO user = userDAO.getUser(userId);

			user.setIni(changedUserIni);

			userDAO.updateUser(user);

			System.out.println("Username updated to " + user.getUserName());

		} catch (DALException e) {
			System.out.println("There exists no user with the specified user ID: " + userId);
		} catch (InputException e) {
			System.out.println("Input not recognised.");
		}
	}

	private void changeUserCPR(int userId, String changedUserCPR) throws DALException, InputException {
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

	private void changeUserPassword(int userId, String ChangedUserPassword) {
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

	private void removeUserRoles(int userId) throws DALException, InputException {
		try {
			UserDTO user = userDAO.getUser(userId);

			user.removeAllRoles();

			userDAO.updateUser(user);

			System.out.println("All of " + user.getUserName() + "'s roles have been removed.");
		} catch (DALException e) {
			System.out.println("There exists no user with the specified user ID: " + userId);
		}
	}

	private void removeOneUserRole(int userId, String role) throws DALException, InputException {
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

	private void addUserRole(int userId, String role) throws DALException, InputException {
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
}
