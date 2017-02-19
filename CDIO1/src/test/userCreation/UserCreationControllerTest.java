package test.userCreation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.UserCreationController;
import entity.userCreation.IUserCreation;
import presentationClasses.ITUI;

public class UserCreationControllerTest {
	private ITUI tui;
	private IUserCreation userCreator;
	private UserCreationController con;

	@Before
	public void setUp() throws Exception {
		String[] validRoles = new String[] { "Role1", "Role2", "Role3", "Role4" };
		userCreator = new UserCreatorStub(validRoles);
	}

	@After
	public void tearDown() throws Exception {
		tui = null;
		userCreator = null;
		con = null;
	}

	@Test
	public void testStartUserCreationNoExceptions() {
		String[] expectedAnswers = new String[] { "User creation has begun.",
				"Please enter the ID of the new user. It has to be between 11 and 99",
				"Please enter a username. The username has to have a length between 2 and 20.",
				"Please enter initials. The intials have to contain 2-4 letters.", 
				"Please enter the users cpr number.",
				"What roles do you want to assign to the user? Enter a number corresponding to a role:" 
						+ "\n" + 0 + ": " + "Role1" + "." 
						+ "\n" + 1 + ": " + "Role2" + "." 
						+ "\n" + 2 + ": " + "Role3" + "."
						+ "\n" + 3 + ": " + "Role4" + "." 
						+ "\n" + 4 + ": Stop selecting roles.",
				"What roles do you want to assign to the user? Enter a number corresponding to a role:" 
						+ "\n" + 0 + ": " + "Role1" + "." 
						+ "\n" + 2 + ": " + "Role3" + "." 
						+ "\n" + 3 + ": " + "Role4" + "."
						+ "\n" + 4 + ": Stop selecting roles.",
				"That is not a valid choice.",
				"What roles do you want to assign to the user? Enter a number corresponding to a role:" 
						+ "\n" + 0 + ": " + "Role1" + "." 
						+ "\n" + 2 + ": " + "Role3" + "." 
						+ "\n" + 3 + ": " + "Role4" + "."
						+ "\n" + 4 + ": Stop selecting roles.",
				"Succes." };

		String[] userInputs = new String[] { "10", "name", "ini", "cpr", "1", "5", "4" };

		tui = new TUIStub(userInputs);
		TUIStub tuiTest = (TUIStub) tui;
		con = new UserCreationController(userCreator, tui);
		con.startUserCreation();

		System.out.println("testStartUserCreationNoExceptions: Testing " + (expectedAnswers.length - 1)
				+ " answers from the computer.");
		for (int i = 0; i < expectedAnswers.length; i++) {
			System.out.println("test: " + i);
			assertEquals(
					"The expected messages: '" + expectedAnswers[i] + "' and the resulting message was not the same. '"
							+ tuiTest.responses.get(i) + "' were not the same.",
					expectedAnswers[i], tuiTest.responses.get(i));
		}
	}

	@Test
	public void testStartUserCreationWithExceptionsAndWierdInput() {
		String[] expectedAnswers = new String[] { "User creation has begun.",
				"Please enter the ID of the new user. It has to be between 11 and 99", 
				"UserIDTest",
				"Please enter the ID of the new user. It has to be between 11 and 99",
				"That is not a number.","Please enter the ID of the new user. It has to be between 11 and 99",
				"Please enter a username. The username has to have a length between 2 and 20.", 
				"UserNameTest",
				"Please enter a username. The username has to have a length between 2 and 20.",
				"Please enter initials. The intials have to contain 2-4 letters.", 
				"IniTest",
				"Please enter initials. The intials have to contain 2-4 letters.", 
				"Please enter the users cpr number.",
				"CprTest", 
				"Please enter the users cpr number.",
		};

		String[] userInputs = new String[] { "1001","ost", "10", "n", "name", "i", "ini", "c", "cpr", "1", "5", "4" };

		tui = new TUIStub(userInputs);
		TUIStub tuiTest = (TUIStub) tui;
		con = new UserCreationController(userCreator, tui);
		con.startUserCreation();

		System.out.println("testStartUserCreationWithExceptions: Testing " + (expectedAnswers.length - 1)
				+ " answers from the computer.");
		for (int i = 0; i < expectedAnswers.length; i++) {
			System.out.println("test: " + i);
			assertEquals(
					"The expected messages: '" + expectedAnswers[i] + "' and the resulting message was not the same. '"
							+ tuiTest.responses.get(i) + "' were not the same.",
					expectedAnswers[i], tuiTest.responses.get(i));
		}
	}

}
