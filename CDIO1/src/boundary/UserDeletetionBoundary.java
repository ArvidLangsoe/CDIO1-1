package boundary;

import boundary.interfaces.IUserDeletetionBoundary;
import boundary.interfaces.UI;

public class UserDeletetionBoundary implements IUserDeletetionBoundary {
	UI tui;
	
	public UserDeletetionBoundary(){
		tui = new TUI();
	}
	
	
	@Override
	public int getId(){
		
		tui.show("Please enter a UserID: ");
		
		int id = tui.getInt();

		while(11 <= id && id <= 99){
			id = tui.getInt();
			
			tui.show("Incorrect ID. Must be between 11 and 99.\n Please enter a UserID");
		}

		return id;
	}
	
	public void showException(Exception e){
		tui.show(e.getMessage());
	}
	
	

}
