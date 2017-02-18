package boundary;

import dto.*;
import dto.Validator.InputException;
import dal.*;
import dal.IUserDAO.DALException;

public class UserEditor extends TUI {

	private IUserDAO userDAO;

	public UserEditor(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void editUser() {

		UserDTO userDTO;

		try {
			userDTO = userDAO.getUser(getId());
			show("The following user was found:");
			show(userDTO.toString());
		} catch (DALException e) {
			e.getMessage();
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

				} catch (DALException e) {
					show(e.getMessage());
					e.getStackTrace();
				}

				return;
			}
		}
	}

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
			// show("The username has not been changed for " +
			// userDTO.getUserName() + ". Returning to prior menu...");
			return;
		}

	}

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
			// show("The initials have not been changed for " +
			// userDTO.getUserName()
			// + ". Returning to prior menu...");

			break;
		}

	}

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
			// show("The CPR number has not been changed for " +
			// userDTO.getUserName()
			// + ". Returning to prior menu...");
			return;

		}

	}

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
			// show("No new password selected. Returning to prior menu...");
			return;
		}

	}

	public void manipulateUserRoles(UserDTO userDTO) {
		while (true) {

			show("Please select how you wish to manipulate user roles for " + userDTO.getUserName() + ". \n"
					+ "1: Add a new role. \n" + "2: Remove a role. \n" + "3: Remove *ALL* roles.\n"
					+ "4: Exit role manipulator");
			int userChoice = getInt();

			switch (userChoice) {
			case 1:
				// show("Preparing to add role to user...");
				addUserRole(userDTO);
				break;

			case 2:
				// show("Preparing to remove one role from user...");
				removeOneUserRole(userDTO);
				break;

			case 3:
				// show("Preparing to remove all roles from user...");
				removeUserRoles(userDTO);
				break;

			case 4:
				// show("Exiting role manipulator...");
				return;

			default:
				show("That is not a valid input, please choose an option above.");
				break;
			}
		}
	}

	public void addUserRole(UserDTO userDTO) {

		show("Are you sure you want to add a role to " + userDTO.getUserName() + "? \n" + "Type 1 for Yes. \n"
				+ "Type 2 for No.");

		int userChoice = getInt();

		switch (userChoice) {
		case 1:
			while (true) {
				String roles="";
				for(int i =0;i<Validator.validRoles.length-1;i++){
					roles+=Validator.validRoles[i]+", ";
				}
				roles+= "or "+Validator.validRoles[Validator.validRoles.length-1];
				
				show("Please list the desired role to be added to " + userDTO.getUserName()
						+ ": "+roles);

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
			// show("No roles added to " + userDTO.getUserName());
			return;
		}

	}

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
			// show("You have chosen not to remove any roles from " +
			// userDTO.getUserName()
			// + ". Returning to prior menu...");
			return;
		}

	}

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
			// show("No roles have been removed from " + userDTO.getUserName() +
			// ". Returning to prior menu...");
			return;
		}

	}

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
