package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class that manages the users by, loading from file, updating from file,
 * managing registrations, logins, and username/password changes,
 * @author Michael Manila
 *
 */
public class UsersManager {

	private ArrayList<User> listOfUsers = new ArrayList<User>();
	private int indexOfUserLoggedIn;
	private User userLoggedIn;
	private boolean loggedIn = false;
	
	private PrintWriter outputStream = null;
	private String filePath = "./data/Login.txt";
	private int numberOfUsers;
	
	/**
	 * Default Constructor
	 */
	public UsersManager() {	
		setNumberOfUsers(checkNumberOfLines());
		getUsersFromFile();
	}
	
	/**
	 * Checks If Login input from login page matches any of the
	 * existing users
	 * @param username, String from username text field
	 * @param password, String from password field
	 * @return, boolean true user used proper login username/pw, false if not
	 */
	public boolean loginCheck(String username, String password) {
		
		for(int i = 0; i < numberOfUsers; i++) {
			User tmp = listOfUsers.get(i);			
			if(tmp.getUsername().equals(username)) {
				if(tmp.getPassword().equals(password)) {
					userLoggedIn = tmp;
					setIndexOfUserLoggedIn(i);
					loggedIn = true;
				}
			}
		}
		return loggedIn;
	}
	
	/**
	 * Setter for userLoggedInIndex
	 * @param num, index of user from list used to log in
	 */
	public void setIndexOfUserLoggedIn(int num) {
		indexOfUserLoggedIn = num;
	}
	
	/**
	 * Clears userloggedin and sets logged in to false.
	 */
	public void userLoggedOut() {
		userLoggedIn = null;
		loggedIn = false;
	}
	
	/**
	 * Method that changes the username, updates files and variables after changes.
	 * @param current, username before change attempt.
	 * @param newUsername, username that user wishes to change to.
	 * @param repeat, username user wishes to change to.
	 * @return int, if 0 then incorrect currentPassword, if 1, then newUsername
	 * 			is less than 5 characters. If 2, new does not match repeat, and
	 * 			if 3 then username is changed.
	 */
	public int changeUsername(String current, String newUsername, String repeat) {
		int changeStatus;
		if (current.equals(userLoggedIn.getUsername()) == true){	
			if(newUsername.length() >= 5) {
				if (newUsername.equals(repeat) == true) {
					userLoggedIn.setUsername(newUsername);
					listOfUsers.remove(indexOfUserLoggedIn);
					listOfUsers.add(userLoggedIn);
					updateFile();
					getUsersFromFile();
					changeStatus = 3;
				}
				else {
					changeStatus = 2;
				}
			}
			else {
				changeStatus = 1;
			}
		}
		else {
			changeStatus = 0;
		}
		return changeStatus;
	}

	/**
	 * Method that changes the password, updates files and variables after changes.
	 * @param current, password before change attempt.
	 * @param newPw, password that user wishes to change to.
	 * @param repeatPW, password user wishes to change to.
	 * @return int, if 0 then incorrect current password, if 1, then newPW
	 * 			is less than 7 characters. If 2, new does not match repeat, and
	 * 			if 3 then password is changed.
	 */
	public int changePassword(String current, String newPW, String repeatPW) {
		int changeStatus;
		if (current.equals(userLoggedIn.getPassword()) == true){	
			if(newPW.length() >= 7) {
				if (newPW.equals(repeatPW) == true) {
					userLoggedIn.setPassword(newPW);
					listOfUsers.remove(indexOfUserLoggedIn);
					listOfUsers.add(userLoggedIn);
					updateFile();
					getUsersFromFile();
					changeStatus = 3;
				}
				else {
					changeStatus = 2;
				}
			}
			else {
				changeStatus = 1;
			}
		}
		else {
			changeStatus = 0;
		}
		return changeStatus;
	}
	
	public long checkNumberOfLines(){
		long lineCount = 0;
		try {
			Path path = Paths.get(filePath);
			lineCount = Files.lines(path).count();
			
		}
		catch(IOException e) {
			
		}
		return lineCount;
	}
	
	/**
	 * Reads Login.txt file and gets the users' info and puts it into
	 * list of users with their info.
	 */
	public void getUsersFromFile(){
		BufferedReader reader;
		listOfUsers.clear();
		try {
			
			// Read first two lines and set them as username and password
			reader = new BufferedReader(new FileReader(filePath));
			setNumberOfUsers(checkNumberOfLines());
			int counter = 0;
			while (counter < numberOfUsers){
			String firstNameInFile = reader.readLine();
			String lastNameInFile = reader.readLine();
			String usernameInFile = reader.readLine();
			String passwordInFile = reader.readLine();
			
			User userFromFile = new User(firstNameInFile, lastNameInFile, 
					usernameInFile, passwordInFile);
			listOfUsers.add(userFromFile);
			counter++;
			}
			reader.close();
		}
		catch(IOException e) {
			
		}
	}
	
	/**
	 * Setter for numberOfUsers
	 * @param lines, number of lines that are in file(when loading or editing users),
	 * 			or number of lines that file will have(when adding a user)
	 */
	public void setNumberOfUsers(long lines) {
		int linesInt = (int) lines;
		numberOfUsers = linesInt/4;
	}
	
	/**
	 * Getter for numberOfUsers
	 * @return, number of Users in file/registered
	 */
	public int getNumberOfUsers() {
		return numberOfUsers;
	}
	
	/**
	 * Updates the Login.txt file with current data of all users
	 */
	public void updateFile() {
		try{
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("./data/Login.txt" , false));
            for (int i = 0; i < getNumberOfUsers(); i++) {
            	User tmp = listOfUsers.get(i);
            	outputStream.println(tmp.getFirstName());
            	outputStream.println(tmp.getLastName());
            	outputStream.println(tmp.getUsername());
            	outputStream.println(tmp.getPassword());
            }
            outputStream.close();
        }
        catch(FileNotFoundException e) {}
	}
	
	/**
	 * Method used for registration/sign up, that will create and add a new user.
	 * @param f1, first name
	 * @param l1, last name
	 * @param u1, desired username
	 * @param p1, desired password
	 * @param p2, repeate of desired password
	 * @return changeStatus, int if 0 then username is taken, if 1 then first or last name was empty,
	 * 		 if 2 then username was too short (less than 5 char), if 3 then password too short(less than
	 *  	 7 char), if 4 = passwords didn't match, and if 5 then successful sign up, user made.
	 */
	//0 = username taken, 1 = f1 = null or l1 = null, 2 = u1 < 5 char, 3=p1 < 7 char 4 = p1!p2 5 = made
	public int addUser(String f1, String l1, String u1,
			String p1, String p2) {
		int changeStatus = 0;
		if (f1.length() > 0) { 
			if(l1.length() > 0) {
				if(u1.length() >= 5) {
					if(p1.length() >= 7) {
						if(p1.equals(p2)) {
							for(int i = 0; i < numberOfUsers; i++) {
								User tmp = listOfUsers.get(i);
									if(tmp.getUsername().equals(u1)) {
										changeStatus = 0;
										return changeStatus;	
									}
								}
						
							User tmp = new User(f1, l1, u1, p1);
							listOfUsers.add(tmp);
							setNumberOfUsers(checkNumberOfLines() + 4);
							updateFile();
							getUsersFromFile();
							changeStatus = 5;
						}
						else {
							changeStatus = 4;
						}
					}
					else {
						changeStatus = 3;
					}
				}
				else {
					changeStatus = 2;
				}
			}
			else {
				changeStatus = 1;
			}
		}
		else {
			changeStatus = 1;
		}
		return changeStatus;
	}
}
