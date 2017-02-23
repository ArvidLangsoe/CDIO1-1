package presentationLayer;

import java.util.List;

import dataAccessObjects.IUserDAO;
import dataAccessObjects.IUserDAO.DALException;
import dataTransferObjects.UserDTO;

/**
 * The class UserViewer extends TUI.
 * The responsibility of this class is to carry out the tasks of the showing the user administrator the wanted user/users from the data 
 * @author Group 22
 *
 */
public class UserViewer extends TUI {
	
	//Instance variables.
	private IUserDAO userDAO;

	/**
	 * Constructor
	 * @param userDAO The data access object to user.
	 */
	public UserViewer(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * Shows the information stated by the user administrator.
	 */
	public void showUserViewerMenu() {
		while (true) {
			List<UserDTO> userArray = null;
			try {
				userArray = userDAO.getUserList();
			} catch (DALException e) {
				show("ERROR: Could not get the user list!");
			}

			show("\n What users do you want to see?\n" + "1. All users.\n" + "2. Users with a specific id.\n"
					+ "3. Users with specific initials. \n" + "4. Users with a specific CPR-number.\n"
					+ "5. Users with a specific role.\n"+"6. Leave menu.");

			int decision = getInt();

			switch (decision) {
			case 1:
				show("All users are shown:");
				showAllUsers(userArray);
				break;
			case 2:
				show("What id do you want to see?");
				int id = getInt();
				showUserWithId(id, userArray);
				break;
			case 3:
				show("What initials do you want to see?");
				String ini = getString();
				showUserWithIni(ini, userArray);
				break;
			case 4:
				show("What CPR-number do you want to see?");
				String cpr = getString();
				showUserWithCpr(cpr, userArray);
				break;
			case 5:
				show("What role do you want to see?");
				String role = getString();
				showUsersWithRole(role, userArray);
				break;
			case 6: 
				return;
			default:
				show("That is not an option. Please choose one of the above.");
				break;
			}
		}
	}

	/**
	 * Shows all users in the data.
	 * @param userArray The list containing all the users in the data.
	 */
	private void showAllUsers(List<UserDTO> userArray) {
		if(userArray.isEmpty()){
			show("No users exist.");
			return;
		}
		for (UserDTO user : userArray) {
			String roles = userRoles(user);
			printUser(user, roles);
		}
	}

	/**
	 * Shows a specific user with the given id.
	 * @param id The id of the user you want to see.
	 * @param userArray The list containing all users in the data.
	 */
	private void showUserWithId(int id, List<UserDTO> userArray) {
		int count=0;
		for (UserDTO user : userArray) {
			if (id == user.getUserId()) {
				count++;
				String roles = userRoles(user);
				printUser(user, roles);
			}
		}
		
		if(count==0){
			show("No user exist with that id.");
		}
	}

	/**
	 * Shows a specific user with the given initials
	 * @param ini The initials of the user you want to see.
	 * @param userArray The list of all the users in the data.
	 */
	private void showUserWithIni(String ini, List<UserDTO> userArray) {
		int count=0;
		for (UserDTO user : userArray) {
			if (ini.equals(user.getIni())) {
				count++;
				String roles = userRoles(user);
				printUser(user, roles);
			}
		}
		if(count==0){
			show("No user exist with those initials.");
		}
	}

	/**
	 * Shows a specific user with the given CPR.
	 * @param cpr The CPR of the user you want to see.
	 * @param userArray The list of all the users in the data.
	 */
	private void showUserWithCpr(String cpr, List<UserDTO> userArray) {
		int count=0;
		for (UserDTO user : userArray) {
			if (cpr.equals(user.getCpr())) {
				count++;
				String roles = userRoles(user);
				printUser(user, roles);
			}
		}
		if(count==0){
			show("No user exist with that CPR.");
		}
	}

	/**
	 * Shows all the users with a given role.
	 * @param role The role that you want to see the users with.
	 * @param userArray The list of all the users in the data.
	 */
	private void showUsersWithRole(String role, List<UserDTO> userArray) {
		int count=0;
		for (UserDTO user : userArray) {
			String roles = userRoles(user);
			if (roles.contains(role)) {
				count++;
				printUser(user, roles);
			}
		}
		if(count==0){
			show("No user exist with that role.");
		}
	}

	/**
	 * Returns the roles of a user as a string.
	 * @param user The user to find the roles of.
	 * @return The roles as a string.
	 */
	private String userRoles(UserDTO user) {
		String roles = "";
		for (String s : user.getRoles()) {
			roles += s + ", ";
		}
		return roles;
	}

	/**
	 * Shows the user.
	 * @param user The user to be shown.
	 * @param roles
	 */
	private void printUser(UserDTO user, String roles) {
		show(user.toString());
		//TODO the variables roles not needed?
	}
}
