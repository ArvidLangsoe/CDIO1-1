package controller;

import boundary.interfaces.IUserCreationBoundary;
import boundary.interfaces.IUserDeletetionBoundary;
import boundary.interfaces.IUserEditorBoundary;
import boundary.interfaces.IUserInformationBoundary;
import boundary.interfaces.IUserManagementBoundary;
import controller.interfaces.IUserManagementController;

public class UserManegementController implements IUserManagementController {

	private IUserCreationBoundary create;
	private IUserDeletetionBoundary delete;
	private IUserInformationBoundary information;
	private IUserEditorBoundary edit;
	private IUserManagementBoundary TUIBound;
	public UserManegementController(
			IUserCreationBoundary create,
			IUserDeletetionBoundary delete,
			IUserInformationBoundary information,
			IUserEditorBoundary edit,
			IUserManagementBoundary TUIBound) {
		this.create=create;
		this.delete=delete;
		this.information=information;
		this.edit=edit;
		this.TUIBound =TUIBound;
	}

	@Override
	public void manageUsers() {

	}
	public void choiceHandler(int decision)
	{
		switch(decision)
		{
		case 1: create.createNewUser();
		case 4: delete.getId();
		//case 2: information.showUsers();
		//case 3: edit.editUser();
		case 5: run = false;
		}
	}
	public void start()
	{
		while(run)
		{
			choiceHandler(TUIBound.getUserChoice(options));
			

		}

	}
	String[] options=
		{
				"1:Opret ny bruger",
				"2:List Brugere",
				"3:Ret bruger",
				"4:Slet bruger",
				"5:Afslut program"
		};
	boolean run = true;


}
