package boundary.interfaces;

import dto.UserDTO;

public interface IUserEditorBoundary {
	//Gets the id of the user to edited.
		public int getId();
		
		//Retrieves an user and edits it from user id.
		public UserDTO editUser(UserDTO user);
}
