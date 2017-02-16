package controller;

import boundary.UserCreationBoundary;
import boundary.interfaces.IUserCreationBoundary;
import boundary.interfaces.UI;
import controller.interfaces.IUserCreationController;

public class UserCreationController implements IUserCreationController  {

	private IUserCreationBoundary boundary;

	public UserCreationController(UI tui) {
		boundary=new UserCreationBoundary(tui);
	}

	@Override
	public void createUser() {
		boundary.createNewUser();
		
	}


}
