package login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
 * 
 * @author Clem
 *
 */
public class SinglePlayerReadWriteTextFile {

	final static File file = new File("singlePlayerInput.txt");
	final static File tempFile = new File("tempSinglePlayerInput.txt");	//temporary file for replacing details
	
	final static String fileName = "singlePlayerInput.txt";
	
	final static Charset ENCODING = StandardCharsets.UTF_8;	// need for reading lines

	static Path path = Paths.get("singlePlayerInput.txt");

	/**
	 * Searches all usernames for inputed username and returns true if found
	 * @param username - username to be searched for
	 * @return boolean of if username is found in text file
	 * @throws IOException
	 */
	public static boolean searchUsername(String username) throws IOException {

		List<String> allLines = Files.readAllLines(path, ENCODING);

		for (int i = 0; i < allLines.size(); i++) {
			int usernameIndexEnd = allLines.get(i).indexOf(",");
			if(usernameIndexEnd > 0){
				if (allLines.get(i).substring(0, usernameIndexEnd).contentEquals(username)) {
					return true;
				}	
			}
		}
		return false;
	}

	/**
	 * Returns all details about the user for the inputed username
	 * @param username - username for the desired details
	 * @return String of the user details
	 * @throws IOException
	 */
	public static String getUserDetails(String username) throws IOException {
		List<String> allLines = Files.readAllLines(path, ENCODING);

		for (int i = 0; i < allLines.size(); i++) {
			int usernameIndexEnd = allLines.get(i).indexOf(",");
			if(usernameIndexEnd > 0){
				if (allLines.get(i).substring(0, usernameIndexEnd).contentEquals(username)) {
					return allLines.get(i);
				}
			}
		}
		return "";
	}

	/**
	 * Gets the username and password combination for the inputed username
	 * @param username - username for the desired username/password combination
	 * @return String of username and password
	 * @throws IOException
	 */
	public static String getUsernamePassword(String username) throws IOException {
		List<String> allLines = Files.readAllLines(path, ENCODING);

		for (int i = 0; i < allLines.size(); i++) {
			int usernameIndexEnd = allLines.get(i).indexOf(",");
			int passwordIndexEnd = allLines.get(i).indexOf("~");
			if (allLines.get(i).substring(0, usernameIndexEnd)
					.contentEquals(username)) {
				return allLines.get(i).substring(0, passwordIndexEnd);
			}
		}
		return "";
	}

	/**
	 * Returns the armour for the inputed user
	 * @param username - username for the desired armour
	 * @return String of armour
	 * @throws IOException
	 */
	public static String getArmour(String username) throws IOException {
		List<String> allLines = Files.readAllLines(path, ENCODING);

		for (int i = 0; i < allLines.size(); i++) {
			int usernameIndexEnd = allLines.get(i).indexOf(",");
			int swordIndexEnd = allLines.get(i).indexOf("#");
			int armourIndexEnd = allLines.get(i).indexOf("!");
			if (allLines.get(i).substring(0, usernameIndexEnd)
					.contentEquals(username)) {
				return allLines.get(i).substring(swordIndexEnd, armourIndexEnd);
			}
		}
		return "";
	}

	/**
	 * Returns the sword for the inputed user
	 * @param username - username for the desired sword
	 * @return String of sword
	 * @throws IOException
	 */
	public static String getSword(String username) throws IOException {
		List<String> allLines = Files.readAllLines(path, ENCODING);

		for (int i = 0; i < allLines.size(); i++) {
			int usernameIndexEnd = allLines.get(i).indexOf(",");
			int passwordIndexEnd = allLines.get(i).indexOf("~");
			int swordIndexEnd = allLines.get(i).indexOf("#");
			if (allLines.get(i).substring(0, usernameIndexEnd)
					.contentEquals(username)) {
				return allLines.get(i).substring(passwordIndexEnd, swordIndexEnd);
			}
		}
		return "";
	}
	
	// sets the sword for the inputed user. sword input should be in the form of sword1
	public static void setSword(String username, String sword) throws IOException {
		if (searchUsername(username)) {
			String replacement = (getUsernamePassword(username) + "~" + sword + getArmour(username) + "!" + getCoins(username) + "'");

			replaceUserDetails(username, replacement);
			

		} else {
			System.out.println("no such username");
		}
	}
	
	// sets the armour for the inputed user. sword input should be in the form of armour1
	public static void setArmour(String username, String armour) throws IOException {
		if (searchUsername(username)) {
			String replacement = (getUsernamePassword(username) + getSword(username) + "#" + armour + "!" + getCoins(username) + "'");

			replaceUserDetails(username, replacement);
			

		} else {
			System.out.println("no such username");
		}
	}

