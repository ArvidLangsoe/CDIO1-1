package controller;

import boundary.UserDeletetionBoundary;
import controller.interfaces.IUserDeletionController;
import dal.IUserDAO;
import dal.IUserDAO.DALException;

public class UserRemoverController implements IUserDeletionController {
	UserDeletetionBoundary boundary;
	IUserDAO data;

	public UserRemoverController(IUserDAO data){
		this.data=data;
	}

	@Override
	public void deleteUser() throws DALException{
		data.deleteUser(boundary.getId());
	}
}
