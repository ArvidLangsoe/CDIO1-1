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

			show("Hvilke brugere vil du se?\n" + "1. Alle brugere.\n" + "2. Brugere med et bestemt id.\n"
					+ "3. Brugere med initialer.\n" + "4. Brugere med et vidst CPR-nummer.\n"
					+ "5. Brugere med en bestemt rolle."+"6. Forlad menu.");

			int decision = getInt();

			switch (decision) {
			case 1:
				show("Alle brugere vises:");
				showAllUsers(userArray);
				break;
			case 2:
				show("Hvilket id?");
				int id = getInt();
				showUserWithId(id, userArray);
				break;
			case 3:
				show("Hvilke initialer?");
				String ini = getString();
				showUserWithIni(ini, userArray);
				break;
			case 4:
				show("Hvilket CPR-nummer?");
				String cpr = getString();
				showUserWithCpr(cpr, userArray);
				break;
			case 5:
				show("Hvilken rolle?");
				String role = getString();
				showUsersWithRole(role, userArray);
				break;
			case 6: 
				return;
			default:
				show("Ikke muligt!");
				break;
			}
		}

	}

	public void showAllUsers(List<UserDTO> userArray) {
		for (UserDTO user : userArray) {
			String roles = userRoles(user);
			printUser(user, roles);
		}
	}

	public void showUserWithId(int id, List<UserDTO> userArray) {
		for (UserDTO user : userArray) {
			if (id == user.getUserId()) {
				String roles = userRoles(user);
				printUser(user, roles);
			}
		}
	}

	public void showUserWithIni(String ini, List<UserDTO> userArray) {
		for (UserDTO user : userArray) {
			if (ini.equals(user.getIni())) {
				String roles = userRoles(user);
				printUser(user, roles);
			}
		}
	}

	public void showUserWithCpr(String cpr, List<UserDTO> userArray) {
		for (UserDTO user : userArray) {
			if (cpr.equals(user.getCpr())) {
				String roles = userRoles(user);
				printUser(user, roles);
			}
		}
	}

	public void showUsersWithRole(String role, List<UserDTO> userArray) {
		for (UserDTO user : userArray) {
			String roles = userRoles(user);
			roles = "Admin, Pharmacist";
			if (roles.contains(role)) {
				printUser(user, roles);
			}
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
		show("ID: " + user.getUserId() + " Navn: " + user.getUserName() + " Initialer: " + user.getIni() + " CPR: "
				+ user.getCpr() + " Roles: " + roles);
	}
}
