package serverlogin;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Clem Beauchamp, Rob Minford
 * Server uses this class to check a text file for the login
 * Username and password combinations are stored
 */
public class ServerLogin {

	// fields for filename and paths.

	final static File file = new File(
			"input.txt");

	final static String fileName = "input.txt";

	final static Charset ENCODING = StandardCharsets.UTF_8; // need for reading
															// lines

	static Path path = Paths
			.get("input.txt");

	/**
	 * finds the username in input.txt.
	 * 
	 * @param username
	 * @return boolean if username is in input.txt.
	 * @throws IOException
	 */
	public static boolean searchUsername(String username) throws IOException {

		List<String> allLines = Files.readAllLines(path, ENCODING);

		for (int i = 0; i < allLines.size(); i++) {
			int usernameIndexEnd = allLines.get(i).indexOf(",");
			if (usernameIndexEnd > 0) {
				if (allLines.get(i).substring(0, usernameIndexEnd)
						.contentEquals(username)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * returns all details about the user for the inputed username
	 * 
	 * @param username
	 * @return String username and password.
	 * @throws IOException
	 */
	public static String getUserDetails(String username) throws IOException {
		List<String> allLines = Files.readAllLines(path, ENCODING);

		for (int i = 0; i < allLines.size(); i++) {
			int usernameIndexEnd = allLines.get(i).indexOf(",");
			if (usernameIndexEnd > 0) {
				if (allLines.get(i).substring(0, usernameIndexEnd)
						.contentEquals(username)) {
					return allLines.get(i);
				}
			}
		}
		return "";
	}

	/**
	 * returns true if the inputed username and password match the data from the
	 * text file.
	 * 
	 * @param username
	 * @param password
	 * @return boolean if password is correct.
	 * @throws IOException
	 */
	public static boolean checkPassword(String username, String password)
			throws IOException {

		if (searchUsername(username)) { // checks that username exists
			// int endIndex = getUserDetails(username).indexOf("~"); // gets the
			// index of the end of the required data
			System.out.println(getUserDetails(username).length());
			return (getUserDetails(username).substring(0,
					(username + "," + password).length()) // gets the required
															// username and
															// password and
					.equals(username + "," + password)); // checks if it equals
															// the input

		} else {
			System.out.println("username doesn't exist");
			return false;
		}

	}

	/**
	 * writes a new line in the text file with the inputed username and
	 * password.
	 * 
	 * @param username
	 * @param password
	 * @throws IOException
	 */
	public static void createNewUser(String username, String password)
			throws IOException {

		if (!searchUsername(username)) { // checks that the username is unique
			String userData = (username + "," + password); // string to be added
			System.out.println(userData);

			List<String> userDataList = new ArrayList<String>(
					Arrays.asList(userData)); // for the write method

			Files.write(path, userDataList, ENCODING, StandardOpenOption.APPEND); // writes
																					// the
																					// data
																					// to
																					// the
																					// file
																					// at
																					// the
																					// end
		} else {
			System.out.println("username already exists");
		}
	}

}