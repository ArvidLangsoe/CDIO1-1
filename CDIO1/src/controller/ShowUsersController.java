package controller;

import dal.UserDAO;
import dto.UserDTO;

public class ShowUsersController {
	private UserDAO userDAO;
	
	public ShowUsersController(UserDAO userDAO) 
	{
		this.userDAO = userDAO;
	}
	
	public void showUserList()
	{
		for(UserDTO userDTO : userDAO.getUserList())
			System.out.println(userDTO.toString());
	}
	

}
