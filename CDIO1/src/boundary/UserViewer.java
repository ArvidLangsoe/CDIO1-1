package boundary;

import java.util.List;

import dal.IUserDAO.DALException;
import dal.IUserDAO;
import dto.UserDTO;

public class UserViewer extends TUI {
	private IUserDAO userDAO;

	public UserViewer(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

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
					+ "5. Users with a specific role..\n"+"6. Leave menu.");

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

	public void showAllUsers(List<UserDTO> userArray) {
		if(userArray.isEmpty()){
			show("No users exist.");
			return;
		}
		for (UserDTO user : userArray) {
			String roles = userRoles(user);
			printUser(user, roles);
		}
	}

	public void showUserWithId(int id, List<UserDTO> userArray) {
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

	public void showUserWithIni(String ini, List<UserDTO> userArray) {
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

	public void showUserWithCpr(String cpr, List<UserDTO> userArray) {
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

	public void showUsersWithRole(String role, List<UserDTO> userArray) {
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

	private String userRoles(UserDTO user) {
		String roles = "";
		for (String s : user.getRoles()) {
			roles += s + ", ";
		}
		return roles;
	}

	private void printUser(UserDTO user, String roles) {
		show(user.toString());
	}
}
