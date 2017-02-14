package controller;

import boundary.UserCreationBoundary;
import boundary.interfaces.IUserCreationBoundary;
import controller.interfaces.IUserCreationController;

public class UserCreationController implements IUserCreationController  {

	private IUserCreationBoundary boundary;

	public UserCreationController() {
		boundary=new UserCreationBoundary();
	}

	@Override
	public void createUser() {
		boundary.createNewUser();
		
	}


}
