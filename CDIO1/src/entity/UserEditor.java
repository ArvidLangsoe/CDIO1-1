package entity;

import dto.UserDTO;
import boundary.interfaces.IUserEditorBoundary;
import boundary.interfaces.UI;
import dto.InputException;

public class UserEditor implements IUserEditorBoundary {

	private UI tui;

	public void changeUserName(UserDTO userDTO) {
		while (true) {
			try {
				System.out.println("Are you sure you want to change the username for " + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");
				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					System.out.println("Please type in the new username: ");

					String changedUserName = tui.getString();
					
					userDTO.setUserName(changedUserName);

					System.out.println("Username updated for " + userDTO.getUserName());

					break;

				case 2:
					System.out.println("The username has not been changed for " + userDTO.getUserName()
							+ ". Returning to prior menu...");
					break;
				}

			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void changeUserIni(UserDTO userDTO) {
		while (true) {
			try {
				System.out.println("Are you sure you want to change the initials for " + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");
				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					System.out.println("Please list new user Initials (2-4 characters long): ");

					String changedUserIni = tui.getString();

					userDTO.setIni(changedUserIni);

					System.out.println("Username updated for " + userDTO.getUserName());

					break;

				case 2:
					System.out.println("The initials have not been changed for " + userDTO.getUserName()
							+ ". Returning to prior menu...");

				break;
				}

			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void changeUserCPR(UserDTO userDTO) {

		while (true) {
			try {
				System.out.println("Are you sure you want to change the CPR number for" + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");

				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					System.out.println(
							"Please enter new user CPR number (6 numbers followed by a '-' and then 4 numbers.):");

					String changedUserCpr = tui.getString();
					while (!userDTO.isCprValid(changedUserCpr)) {
						System.out.println("The listed CPR number is not a valid input. Please list a valid input.");

					}
					userDTO.setCpr(changedUserCpr);

					System.out.println("User CPR number updated for " + userDTO.getCpr());

					break;

				case 2:
					System.out.println("The CPR number has not been changed for " + userDTO.getUserName()
							+ ". Returning to prior menu...");
					break;

				}

			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void changeUserPassword(UserDTO userDTO) {

		while (true) {
			try {
				System.out.println("Are you sure you want to change the password for " + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");

				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					System.out.println("Please enter a new password: ");

					String changedUserPassword = tui.getString();

					userDTO.setPassword(changedUserPassword);

					System.out.println("User CPR number updated for " + userDTO.getUserName());

					break;

				case 2:
					System.out.println("No new password selected. Returning to prior menu...");
					break;
				}

			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void manipulateUserRoles(UserDTO userDTO) {
		while (true) {

			System.out.println("Please select how you wish to manipulate user roles for " + userDTO.getUserName()
					+ ". \n" + "1: Add a new role. \n" + "2: Remove a role. \n" + "3: Remove *ALL* roles."
					+ "4: Exit role manipulator");
			int userChoice = tui.getInt();

			switch (userChoice) {
			case 1:
				System.out.println("Preparing to add role to user...");
				addUserRole(userDTO);
				break;

			case 2:
				System.out.println("Preparing to remove one role from user...");
				removeOneUserRole(userDTO);
				break;

			case 3:
				System.out.println("Preparing to remove all roles from user...");
				removeUserRoles(userDTO);
				break;

			case 4:
				System.out.println("Exiting role manipulator");
				editUser(userDTO);
				break;

			}
		}
	}

	public void addUserRole(UserDTO userDTO) {

		while (true) {
			try {
				System.out.println("Are you sure you want to add a role to " + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");

				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					System.out.println("Please list the desired role to be added to " + userDTO.getUserName()
							+ ": Admin, Pharmacist, Foreman or Operator.");
					String userRoleChoice = tui.getString();

					while (!userDTO.isRoleValid(userRoleChoice)) {
						System.out.println("The listed password is not a valid input. Please list a valid input.");
					}

					userDTO.addRole(userRoleChoice);
					System.out
							.println("Added " + userChoice + " to the list of " + userDTO.getUserName() + "'s roles. Returning to prior menu...");
					break;

				case 2:
					System.out.println("No roles added to " + userDTO.getUserName());
				}
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void removeOneUserRole(UserDTO userDTO) {
		while (true) {
			try {
				System.out.println("Are you sure you want to remove a role from " + userDTO.getUserName() + "? \n"
						+ "Type 1 for Yes. \n" + "Type 2 for No.");

				int userChoice = tui.getInt();

				switch (userChoice) {
				case 1:
					System.out.println("Please specify the role you want removed from " + userDTO.getUserName() + ".");
					String roleChoice = tui.getString();
					userDTO.removeRole(roleChoice);
					System.out.println(
							"You have succesfully removed " + roleChoice + " from " + userDTO.getUserName() + ".");
					break;

				case 2:
					System.out.println("You have chosen not to remove any roles from " + userDTO.getUserName() + ". Returning to prior menu...");
					break;
				}

			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void removeUserRoles(UserDTO userDTO) {

		while (true) {

			System.out.println("Are you sure you want to remove *ALL* roles for " + userDTO.getUserName()
					+ "? This choice is not revertable. \n" + "Type 1 for Yes. \n" + "Type 2 for No.");

			int userChoice = tui.getInt();

			switch (userChoice) {
			case 1:
				userDTO.removeAllRoles();
				System.out.println("All of " + userDTO.getUserName() + "'s roles have been removed.");
				break;

			case 2:
				System.out.println("No roles have been removed from " + userDTO.getUserName() + ". Returning to prior menu...");
				break;
			}

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
		while (true) {
			System.out.println("Please choose an option: \n" + "1: Change the username. \n"
					+ "2: Change the user's initials. \n" + "3: Change the user's password. \n"
					+ "4: Manipulate user roles. \n" + "5: Exit the User Editor.");

			int userChoice = tui.getInt();

			switch (userChoice) {
			case 1:
				changeUserName(userDTO);
				break;

			case 2:
				changeUserIni(userDTO);
				break;

			case 3:
				changeUserPassword(userDTO);
				break;

			case 4:
				manipulateUserRoles(userDTO);
				break;

			// EXIT TO THE MAIN CONTROLLER HERE
			case 5:
				System.out.println("Input recognized, returning to prior menu...");
				return userDTO;
			}
		}
	}
}
