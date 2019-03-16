package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Login Class used for Course and Program Manager Application
 * @author Michael Manila
 *
 */
public class Login {

	private String encryptedUser;
	private String encryptedPass;
	private String decryptedUser;
	private String decryptedPass;
	
	private PrintWriter outputStream = null;
	
	public String filePath = "./data/Login.txt";
	
	/**
	 * Default Constructor
	 */
	public Login() {	
		getCredentialsFromFile();
		/*
		* Prints To Help Check What Username and Password is
		System.out.println(decryptedUser);
		System.out.print(decryptedPass);
		*/
	}
	
	/**
	 * Method used to check if user inputed proper username and login at login page
	 * @return boolean, true if correct username and password, false otherwise
	 */
	public boolean loginCheck(String username, String password) {
		if (username.equals(this.decryptedUser)) {
			if(password.equals(this.decryptedPass)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * MAIN FUNCTION FOR TESTING
	 */
	/*public static void main(String[] args) {
		Login log = new Login();
		log.getCredentialsFromFile();
		log.encrypt("admin", "Password1");
		
		System.out.println(log.getDeencryptedPass());
		System.out.print(log.getDeencryptedUser());
		log.updateFile();
	
		
		log.getCredentialsFromFile();
		//System.out.println(log.getDeencryptedUser());
		//System.out.println(log.getDeencryptedPass());
		}*/
	
	/**
	 * Method used to encrypt username and password using AES cipher
	 * @param usernameToEncrypt, username to be encrypted
	 * @param passwordToEncrypt, password to be encrypted
	 */
	public void encrypt(String usernameToEncrypt, String passwordToEncrypt) {
		
		try {
            String usernameKey = "keyUsernamePlace"; // 128 bit key
            // Create key and and cipher used to encrypt
            Key aesKeyUser = new SecretKeySpec(usernameKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKeyUser);
            byte[] encryptedUsername = cipher.doFinal(usernameToEncrypt.getBytes());
            setEncryptedUser(new String(encryptedUsername));
            //System.out.println(new String(encryptedUser));
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
		
        try {
        	String password = "Password1";
            String passwordKey = "keyPasswordPlace"; // 128 bit key
            // Create key and and cipher used to encrypt
            Key aesKeyPass = new SecretKeySpec(passwordKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKeyPass);
            byte[] encryptedPassword = cipher.doFinal(passwordToEncrypt.getBytes());
            setEncryptedPass(new String(encryptedPassword));
            //System.out.println(new String(encryptedPass));
        }
        
        catch(Exception e) 
        {
            e.printStackTrace();
        }
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
		if (current.equals(getDeencryptedUser()) == true){	
			if(newUsername.length() >= 5) {
				if (newUsername.equals(repeat) == true) {
					setDeencryptedUser(newUsername);
					encrypt(newUsername, this.decryptedPass);
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
		if (current.equals(getDeencryptedPass()) == true){	
			if(newPW.length() >= 7) {
				if (newPW.equals(repeatPW) == true) {
					setDeencryptedPass(newPW);
					encrypt(this.decryptedUser, newPW);
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
	 */
	public void getCredentialsFromFile() {
		BufferedReader reader;
		try {
			
			// Read first two lines and set them as username and password
			reader = new BufferedReader(new FileReader(filePath));
			setEncryptedUser(reader.readLine());
			setEncryptedPass(reader.readLine());	
			reader.close();
		
			String usernameKey = "keyUsernamePlace"; // 128 bit key
			String passwordKey = "keyPasswordPlace"; // 128 bit key
            // Create key and cipher
            Key aesKeyUser = new SecretKeySpec(usernameKey.getBytes(), "AES");
            Key aesKeyPass = new SecretKeySpec(passwordKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            
			 // Decrypt the Username
		    cipher.init(Cipher.DECRYPT_MODE, aesKeyUser);
		    byte[] unDeencryptedUserBytes = getEncryptedUser().getBytes();
		    setDeencryptedUser(new String(cipher.doFinal(unDeencryptedUserBytes)));
		    //System.out.println(decryptedUser);
		    
		    // Decrypt the Password
		    cipher.init(Cipher.DECRYPT_MODE, aesKeyPass);
		    byte[] unDeencryptedPassBytes = getEncryptedPass().getBytes();
		    
		    setDeencryptedPass(new String(cipher.doFinal(unDeencryptedPassBytes)));
		    //System.out.println(decryptedPass);
		    
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) 
        {
            e.printStackTrace();
        }	
	}
	
	/**
	 * Updates the Ligin.txt file with current encrypted user and pass Strings
	 */
	public void updateFile() {
        try{
            outputStream = new PrintWriter(new FileOutputStream("./data/Login.txt" , false));
            outputStream.println(this.getEncryptedUser());
            outputStream.print(this.getEncryptedPass());
            outputStream.close();
        }
        catch(FileNotFoundException e) {}
	}
	
	/**
	 * Setter for encryptedUser
	 * @param lineFromFile, first line fromLogin.txt
	 */
	private void setEncryptedUser(String lineFromFile) {
		this.encryptedUser = lineFromFile;
	}
	
	/**
	 * Setter for encryptedPass
	 * @param lineFromFile, 2nd line from login.txt
	 */
	private void setEncryptedPass(String lineFromFile) {
		this.encryptedPass = lineFromFile;
	}
	
	/**
	 * Setter for decryptedUser
	 * @param decrypted, string decrypted in getCreditialsFromFile() method
	 */
	private void setDeencryptedUser(String decrypted) {
		this.decryptedUser = decrypted;
	}
	
	/**
	 * Setter for decryptedPass
	 * @param decrypted, string decrypted in getCreditialsFromFile() method
	 */
	private void setDeencryptedPass(String decrypted) {
		this.decryptedPass = decrypted;
	}
	

	/**
	 *  Getter for encryptedUser
	 * @return encyptedUser
	 */
	public String getEncryptedUser() {
		return encryptedUser;
	}
	
	/**
	 *  Getter for encryptedPass
	 * @return encyptedPass
	 */
	public String getEncryptedPass() {
		return encryptedPass;
	}
	
	/**
	 *  Getter for decryptedUser
	 * @return deencyptedUser
	 */
	public String getDeencryptedUser() {
		return decryptedUser;
	}
	
	/**
	 *  Getter for decryptedPass
	 * @return deencyptedPass
	 */
	public String getDeencryptedPass() {
		return decryptedPass;
	}
	
	
}
