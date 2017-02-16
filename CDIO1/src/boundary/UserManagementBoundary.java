package boundary;

public class UserManagementBoundary extends TUI {

	private UserCreationBoundary create;
	private UserDeletetionBoundary delete;
	private UserViewer view;
	private UserEditor edit;

	String options = "1:Opret ny bruger \n"
			+ "2:List Brugere \n"
			+ "3:Ret bruger \n"
			+ "4:Slet bruger \n"
			+ "5:Afslut program \n";

	public UserManagementBoundary(UserCreationBoundary create, UserDeletetionBoundary delete, UserViewer view, UserEditor edit) {
		this.create = create;
		this.delete = delete;
		this.view = view;
		this.edit = edit;
	}

	public int getUserChoice(){
		show(options);
		return getInt();
	}


	public void manageUsers() {

	}

	public boolean choiceHandler(int decision) {
		switch (decision) {
		case 1:
			create.createNewUser();
			return true;
		case 4:
			delete.deleteUser();
			return true;
		case 2:
			view.showUserViewerMenu();
			return true;
		case 3:
			edit.editUser();
			return true;
		case 5:
			return false;
		default: 
			show("Bad input");
			return true;
		}

	}

	public void start() 
	{
		while (!choiceHandler(getUserChoice()));
	}

}
