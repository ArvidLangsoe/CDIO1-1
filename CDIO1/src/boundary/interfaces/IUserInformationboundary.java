package boundary.interfaces;

import java.util.ArrayList;

import dto.UserDTO;

public interface IUserInformationboundary {

	// Show information to the user. The user should say what he wants to see, and
	// the method should show him that.
	public void showUsers(ArrayList<UserDTO> userArray);
}