	//replaces all of the user's details with the replacement. Both must be in the form of username,password~sword#armour!
	public static void replaceUserDetails(String username, String replacementDetails) throws IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		
		int numberOfUsers = getNumberOfUsers();
		
		for(int i = 0; i < numberOfUsers ; i++){
			
			String currentLine = reader.readLine();
			
			if(currentLine != null){
				if(currentLine.contentEquals(getUserDetails(username))){ 
					writer.append(replacementDetails);	// if username details match the current line then write the 
					writer.newLine();					// replacement instead in new file
				} else {
					writer.append(currentLine); // otherwise add the current line to the new file
					writer.newLine();
				}
			} else {
				break;
			}
		}

		reader.close();
		writer.close();
		
		if(file.delete()){
			tempFile.renameTo(file);	// replaces old file with new one 
		}
		
		return;
		
	}
	
	// returns the number of users in the input file as an int
	public static int getNumberOfUsers() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file)); 
		int numberOfUsers = 0;
		
		try {
			while(reader.readLine() != null){
				numberOfUsers++;	// increments the int every time the current line is not null
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		reader.close();
		return numberOfUsers;
	}
	
//	// returns true if the inputed username and password match the data from the text file
//	public static boolean checkPassword(String username, String password)
//			throws IOException {
//
//		if (searchUsername(username)) { // checks that username exists
//			int endIndex = getUserDetails(username).indexOf("~"); // gets the index of the end of the required data
//
//			return (getUserDetails(username).substring(0, endIndex) // gets the required username and password and
//					.equals(username + "," + password));  			// checks if it equals the input
//
//		} else {
//			System.out.println("username doesn't exist");
//			return false;
//		}
//
//	}

	// writes a new line in the text file with the inputed username and password with default sword and armour. 
	// In the form username,0~sword#armour!100'
	public static void createNewUser(String username) throws IOException {
		
		if(!searchUsername(username)){ // checks that the username is unique
			String userData = (username + ",0~sword1#armour1!0'"); // string to be added

			userData.toCharArray(); 														// the string must be converted
			List<String> userDataList = new ArrayList<String>(Arrays.asList(userData)); 	  //  for the write method

			Files.write(path, userDataList, ENCODING, StandardOpenOption.APPEND); // writes the data to the file at the end
		} else {
			System.out.println("username already exists");
		}
	} 
	
	public static String getCoins(String username) throws IOException{
		List<String> allLines = Files.readAllLines(path, ENCODING);

		for (int i = 0; i < allLines.size(); i++) {
			int usernameIndexEnd = allLines.get(i).indexOf(",");
			int armourIndexEnd = allLines.get(i).indexOf("!");
			int coinsIndexEnd = allLines.get(i).indexOf("'");
			if (allLines.get(i).substring(0, usernameIndexEnd)
					.contentEquals(username)) {
				return allLines.get(i).substring(armourIndexEnd + 1, coinsIndexEnd);
			}
		}
		return "";
	}
	
	// In the form username,0~sword#armour!100'
	public static String getSanguine(String username) throws IOException{
		List<String> allLines = Files.readAllLines(path, ENCODING);

		for (int i = 0; i < allLines.size(); i++) {
			int usernameIndexEnd = allLines.get(i).indexOf(",");
			int sanguineIndexEnd = allLines.get(i).indexOf("~");
			if (allLines.get(i).substring(0, usernameIndexEnd)
					.contentEquals(username)) {
				return allLines.get(i).substring(usernameIndexEnd + 1, sanguineIndexEnd);
			}
		}
		return "";
	}
	
	// In the form username,0~sword#armour!100'
	public static void setSanguine(String username, String sanguineDamage) throws IOException{
		if (searchUsername(username)) {
			String replacement = ((username + ",") + sanguineDamage + getSword(username) + getArmour(username) + "!" + getCoins(username) + "'");

			replaceUserDetails(username, replacement);
			

		} else {
			System.out.println("no such username");
		}
	}
	
	public static void setCoins(String username, String coins) throws IOException{
		if (searchUsername(username)) {
			String replacement = (getUsernamePassword(username) + getSword(username) + getArmour(username) + "!" + coins + "'");

			replaceUserDetails(username, replacement);
			

		} else {
			System.out.println("no such username");
		}
	}

	
	public static void main(String[] args) throws IOException {
//		System.out.println(getUserDetails("user"));
		setSanguine("user","20");
//		setCoins("user", "1000");
	}
}

