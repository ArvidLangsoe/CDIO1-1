package Main;

import boundary.*;
import dal.*;

public class Main {

	public static void main(String[] args) {
			IUserDAO data = new UserDAO(null);
			UserViewer view = new UserViewer(data);
			UserRemover delete = new UserRemover(data);
			UserCreator create = new UserCreator(data);
			UserEditor edit = new UserEditor(data);
			MenuAdministrator menu = new MenuAdministrator(create, delete, view, edit);
			menu.start();
	}

}
