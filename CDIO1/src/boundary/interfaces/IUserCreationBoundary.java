package boundary.interfaces;

import dto.UserDTO;

public interface IUserCreationBoundary {

	//Creates a new UserDTO and fills it with information.
	public UserDTO getNewUser();
	
	//Edits the userId of an existing user.
	public UserDTO editUserId();
	
}
