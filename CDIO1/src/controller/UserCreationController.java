package controller;

import boundary.UI;
import boundary.UserCreationBoundary;
import boundary.interfaces.IUserCreationBoundary;
import controller.interfaces.IUserCreationController;
import dal.IUserDAO;
import dal.IUserDAO.DALException;

public class UserCreationController implements IUserCreationController  {

	private IUserCreationBoundary boundary;
	private IUserDAO data;

	public UserCreationController(UI tui, IUserDAO data) {
		boundary=new UserCreationBoundary(tui);
		this.data=data;
	}

	@Override
	public void createUser() {
		try {
			data.createUser(boundary.createNewUser());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}