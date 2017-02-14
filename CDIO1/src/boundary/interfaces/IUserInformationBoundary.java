	package boundary.interfaces;
	 
	 import java.util.List;
	 
	 import dto.UserDTO;
	 
	 public interface IUserInformationBoundary {
	 
	 	// Show information to the user. The user should say what he wants to see, and
	 	// the method should show him that.
	 	public void showUsers(List<UserDTO> userArray);
	 }

