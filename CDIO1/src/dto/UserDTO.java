package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import boundary.TUI;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 4545864587995944260L;
	
	private int userId; 
	private String userName;
	private String ini;
	private String cpr;
	private String password;
	private List<String> roles;
	
	public UserDTO() {
		this.roles = new ArrayList<>();
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) throws InputException {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) throws InputException {
		this.userName = userName;
	}
	
	public String getIni() {
		return ini;
	}
	
	public void setIni(String ini) throws InputException {
		this.ini = ini;
	}
	
	public String getCpr() {
		return cpr;
	}
	
	public void setCpr(String cpr) throws InputException{
		this.cpr = cpr;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) throws InputException {
		this.password = password;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void addRole(String role) throws InputException {
		for (int i = 0; i < roles.size(); i++) {
			if (roles.get(i).equals(role)) {
				return;
			}
		}
		this.roles.add(role);
	}
	
	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public void removeRole(String role) throws InputException {		
		if(!this.roles.remove(role))
			throw new InputException("This user doesn't have role: "+role);
	}
	
	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public void removeAllRoles() {
		this.roles.clear();
	}
	
	public boolean hasRole(String role)
	{
		boolean hasRole = false;
		for(int i = 0; i < roles.size(); i++)
		{
			if (roles.get(i).equals(role))
			{
				hasRole = true;
			}
		}
		return hasRole;
	}
	
	@Override
	public String toString() 
	{
		String newString = "UserDTO [userId =" + userId + ", userName =" + userName + ", ini =" + ini + ", roles = ";
		
		for(String role : roles)
		{
			newString = newString + role;
		}
		return newString + "]";
	}
	
	public String generateInitials (String name)
	{
		String[] nameParts = name.split(" ");
		String newIni = "";
		if (nameParts.length == 1)
		{
			newIni = nameParts[0].substring(0, 2);
		}
		else
		{
			for(String namePart : nameParts)
			{
				newIni = newIni + namePart.substring(0, 1);
			}
		}
		return newIni;
	}
	
	public String generatePassword() {
		String password = "";
		int passLength = 8;
		boolean passwordValid = false;
		while (!passwordValid) {
			password = "";
			for (int i = 0; i < passLength; i++) 
			{
				char newCharacther;
				int randGroup = (int) (Math.random() * 100);
				
				// Add a special characther
				if (randGroup < 5) 
				{
					String specialCharacthers = ".-_+!?=";
					int rand = (int) (Math.random() * specialCharacthers.length());
					newCharacther = specialCharacthers.charAt(rand);
				}
				// Add a small letter.
				else if (randGroup < 30) 
				{
					int rand = (int) (Math.random() * (122 - 97 + 1) + 97);
					newCharacther = (char) rand;
				}
				// Add a large letter.
				else if (randGroup < 55) 
				{
					int rand = (int) (Math.random() * (90 - 65 + 1) + 65);
					newCharacther = (char) rand;
				}
				// Add a number.
				else 
				{
					int rand = (int) (Math.random() * (57 - 48 + 1) + 48);
					newCharacther = (char) rand;
				}
				
				password += newCharacther + "";
			}
			try 
			{
				Validator.validatePassword(password);
				passwordValid = true;
			} 
			catch (InputException e) 
			{
				
			}
		}
		return password;
	}
	
	
}
