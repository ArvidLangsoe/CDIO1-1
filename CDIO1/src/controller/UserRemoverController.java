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
	public void deleteUser()
	{
		try
		{
			data.deleteUser(boundary.getId());
		}
		catch(DALException e)
		{
			boundary.showException(e);
			
			//Try again
			deleteUser();
		}
	}
}
