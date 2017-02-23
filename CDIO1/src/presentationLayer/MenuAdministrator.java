package presentationLayer;

/**
 * The class Menu Administrator extends the class TUI.
 * The responsibility of this class is to carry out the task of calling the correct subMenu, 
 * which controls one of the following operations create user, delete user, edit user and show users.
 * @author Group 22
 *
 */
public class MenuAdministrator extends TUI {

	//Instance variables.
	private UserCreator create;
	private UserRemover delete;
	private UserViewer view;
	private UserEditor edit;
	
	
	/**
	 * Constructor
	 * @param create The UserCreator to use.
	 * @param delete The UserRemover to use.
	 * @param view The UserViewer to use.
	 * @param edit The UserEditor to use.
	 */
	public MenuAdministrator(UserCreator create, UserRemover delete, UserViewer view, UserEditor edit) {
		this.create = create;
		this.delete = delete;
		this.view = view;
		this.edit = edit;
	}
	
	/**
	 * Shows the administrator the menu with the choices he has and prompts him for an integer answer.
	 * @return The integer answer corresponding to the menu he wants access to.
	 */
	public int getUserChoice(){
		show("\n"+"User administration, you have the following options:\n\n"
				+ "1: Create user. \n"
				+ "2: Show users. \n"
				+ "3: Edit users. \n"
				+ "4: Delete user. \n"
				+ "5: Exit program.");
		return getInt();
	}

	
	/**
	 * Calls the boundary corresponding to the administrators decision.
	 * @param decision The decision of the administrator. 
	 * @return True if the administrator enters a decision corresponding to a menu or 
	 * the administrator enters something not valid. False otherwise.
	 */
	public boolean choiceHandler(int decision) {
		switch (decision) {
		case 1:
			create.createNewUser();
			return true;
		case 2:
			view.showUserViewerMenu();
			return true;
		case 3:
			edit.editUserMenu();
			return true;
		case 4:
			delete.deleteUser();
			return true;
		case 5:
			return false;
		default: 
			show("Not a valid options. Please choose an option above.");
			return true;
		}

	}

	/**
	 * Starts and run the user administrator program until exit.
	 */
	public void start() 
	{
		while (choiceHandler(getUserChoice()));
	}

}
