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


public class Login {

	private String encryptedUser;
	private String encryptedPass;
	public String decryptedUser;
	public String decryptedPass;
	
	private PrintWriter outputStream = null;
	
	public String filePath = "./data/Login.txt";
	
	/**
	 * Default Constructor
	 */
	public Login() {	
		getCredentialsFromFile();
		System.out.println(decryptedUser);
		System.out.print(decryptedPass);
	}
	
	/**
	 * 
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
	
	public void encrypt(String usernameToEncrypt, String passwordToEncrypt) {
		
		try {
        	String username = "admin";
            String usernameKey = "keyUsernamePlace"; // 128 bit key
            // Create key and and cipher used to encrypt
            Key aesKeyUser = new SecretKeySpec(usernameKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKeyUser);
            byte[] encryptedUsername = cipher.doFinal(username.getBytes());
            this.encryptedUser = new String(encryptedUsername);
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
            byte[] encryptedPassword = cipher.doFinal(password.getBytes());
            this.encryptedPass = new String(encryptedPassword);
            //System.out.println(new String(encryptedPass));
        }
        
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
	
	/**
	 * Reads Login.txt file and set the user name and pass variables
	 */
	public void getCredentialsFromFile() {
		BufferedReader reader;
		try {
			
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
	
	
}