package presentationLayer;

import dataAccessObjects.IUserDAO.DALException;
import dataTransferObjects.UserDTO;
import functionLayer.IDataVerifier;
import functionLayer.IDataVerifier.WrongDataException;
import staticClasses.Validator;
import staticClasses.Validator.InputException;

/**
 * The class UserEditor extends TUI. The responsibility of this class is to
 * carry out the tasks of editing a user from the data.
 * 
 * @author Group 22.
 *
 */
public class UserEditor extends TUI {

	// Instance variables.
	private IDataVerifier userDAO;

	/**
	 * Constructor
	 * 
	 * @param IDataVerifier
	 *            The data access object to use.
	 */
	public UserEditor(IDataVerifier userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * Edits a user (UserDTO) by asking the user Administrator for the user ID
	 * of the user to be edited and afterwards what he wants to edit.
	 */
	public void editUserMenu() {

		UserDTO userDTO;

		try {
			userDTO = userDAO.getUser(getId());
			show("The following user was found:");
			show(userDTO.toString());
		} catch (DALException e) {
			show(e.getMessage());
			return;
		}

		while (true) {
			show("Please choose an option: \n" + "1: Change the username. \n" + "2: Change the user's initials. \n"
					+ "3: Change the users CPR number. \n" + "4: Change the user's password. \n"
					+ "5: Manipulate user roles. \n" + "6: Exit the User Editor.");

			int userChoice = getInt();

			switch (userChoice) {
			case 1:
				changeUserName(userDTO);
				break;
			case 2:
				changeUserIni(userDTO);
				break;
			case 3:
				changeUserCPR(userDTO);
				break;
			case 4:
				changeUserPassword(userDTO);
				break;
			case 5:
				manipulateUserRoles(userDTO);
				break;

			// EXIT TO THE MAIN CONTROLLER HERE
			case 6:
				try {
					userDAO.updateUser(userDTO);
					show("Succesfully updated the user.");
				} catch (WrongDataException e) {
					show(e.getMessage());
					e.getStackTrace();
				}
				return;
			}
		}
	}

	/**
	 * Changes the username of the user (UserDTO) if the user administrator
	 * wants to and if the new username is valid.
	 * 
	 * @param userDTO
	 *            The user to change the username of.
	 */
	public void changeUserName(UserDTO userDTO) {

		show("Are you sure you want to change the username for " + userDTO.getUserName() + "? \n" + "Type 1 for Yes. \n"
				+ "Type 2 for No.");
		int userChoice = getInt();

		switch (userChoice) {
		case 1:
			while (true) {
				show("Please type in the new username: ");
				try {
					String changedUserName = getString();
					Validator.validateUsername(changedUserName);
					userDTO.setUserName(changedUserName);
					show("Username updated for " + userDTO.getUserName());
					return;
				} catch (InputException e) {
					show(e.getMessage());
				}
			}
		case 2:
			return;
		}
	}

	/**
	 * Changes the user initials of the user (UserDTO) If the user Administrator
	 * wants to and if the initials are valid.
	 * 
	 * @param userDTO
	 *            The user to change the initials of.
	 */
	public void changeUserIni(UserDTO userDTO) {
		show("Are you sure you want to change the initials for " + userDTO.getUserName() + "? \n" + "Type 1 for Yes. \n"
				+ "Type 2 for No.");
		int userChoice = getInt();

		switch (userChoice) {
		case 1:
			while (true) {
				show("Please list new user Initials (2-4 characters long): ");
				try {
					String changedUserIni = getString();
					Validator.validateInitials(changedUserIni);
					userDTO.setIni(changedUserIni);
					show("Username updated for " + userDTO.getUserName());
					return;
				} catch (InputException e) {
					show(e.getMessage());
				}
			}
		case 2:
			return;
		}
	}

	/**
	 * Changes the CPR of the user (UserDTO) if the user Administrator wants to
	 * and if the CPR is valid.
	 * 
	 * @param userDTO
	 *            The user to change the CPR of.
	 */
	public void changeUserCPR(UserDTO userDTO) {

		show("Are you sure you want to change the CPR number for" + userDTO.getUserName() + "? \n"
				+ "Type 1 for Yes. \n" + "Type 2 for No.");

		int userChoice = getInt();

		switch (userChoice) {
		case 1:
			while (true) {
				show("Please enter new user CPR number (6 numbers followed by a '-' and then 4 numbers.):");
				try {
					String changedUserCpr = getString();
					Validator.validateCPR(changedUserCpr);
					userDTO.setCpr(changedUserCpr);
					show("User CPR number updated for " + userDTO.getCpr());
					return;
				} catch (InputException e) {
					show(e.getMessage());
				}
			}
		case 2:
			return;
		}
	}

	/**
	 * Changes the password of the user (UserDTO) if the user Administrator
	 * wants to and if the password is valid.
	 * 
	 * side note.
	 * If we are talking about security, the user administrator should not be
	 * allowed to change a user password, instead if the user has forgotten his
	 * password, he should ask for a password reset and then get a message (e.g email) with an
	 * auto-generated password.
	 * 
	 * @param userDTO
	 *            The user to change the password of.
	 */
	public void changeUserPassword(UserDTO userDTO) {

		show("Are you sure you want to change the password for " + userDTO.getUserName() + "? \n" + "Type 1 for Yes. \n"
				+ "Type 2 for No.");

		int userChoice = getInt();

		switch (userChoice) {
		case 1:
			while (true) {
				show("Please enter a new password: ");
				try {
					String changedUserPassword = getString();
					Validator.validatePassword(changedUserPassword);
					userDTO.setPassword(changedUserPassword);
					show("User CPR number updated for " + userDTO.getUserName());
					return;
				} catch (InputException e) {
					show(e.getMessage());
				}
			}
		case 2:
			return;
		}
	}

	/**
	 * Manipulates the roles of the given user (UserDTO).
	 * 
	 * @param userDTO
	 *            The user to change the roles of.
	 */
	public void manipulateUserRoles(UserDTO userDTO) {
		while (true) {
			show("Please select how you wish to manipulate user roles for " + userDTO.getUserName() + ". \n"
					+ "1: Add a new role. \n" + "2: Remove a role. \n" + "3: Remove *ALL* roles.\n"
					+ "4: Exit role manipulator");
			int userChoice = getInt();

			switch (userChoice) {
			case 1:
				addUserRole(userDTO);
				break;
			case 2:
				removeOneUserRole(userDTO);
				break;
			case 3:
				removeUserRoles(userDTO);
				break;
			case 4:
				return;
			default:
				show("That is not a valid input, please choose an option above.");
				break;
			}
		}
	}

	/**
	 * Adds a user role to the user (UserDTO) if the user administrator wants to
	 * and if the role entered is valid.
	 * 
	 * @param userDTO
	 *            The user to add the role to.
	 */
	public void addUserRole(UserDTO userDTO) {
		show("Are you sure you want to add a role to " + userDTO.getUserName() + "? \n" + "Type 1 for Yes. \n"
				+ "Type 2 for No.");

		int userChoice = getInt();

		switch (userChoice) {
		case 1:
			while (true) {
				String roles = "";
				for (int i = 0; i < Validator.validRoles.length - 1; i++) {
					roles += Validator.validRoles[i] + ", ";
				}
				roles += "or " + Validator.validRoles[Validator.validRoles.length - 1];

				show("Please list the desired role to be added to " + userDTO.getUserName() + ": " + roles);
				try {
					String userRoleChoice = getString();
					Validator.validateRole(userRoleChoice);
					userDTO.addRole(userRoleChoice);
					show("Added " + userChoice + " to the list of " + userDTO.getUserName()
							+ "'s roles. Returning to prior menu...");
					return;
				} catch (InputException e) {
					show(e.getMessage());
				}
			}
		case 2:
			return;
		}
	}

	/**
	 * Removes a user role from the user (UserDTO) if the user administrator
	 * wants to and if the role exists.
	 * 
	 * @param userDTO
	 *            The user to remove the role from.
	 */
	public void removeOneUserRole(UserDTO userDTO) {
		show("Are you sure you want to remove a role from " + userDTO.getUserName() + "? \n" + "Type 1 for Yes. \n"
				+ "Type 2 for No.");

		int userChoice = getInt();

		switch (userChoice) {
		case 1:
			while (true) {
				show("Please specify the role you want removed from " + userDTO.getUserName() + ".");
				try {
					String roleChoice = getString();
					Validator.validateRole(roleChoice);
					userDTO.removeRole(roleChoice);
					show("You have succesfully removed a role from " + userDTO.getUserName() + ".");
					return;
				} catch (InputException e) {
					show(e.getMessage());
				}
			}
		case 2:
			return;
		}
	}

	/**
	 * Removes all the the roles from the user (UserDTO) if the user
	 * administrator wants to.
	 * 
	 * @param userDTO
	 *            The user to remove the roles from.
	 */
	public void removeUserRoles(UserDTO userDTO) {
		show("Are you sure you want to remove *ALL* roles for " + userDTO.getUserName()
				+ "? This choice is not revertable. \n" + "Type 1 for Yes. \n" + "Type 2 for No.");

		int userChoice = getInt();

		switch (userChoice) {
		case 1:
			userDTO.removeAllRoles();
			show("All of " + userDTO.getUserName() + "'s roles have been removed.");
			return;
		case 2:
			return;
		}
	}

	/**
	 * Asks the user administrator to enter the user ID of the user to be
	 * edited.
	 * 
	 * @return The user ID of the user to be edited.
	 */
	public int getId() {
		while (true) {
			show("Please specify wished userId");
			while (true) {
				try {
					int userInput = getInt();
					Validator.validateUserID(userInput);
					return userInput;
				}
				catch (InputException e) {
					show(e.getMessage());
				}
			}
		}
	}
}
