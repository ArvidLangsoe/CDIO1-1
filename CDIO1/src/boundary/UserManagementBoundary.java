package boundary;

import boundary.interfaces.IUserManagementBoundary;
import boundary.interfaces.UI;

public class UserManagementBoundary implements IUserManagementBoundary {

	int choice;
	private UI TUI;
	public UserManagementBoundary(UI TUI) {
		this.TUI = TUI;
	}

	public int getUserChoice(String[] options){
		for(int i=0;i<options.length;i++)
		{
			System.out.println(options[i]);
		}
		choice = TUI.getInt();
		return choice;		
	}

}
