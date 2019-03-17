package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class BasicLogin {

	
	private String correctUsername;
	private String correctPassword;
	
	private PrintWriter outputStream = null;
	public String filePath = "./data/Login.txt";
	
	/**
	 * Default Constructor
	 */
	public BasicLogin() {	
		getCredentialsFromFile();
		
		// Prints To Help Check What Username and Password is
		//System.out.println(correctUsername);
		//System.out.print(correctPassword);
	}
	
	/**
	 * Method used to check if user inputed proper username and login at login page
	 * @return boolean, true if correct username and password, false otherwise
	 */
	public boolean loginCheck(String username, String password) {
		if (username.equals(getCorrectUsername())) {
			if(password.equals(this.getCorrectPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public String getCorrectUsername() {
		return this.correctUsername;
	}
	
	public String getCorrectPassword() {
		return this.correctPassword;
	}
	
	
	public void setCorrectUsername(String newUsername) {
		this.correctUsername = newUsername;
	}
	
	public void setCorrectPassword(String newPassword) {
		this.correctPassword = newPassword;
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
		if (current.equals(getCorrectUsername()) == true){	
			if(newUsername.length() >= 5) {
				if (newUsername.equals(repeat) == true) {
					setCorrectUsername(newUsername);
					updateFile();
					getCredentialsFromFile();
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
		if (current.equals(getCorrectPassword()) == true){	
			if(newPW.length() >= 7) {
				if (newPW.equals(repeatPW) == true) {
					setCorrectPassword(newPW);
					updateFile();
					getCredentialsFromFile();
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
	 * Reads Login.txt file and set the username and password variables
	 * @throws IOException 
	 */
	public void getCredentialsFromFile(){
		BufferedReader reader;
		try {
			
			// Read first two lines and set them as username and password
			reader = new BufferedReader(new FileReader(filePath));
			setCorrectUsername(reader.readLine());
			setCorrectPassword(reader.readLine());	
			reader.close();   
		}
		catch(IOException e) {
			
		}
	}
	
	/**
	 * Updates the Ligin.txt file with current encrypted user and pass Strings
	 */
	public void updateFile() {
        try{
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("./data/Login.txt" , false));
            outputStream.println(this.getCorrectUsername());
            outputStream.print(this.getCorrectPassword());
            outputStream.close();
        }
        catch(FileNotFoundException e) {}
	}
}
