package dataTransferObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dataTransferObjects.Validator.InputException;

/**
 * The objects of the UserDTO is used as data transfer object. The class contains methods to add or 
 * remove information from the data transfer object. In addition the class also contains a few utility methods.
 * @author Group 22
 *
 */
public class UserDTO implements Serializable {
	
	//The serial id making us able to identify the object when saved and loaded.
	private static final long serialVersionUID = 4545864587995944260L;
	
	//Instance variables of the object UserDTO
	private int userId;
	private String userName;
	private String ini;
	private String cpr;
	private String password;
	private List<String> roles;

	/**
	 * Constructor. The constructor only initializes the userDTO roles as an ArrayList.
	 */
	public UserDTO() {
		this.roles = new ArrayList<>();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIni() {
		return ini;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	/**
	 * Adds the given role to the list of roles if it is not already in the list.
	 * @param role The role to be added.
	 */
	public void addRole(String role) {

		if (hasRole(role)) {
			return;
		}

		this.roles.add(role);
		Collections.sort(this.roles);
	}

	/**
	 * Removes the given role from the list if it is in the list. If the role is not in the list the method does nothing.
	 * @param role The role to be removed.
	 */
	public void removeRole(String role) {
		this.roles.remove(role);

	}

	/**
	 * Removes all the roles of the userDTO.
	 */
	public void removeAllRoles() {
		this.roles.clear();
	}

	/**
	 * Checks if the userDTO has the given role.
	 * @param role The role to be checked.
	 * @return True if the userDTO has the role, false otherwise.
	 */
	public boolean hasRole(String role) {
		boolean hasRole = false;
		for (int i = 0; i < roles.size(); i++) {
			if (roles.get(i).equals(role)) {
				hasRole = true;
			}
		}
		return hasRole;
	}

	/**
	 * Creates a string representation of the object without showing the password and CPR of the userDTO.
	 */
	@Override
	public String toString() {
		String newString = "ID: " + userId + ", Username: " + userName + ", Initials: " + ini + ", Roles: ";

		for (String role : roles) {
			newString = newString + role + ", ";
		}
		return newString;
	}

	/**
	 * Checks if the given user is equal to this user.
	 * @param user The user to be checked.
	 * @return True if the given user is equal to this user, false otherwise.
	 */
	public boolean equals(UserDTO user) {

		if (this.getUserId() != user.getUserId()) {
			return false;
		} else if (!this.getUserName().equals(user.getUserName())) {
			return false;
		} else if (!this.getIni().equals(user.getIni())) {
			return false;
		} else if (!this.getCpr().equals(user.getCpr())) {
			return false;
		} else if (!this.getPassword().equals(user.getPassword())) {
			return false;
		} else if (!this.getRoles().equals(this.getRoles())) {
			return false;
		}

		return true;
	}

	/**
	 * Generates initials from a given name.
	 * @param name The name the initials needs to be created from.
	 * @return The generated initials.
	 */
	public String generateInitials(String name) {
		String[] nameParts = name.split(" ");
		String newIni = "";
		if (nameParts.length == 1) {
			newIni = nameParts[0].substring(0, 2);
		} else {
			for (String namePart : nameParts) {
				newIni = newIni + namePart.substring(0, 1);
			}
		}
		return newIni;
	}

	/**
	 * Generates a password for the userDTO accepting the rules of DTU passwords.
	 * @return The generated password.
	 */
	public String generatePassword() {
		String password = "";
		int passLength = 8;
		boolean passwordValid = false;
		while (!passwordValid) {
			password = "";
			for (int i = 0; i < passLength; i++) {
				char newCharacther;
				int randGroup = (int) (Math.random() * 100);

				// Add a special characther
				if (randGroup < 5) {
					String specialCharacthers = ".-_+!?=";
					int rand = (int) (Math.random() * specialCharacthers.length());
					newCharacther = specialCharacthers.charAt(rand);
				}
				// Add a small letter.
				else if (randGroup < 30) {
					int rand = (int) (Math.random() * (122 - 97 + 1) + 97);
					newCharacther = (char) rand;
				}
				// Add a large letter.
				else if (randGroup < 55) {
					int rand = (int) (Math.random() * (90 - 65 + 1) + 65);
					newCharacther = (char) rand;
				}
				// Add a number.
				else {
					int rand = (int) (Math.random() * (57 - 48 + 1) + 48);
					newCharacther = (char) rand;
				}

				password += newCharacther + "";
			}
			try {
				Validator.validatePassword(password);
				passwordValid = true;
			} catch (InputException e) {

			}
		}
		return password;
	}

}
