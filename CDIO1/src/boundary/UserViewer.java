package boundary;

import java.util.List;

import boundary.interfaces.IUserInformationBoundary;
import boundary.interfaces.UI;
import dal.UserDAO;
import dto.UserDTO;

public class UserViewer extends TUI {
	private UserDAO userDAO;
	
	public UserViewer(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	
	public void showUserViewerMenu()
	{
		List<UserDTO> userArray = userDAO.getUserList();
		show("Hvilke brugere vil du se?");
		show("1. Alle brugere.");
		show("2. Brugere med et bestemt id.");
		show("3. Brugere med initialer.");
		show("4. Brugere med et vidst CPR-nummer.");
		show("5. Brugere med en bestemt rolle.");
		
		int decision = getInt();
		
		switch(decision)
		{
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
		default: show("Ikke muligt!");
			break;
		}
		
	}
	
	public void showAllUsers(List<UserDTO> userArray)
	{
		for(UserDTO user : userArray)
		{
			String roles = userRoles(user);
			printUser(user, roles);
		}
	}
	
	public void showUserWithId(int id, List<UserDTO> userArray)
	{
		for(UserDTO user : userArray)
		{
			if(id == user.getUserId())
			{
				String roles = userRoles(user);
				printUser(user, roles);
			}
		}
	}
	
	public void showUserWithIni(String ini, List<UserDTO> userArray)
	{
		for(UserDTO user : userArray)
		{
			if(ini.equals(user.getIni()))
			{
				String roles = userRoles(user);
				printUser(user, roles);
			}
		}
	}
	
	public void showUserWithCpr(String cpr, List<UserDTO> userArray)
	{
		for(UserDTO user : userArray)
		{
			if(cpr.equals(user.getCpr()))
			{
				String roles = userRoles(user);
				printUser(user, roles);
			}
		}
	}
	
	public void showUsersWithRole(String role, List<UserDTO> userArray)
	{
		for(UserDTO user : userArray)
		{
			String roles = userRoles(user);
			roles = "Admin, Pharmacist";
			if(roles.contains(role))
			{
				printUser(user, roles);
			}
		}
	}
	
	private String userRoles(UserDTO user)
	{
		String roles = "";
		for(String s : user.getRoles()) 
		{
			roles += s + ", ";
		}
		return roles;
	}
	
	private void printUser(UserDTO user, String roles)
	{
		show("ID: " + user.getUserId() + 
			" Navn: " + user.getUserName() + 
			" Initialer: " + user.getIni() + 
			" CPR: " + user.getCpr() +
			" Roles: " + roles);
	}
}
