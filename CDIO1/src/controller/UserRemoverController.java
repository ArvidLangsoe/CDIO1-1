package controller;

import boundary.ITUI;
import dal.IUserDAO;
import entity.UserRemover;

public class UserRemoverController {
	UserRemover func;
	ITUI tui;
	IUserDAO data;

	public UserRemoverController(IUserDAO data){
		this.data=data;
		func = new UserRemover(data);
	}

	public void start(){
		while(!deleteUser(tui.getUserInput())){
			tui.showResponse("Enter userID: ");
		}
	}

	public boolean deleteUser(String input){
		try{
			int userID = Integer.parseInt(input);
			func.deleteUser(userID);
			return true;

		}catch (Exception e){
			tui.showResponse("ERROR: "+e);
		}

		return false;
	}
}
