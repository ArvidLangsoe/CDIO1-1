package entity;

import dto.UserDTO;
import dal.IUserDAO.DALException;
import dal.UserDAO;
import boundary.TUI;
import boundary.interfaces.IUserEditorBoundary;
import boundary.interfaces.UI;
import dto.InputException;

public class UserEditor extends TUI {

	private UI tui;
	private UserDAO userDAO;

	public UserEditor(int userId, UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void editUser() {

		UserDTO userDTO;

		try {
			userDTO = userDAO.getUser(getId());
		} catch (DALException e) {
			e.getMessage();
			return;
		}

		while (true) {
			show("Please choose an option: \n" + "1: Change the username. \n" + "2: Change the user's initials. \n"
					+ "3: Change the users CPR number. \n" + "4: Change the user's password. \n"
					+ "5: Manipulate user roles. \n" + "6: Exit the User Editor.");

			int userChoice = tui.getInt();

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
				show("Input recognized, returning to prior menu...");
				try {
					userDAO.updateUser(userDTO);
				} catch (DALException e) {
					show("Error - UserID not found in Database.");
				}

			}
		}
	}

	public void changeUserName(UserDTO userDTO) {
		while (true) {
			try {
				show("Are you sure you want to change the username for " + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");
				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					show("Please type in the new username: ");

					String changedUserName = tui.getString();

					userDTO.setUserName(changedUserName);

					show("Username updated for " + userDTO.getUserName());

					break;

				case 2:
					show("The username has not been changed for " + userDTO.getUserName()
							+ ". Returning to prior menu...");
					break;
				}

			} catch (InputException e) {
				show(e.getMessage());
			}
		}
	}

	public void changeUserIni(UserDTO userDTO) {
		while (true) {
			try {
				show("Are you sure you want to change the initials for " + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");
				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					show("Please list new user Initials (2-4 characters long): ");

					String changedUserIni = tui.getString();

					userDTO.setIni(changedUserIni);

					show("Username updated for " + userDTO.getUserName());

					break;

				case 2:
					show("The initials have not been changed for " + userDTO.getUserName()
							+ ". Returning to prior menu...");

					break;
				}

			} catch (InputException e) {
				show(e.getMessage());
			}
		}

	}

	public void changeUserCPR(UserDTO userDTO) {

		while (true) {
			try {
				show("Are you sure you want to change the CPR number for" + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");

				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					show("Please enter new user CPR number (6 numbers followed by a '-' and then 4 numbers.):");

					String changedUserCpr = tui.getString();
					while (!userDTO.isCprValid(changedUserCpr)) {
						show("The listed CPR number is not a valid input. Please list a valid input.");

					}
					userDTO.setCpr(changedUserCpr);

					show("User CPR number updated for " + userDTO.getCpr());

					break;

				case 2:
					show("The CPR number has not been changed for " + userDTO.getUserName()
							+ ". Returning to prior menu...");
					break;

				}

			} catch (InputException e) {
				show(e.getMessage());
			}
		}
	}

	public void changeUserPassword(UserDTO userDTO) {

		while (true) {
			try {
				show("Are you sure you want to change the password for " + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");

				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					show("Please enter a new password: ");

					String changedUserPassword = tui.getString();

					userDTO.setPassword(changedUserPassword);

					show("User CPR number updated for " + userDTO.getUserName());

					break;

				case 2:
					show("No new password selected. Returning to prior menu...");
					break;
				}

			} catch (InputException e) {
				show(e.getMessage());
			}
		}
	}

	public void manipulateUserRoles(UserDTO userDTO) {
		while (true) {

			show("Please select how you wish to manipulate user roles for " + userDTO.getUserName() + ". \n"
					+ "1: Add a new role. \n" + "2: Remove a role. \n" + "3: Remove *ALL* roles."
					+ "4: Exit role manipulator");
			int userChoice = tui.getInt();

			switch (userChoice) {
			case 1:
				show("Preparing to add role to user...");
				addUserRole(userDTO);
				break;

			case 2:
				show("Preparing to remove one role from user...");
				removeOneUserRole(userDTO);
				break;

			case 3:
				show("Preparing to remove all roles from user...");
				removeUserRoles(userDTO);
				break;

			case 4:
				show("Exiting role manipulator");
				break;

			}
		}
	}

	public void addUserRole(UserDTO userDTO) {

		while (true) {
			try {
				show("Are you sure you want to add a role to " + userDTO.getUserName() + "? \n" + "Type 1 for Yes. \n"
						+ "Type 2 for No.");

				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					show("Please list the desired role to be added to " + userDTO.getUserName()
							+ ": Admin, Pharmacist, Foreman or Operator.");
					String userRoleChoice = tui.getString();

					while (!userDTO.isRoleValid(userRoleChoice)) {
						show("The listed password is not a valid input. Please list a valid input.");
					}

					userDTO.addRole(userRoleChoice);
					show("Added " + userChoice + " to the list of " + userDTO.getUserName()
							+ "'s roles. Returning to prior menu...");
					break;

				case 2:
					show("No roles added to " + userDTO.getUserName());
				}
			} catch (InputException e) {
				show(e.getMessage());
			}
		}
	}

	public void removeOneUserRole(UserDTO userDTO) {
		while (true) {
			try {
				show("Are you sure you want to remove a role from " + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");

				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					show("Please specify the role you want removed from " + userDTO.getUserName() + ".");
					String roleChoice = tui.getString();
					userDTO.removeRole(roleChoice);
					show("You have succesfully removed " + roleChoice + " from " + userDTO.getUserName() + ".");
					break;

				case 2:
					show("You have chosen not to remove any roles from " + userDTO.getUserName()
							+ ". Returning to prior menu...");
					break;
				}

			} catch (InputException e) {
				show(e.getMessage());
			}
		}
	}

	public void removeUserRoles(UserDTO userDTO) {

		while (true) {

			show("Are you sure you want to remove *ALL* roles for " + userDTO.getUserName()
					+ "? This choice is not revertable. \n" + "Type 1 for Yes. \n" + "Type 2 for No.");

			int userChoice = tui.getInt();

			switch (userChoice) {
			case 1:
				userDTO.removeAllRoles();
				show("All of " + userDTO.getUserName() + "'s roles have been removed.");
				break;

			case 2:
				show("No roles have been removed from " + userDTO.getUserName() + ". Returning to prior menu...");
				break;
			}

		}
	}

	public int getId() {
		while (true) {
			try {
				show("Please specify wished userId");

				UserDTO fetchedUser = new UserDTO();

				int userInput = tui.getInt();

				fetchedUser.isUserIDValid(userInput);

				return userInput;
			}

			catch (InputException e) {
				show("Input not recognised.");
			}
		}

	}

}
