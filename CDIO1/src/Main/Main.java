package Main;

import boundary.*;
import dal.*;

public class Main {

	public static void main(String[] args) {
			IUserDAO data = new UserDAO(null);
			IDataVerifier func= new DataVerifier(data);
			UserViewer view = new UserViewer(data);
			UserRemover delete = new UserRemover(data);
			UserCreator create = new UserCreator(func);
			UserEditor edit = new UserEditor(func);
			MenuAdministrator menu = new MenuAdministrator(create, delete, view, edit);
			menu.start();
	}

}
