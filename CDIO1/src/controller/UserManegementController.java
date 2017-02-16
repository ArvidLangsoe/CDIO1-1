package controller;

import controller.interfaces.IUserCreationController;
import controller.interfaces.IUserDeletionController;
import controller.interfaces.IUserEditorController;
import controller.interfaces.IUserInformationController;
import controller.interfaces.IUserManagementController;
import boundary.interfaces.IUserManagementBoundary;

public class UserManegementController implements IUserManagementController {

	private IUserCreationController create;
	private IUserDeletionController delete;
	private IUserInformationController information;
	private IUserEditorController edit;
	private IUserManagementBoundary TUIBound;

	public UserManegementController(IUserCreationController create, IUserDeletionController delete,
			IUserInformationController information, IUserEditorController edit, IUserManagementBoundary TUIBound) 
	{
		this.create = create;
		this.delete = delete;
		this.information = information;
		this.edit = edit;
		this.TUIBound = TUIBound;
	}

	@Override
	public void manageUsers() {

	}

	public void choiceHandler(int decision) {
		switch (decision) {
		case 1:
			create.createUser();
			break;
		case 4:
			delete.deleteUser();
			break;
		case 2:
			information.getUserInformation();
			break;
		case 3:
			edit.editUser();
			break;
		case 5:
			run = false;
			break;
		}

	}

	public void start() {
		while (run) {
			choiceHandler(TUIBound.getUserChoice(options));
		}
	}

	String[] options = { "1:Opret ny bruger", "2:List Brugere", "3:Ret bruger", "4:Slet bruger", "5:Afslut program" };
	boolean run = true;

}
