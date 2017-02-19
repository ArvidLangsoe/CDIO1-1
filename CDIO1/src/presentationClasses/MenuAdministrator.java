package presentationClasses;

public class MenuAdministrator extends TUI {

	private UserCreator create;
	private UserRemover delete;
	private UserViewer view;
	private UserEditor edit;

	private String options = "\n"+"User administration, you have the following options:\n\n"
			+ "1: Create user. \n"
			+ "2: Show users. \n"
			+ "3: Edit users. \n"
			+ "4: Delete user. \n"
			+ "5: Exit program.";

	public MenuAdministrator(UserCreator create, UserRemover delete, UserViewer view, UserEditor edit) {
		this.create = create;
		this.delete = delete;
		this.view = view;
		this.edit = edit;
	}

	public int getUserChoice(){
		show(options);
		return getInt();
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
			edit.editUserMenu();
			return true;
		case 5:
			return false;
		default: 
			show("Not a valid options. Please choose an option above.");
			return true;
		}

	}

	public void start() 
	{
		while (choiceHandler(getUserChoice()));
	}

}
