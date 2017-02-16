package boundary;

import boundary.interfaces.*;

public class UserManagementBoundary extends TUI implements IUserManagementBoundary {
	
	private IUserCreationBoundary create;
	private IUserDeletetionBoundary delete;
	private IUserInformationBoundary information;
	private IUserEditorBoundary edit;
	
	String[] options = { "1:Opret ny bruger", "2:List Brugere", "3:Ret bruger", "4:Slet bruger", "5:Afslut program" };
	boolean run = true;
	
	public UserManagementBoundary(IUserCreationBoundary create, IUserDeletetionBoundary delete, IUserInformationBoundary information,IUserEditorBoundary edit) {
		this.create = create;
		this.delete = delete;
		this.information = information;
		this.edit = edit;
	}

	public int getUserChoice(){
		for(int i=0 ; i < options.length ; i++)
		{
			show(options[i]);
		}

		return getInt();
	}


	public void manageUsers() {
		
	}

	public boolean choiceHandler(int decision) {
		switch (decision) {
		case 1:
			create.createNewUser();
			return true;
			break;
		case 4:
			delete.deleteUser();
			return true;
			break;
		case 2:
			information.showUsers();
			return true;
			break;
		case 3:
			edit.editUser();
			return true;
			break;
		case 5:
			return false;
			break;
		}

	}

	public void start() {
		while (!choiceHandler(getUserChoice()))
			show("Bad command");
	}
	
}
