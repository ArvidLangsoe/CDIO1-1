package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import staticClasses.Validator;
import staticClasses.Validator.InputException;

public class ValidatorTest {

	/**
	 * Test if the validateeuserid is correct.
	 */
	@Test
	public void testValidateUserID() {

		for (int userID = 11; userID <= 99; userID++) {
			try {
				Validator.validateUserID(userID);
			} catch (InputException e) {
				fail("User ID: " + userID + " was not a valid userId and it should be.");
			}
		}

		try {
			Validator.validateUserID(10);
			fail("User ID: " + 10 + " was a valid user id, and it shouldn't be.");
		} catch (InputException e) {

		}

		try {
			Validator.validateUserID(100);
			fail("User ID: " + 100 + " was a valid user id, and it shouldn't be.");
		} catch (InputException e) {

		}

	}

	/**
	 * Test if the validateusername is correct.
	 */
	@Test
	public void testValidateUsername() {
		String userName = "";
		try {
			userName = "This is my username1";
			Validator.validateUsername(userName);
			userName = "Th";
			Validator.validateUsername(userName);
			userName = "username1";
			Validator.validateUsername(userName);

		} catch (InputException e) {
			fail("User name: '" + userName + "' of length:" + userName.length()
					+ " was not a valid username, but it should be.");
		}

		try {
			userName = "This is my username12";
			Validator.validateUsername(userName);
			fail("User name: '" + userName + "' of length:" + userName.length()
					+ " was a valid username, but it should be.");
		} catch (InputException e) {

		}
		try {
			userName = "T";
			Validator.validateUsername(userName);
			fail("User name: '" + userName + "' of length:" + userName.length()
					+ " was a valid username, but it should be.");
		} catch (InputException e) {

		}
		try {
			userName = null;
			Validator.validateUsername(userName);
			fail("User name: '" + userName + "' of length:" + null + " was a valid username, but it should be.");
		} catch (InputException e) {

		}

	}

	/**
	 * Test if validateinitials are correct.
	 */
	@Test
	public void testValidateInitials() {
		String ini = "";
		try {
			ini = "ergr";
			Validator.validateInitials(ini);
			ini = "er";
			Validator.validateInitials(ini);
			ini = "few";
			Validator.validateInitials(ini);

		} catch (InputException e) {
			fail("Initals: '" + ini + "' of length:" + ini.length()
					+ " was not a valid set of initals, but it should be.");
		}

		try {
			ini = "rgwsr";
			Validator.validateInitials(ini);
			fail("Initals: '" + ini + "' of length:" + ini.length()
					+ " was a valid set of initals, but it shouldn't be.");
		} catch (InputException e) {

		}
		try {
			ini = "y";
			Validator.validateInitials(ini);
			fail("Initals: '" + ini + "' of length:" + ini.length()
					+ " was a valid username set of initials, but it shouldn't be.");
		} catch (InputException e) {

		}
		try {
			ini = null;
			Validator.validateInitials(ini);
			fail("Initals: '" + ini + "' of length:" + null
					+ " was a valid username set of initials, but it shouldn't be.");
		} catch (InputException e) {

		}
	}

	/**
	 * Test if validate cpr is correct.
	 */
	@Test
	public void testValidateCPR() {
		String[] workingCPR = new String[] { "1111111118", "2103561178", "0101816222" };
		String[] nonWorkingCPR = new String[] { null, "374583625", "17495302964", "djeufkghei", "4013139915",
				"3713139915", "2013139915", "2020139915", "0101012978" };
		System.out.println("");
		System.out.println("Begin CPRValidation test.");
		System.out.println();
		int index = 0;
		try {
			for (index = 0; index < workingCPR.length; index++) {
				Validator.validateCPR(workingCPR[index]);
			}
		} catch (InputException e) {
			fail("Failure at cpr: " + workingCPR[index] + " Error message: " + e.getMessage());
		}

		for (index = 0; index < nonWorkingCPR.length; index++) {
			try {
				Validator.validateCPR(nonWorkingCPR[index]);
				fail("Failure at cpr: " + nonWorkingCPR[index] + " should be an invalid cpr number.");
			} catch (InputException e) {
				System.out.println("Error message for nonWorkingCPR: '" + nonWorkingCPR[index] + "'\n    message: "
						+ e.getMessage());
				System.out.println();
			}
		}

	}

	/**
	 * Test if validate password is correct.
	 */
	@Test
	public void testValidatePassword() {
		String[] validPasswords = new String[] { "a8E7ge8e", "_?Abu132G" };
		String[] notValidPasswords = new String[] { null, "Th3", "password", "123456789a", "SELECT*FROM",
				"SELECT * FROM" };
		System.out.println("");
		System.out.println("Begin passwordValidation test.");
		System.out.println();
		int index = 0;
		try {
			for (index = 0; index < validPasswords.length; index++) {
				Validator.validatePassword(validPasswords[index]);
			}
		} catch (InputException e) {
			fail("Failure at password: " + validPasswords[index] + " Error message: " + e.getMessage());
		}

		for (index = 0; index < notValidPasswords.length; index++) {
			try {
				Validator.validatePassword(notValidPasswords[index]);
				fail("Failure at password: " + notValidPasswords[index] + " should be an invalid password.");
			} catch (InputException e) {
				System.out.println("Error message for nonValidPassword: '" + notValidPasswords[index]
						+ "'\n    message: " + e.getMessage());
				System.out.println();
			}
		}
	}

	/**
	 * Test if validaterole is correct.
	 */
	@Test
	public void testValidateRole() {
		String[] roles = new String[] { "Admin", "Pharmacist", "Foreman", "Operator" };
		int index = 0;
		try {
			for (index = 0; index < roles.length; index++) {
				Validator.validateRole(roles[index]);
			}

		} catch (InputException e) {
			fail("Role: '" + roles[index] + " was not a valid role, but it should be.");
		}

		try {
			Validator.validateInitials(roles[0] + "s");
			fail("Role: '" + roles[0] + "s" + " was a valid role, but it shouldn't be.");
		} catch (InputException e) {

		}
		try {
			Validator.validateInitials(null);
			fail("Role: '" + roles[0] + "s" + " was a valid role, but it shouldn't be.");
		} catch (InputException e) {

		}

	}

}
