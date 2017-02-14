package controller;

import boundary.interfaces.IUserInformationBoundary;
import controller.interfaces.IUserInformationController;
import dal.UserDAO;

public class UserInformationController implements IUserInformationController {
	private UserDAO userDAO;
	private IUserInformationBoundary userInfo;
	
	public UserInformationController(UserDAO userDAO, IUserInformationBoundary userInfo) 
	{
		this.userDAO = userDAO;
		this.userInfo = userInfo;
	}

	@Override
	public void getUserInformation() {
		userInfo.showUsers(userDAO.getUserList());
	}
	

}
