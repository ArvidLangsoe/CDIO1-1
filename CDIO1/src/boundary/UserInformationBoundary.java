package boundary;

import java.util.List;

import boundary.interfaces.IUserInformationBoundary;
import boundary.interfaces.UI;
import dto.UserDTO;

public class UserInformationBoundary implements IUserInformationBoundary {
	private UI tui;
	
	public UserInformationBoundary(UI tui)
	{
		this.tui = tui;
	}
	
	@Override
	public void showUsers(List<UserDTO> userArray)
	{
		tui.show("Hvilke brugere vil du se?");
		tui.show("1. Alle brugere.");
		tui.show("2. Brugere med et bestemt id.");
		tui.show("3. Brugere med initialer.");
		tui.show("4. Brugere med et vidst CPR-nummer.");
		tui.show("5. Brugere med en bestemt rolle.");
		
		int decision = tui.getInt();
		
		switch(decision)
		{
		case 1: 
			tui.show("Alle brugere vises:");
			showAllUsers(userArray);
			break;
		case 2:
			tui.show("Hvilket id?");
			int id = tui.getInt();
			showUserWithId(id, userArray);
			break;
		case 3:
			tui.show("Hvilke initialer?");
			String ini = tui.getString();
			showUserWithIni(ini, userArray);
			break;
		case 4:
			tui.show("Hvilket CPR-nummer?");
			String cpr = tui.getString();
			showUserWithCpr(cpr, userArray);
			break;
		case 5:
			tui.show("Hvilken rolle?");
			String role = tui.getString();
			showUsersWithRole(role, userArray);
			break;
		default: tui.show("Ikke muligt!");
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
		tui.show("ID: " + user.getUserId() + 
				" Navn: " + user.getUserName() + 
				" Initialer: " + user.getIni() + 
				" CPR: " + user.getCpr() +
				" Roles: " + roles);
	}
}
