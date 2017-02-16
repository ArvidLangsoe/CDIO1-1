package Main;

import boundary.*;
import dal.*;

public class Main {

	public static void main(String[] args) {
			IUserDAO data = new UserDAO(null);
			UserViewer view = new UserViewer(data);
			UserDeletetionBoundary delete = new UserDeletetionBoundary(data);
			UserCreationBoundary create = new UserCreationBoundary(data);
			UserEditor edit = new UserEditor(data);
			UserManagementBoundary menu = new UserManagementBoundary(create, delete, view, edit);
			menu.start();
	}

}
