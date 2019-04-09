package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Data.BasicLogin;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * GUI Class for Course + Program Manager
 * Currently contains User Login and Logout Functionalities
 * @author Michael Manila
 */

public class CourseManagerGUI {

	
	//Create Instance of Login Class
	BasicLogin logins = new BasicLogin();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseManagerGUI window = new CourseManagerGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Components used in CourseManagerGui
	 */

	//Main Frame
	private JFrame frmCourseAndProgram;

	//Login panel components
	private JPanel loginPanel;
	private JLabel lblUsername;
	private JTextField usernameField;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblCourseAndProgram;
	private JPanel whiteLoginPanel;
	private JLabel loginMessage;

	//Main Menu components
	private JPanel menuPanel;
	private JLabel lblSearch;
	private JButton btnSearchFaculty;
	private JButton btnSearchDepartment;
	private JButton btnSearchPrograms;
	private JButton btnSearchCourses;
	private JPanel searchWhitePanel;
	private JLabel lblAdd;
	private JButton btnAddFaculty;
	private JButton btnAddDepartment;
	private JButton btnAddProgram;
	private JButton btnAddCourse;
	private JPanel addWhitePanel;
	private JButton btnLogout;
	private JButton btnSettings;
	private JPanel menuTopRight;
    private JLabel errorMessage;

	//Settings Menu components
	private JPanel settingsPanel;
	private JButton btnBack;
	private JPanel settingsTopPanel;
	private JLabel lblUsernameAndPassword;
	private JPanel usernameSettingsPanel;
	private JLabel lblCurrentUsername;
	private JLabel lblNewUsername;
	private JLabel lblVerifyNewUsername;
	private JButton btnChangeUsername;
	private JLabel lblWrongCurrUser;
	private JTextField txtCurrentUsername;
	private JTextField txtNewUsername;
	private JTextField txtRepeatUsername;
	private JPasswordField reenteredPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField currentPasswordField;
	private JLabel lblUserLength;
	private JLabel lblRepeatUserNoMatch;
	private JLabel lblUsernameChanged;
	private JPanel passwordPanel;
	private JLabel lblCurrentPassword;
	private JLabel lblNewPassword;
	private JLabel lblReenterNewPassword;
	private JButton btnChangePassword;
	private JLabel lblWrongCurrPass;
	private JLabel lblPassLength;
	private JLabel lblRepeatPassNoMatch;
	private JLabel lblPWChanged;


	/**
	 * Create the application.
	 */
	public CourseManagerGUI() {
		initialize();
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmCourseAndProgram = new JFrame();
		frmCourseAndProgram.setResizable(false);
		frmCourseAndProgram.setTitle("Course and Program Manager");
		frmCourseAndProgram.setBounds(100, 100, 650, 400);
		frmCourseAndProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCourseAndProgram.getContentPane().setLayout(null);
		frmCourseAndProgram.setVisible(true);



		/**============================================================================================================
		 * Create Login Page
		 */
		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 644, 371);
		loginPanel.setBackground(new Color(176, 196, 222));
		loginPanel.setLayout(null);
		frmCourseAndProgram.getContentPane().add(loginPanel);

		// Username Label
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(375, 61, 169, 31);
		loginPanel.add(lblUsername);
		
		// Password Label
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(375, 160, 169, 31);
		loginPanel.add(lblPassword);
		
		//Username Text Field
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameField.setBounds(375, 95, 230, 31);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		// Password Field
		passwordField = new JPasswordField();
		passwordField.setBounds(375, 195, 230, 31);
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String usernameInput = usernameField.getText();
					String pwInput = "";

					// Get password from password field and gradually create a string
					char[] passwordArr = passwordField.getPassword();
					int pwLength = passwordArr.length;
					for(int i =0; i < pwLength; i++) {
						pwInput += passwordArr[i];
					}
					// Prints to help check getting proper input
					//System.out.println(usernameInput);
					//System.out.println(pwInput);

					// check if login username and password are correct
					if(logins.loginCheck(usernameInput, pwInput) == true) {

						//Switch to Menu Panel
						loginPanel.setVisible(false);
						menuPanel.setVisible(true);

						//Clear username and password inputed so user doesn't have to clear text field after logout
						usernameField.setText(null);
						passwordField.setText(null);
						loginMessage.setVisible(false);

					}

					else {
						// Incorrect Username and Password Combination, display message and clear password field
						loginMessage.setVisible(true);
						passwordField.setText(null);
					}
				}
			}
		});
		loginPanel.add(passwordField);
		
		//Log in Button
		btnLogin = new JButton("Log In");
		btnLogin.setBackground(new Color(176, 196, 222));
		btnLogin.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		btnLogin.setBounds(400, 250, 150, 30);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String usernameInput = usernameField.getText();
				String pwInput = "";

				// Get password from password field and gradually create a string
				char[] passwordArr = passwordField.getPassword();
				int pwLength = passwordArr.length;
				for(int i =0; i < pwLength; i++) {
					pwInput += passwordArr[i];
				}
				// Prints to help check getting proper input
				//System.out.println(usernameInput);
				//System.out.println(pwInput);

				// check if login username and password are correct
				if(logins.loginCheck(usernameInput, pwInput) == true) {

					//Switch to Menu Panel
					loginPanel.setVisible(false);
					menuPanel.setVisible(true);

					//Clear username and password inputed so user doesn't have to clear text field after logout
					usernameField.setText(null);
					passwordField.setText(null);
					loginMessage.setVisible(false);

				}

				else {
					// Incorrect Username and Password Combination, display message and clear password field
					loginMessage.setVisible(true);
					passwordField.setText(null);
				}


			}
		});
		loginPanel.add(btnLogin);
		
		//Label on the left side of Login Screen
		lblCourseAndProgram = new JLabel("<html>Course and <br>Program Manager</html>");
		lblCourseAndProgram.setBackground(new Color(176, 196, 222));
		lblCourseAndProgram.setBounds(28, 93, 278, 182);
		loginPanel.add(lblCourseAndProgram);
		lblCourseAndProgram.setFont(new Font("Sitka Small", Font.PLAIN, 25));

		//Another panel on Login Screen for aesthetics
		whiteLoginPanel = new JPanel();
		whiteLoginPanel.setBackground(Color.WHITE);
		whiteLoginPanel.setBounds(342, 0, 302, 371);
		loginPanel.add(whiteLoginPanel);
		whiteLoginPanel.setLayout(null);
		
		// Login Message for when user enters incorrect combination
		loginMessage = new JLabel("<html>Wrong Username and <br>Password Combination</html>");
		loginMessage.setBounds(37, 293, 210, 48);
		whiteLoginPanel.add(loginMessage);
		loginMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		loginMessage.setHorizontalAlignment(SwingConstants.CENTER);
		loginMessage.setForeground(Color.RED);
		loginMessage.setVisible(false);



		/**=============================================================================================================
		 * Create Main Menu Page
		 */
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(176, 196, 222));
		menuPanel.setBounds(0, 0, 644, 371);
		//Set Visibility so login screen will show on start up and not menu
		menuPanel.setVisible(false);
		menuPanel.setLayout(null);
		frmCourseAndProgram.getContentPane().add(menuPanel);

		// Search Label
		lblSearch = new JLabel("Manage:");
		lblSearch.setBounds(25, 100, 90, 20);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuPanel.add(lblSearch);

		// Search Faculty Button
		btnSearchFaculty = new JButton("Faculty");
		btnSearchFaculty.setBackground(new Color(176, 196, 222));
		btnSearchFaculty.setBounds(60, 130, 120, 30);
		btnSearchFaculty.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		btnSearchFaculty.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e){
				//create and open Faculty search window
				openItemSearchPage(CSVTools.typeF, btnSearchFaculty);
			}
		});
		menuPanel.add(btnSearchFaculty);
		
		// Search Department Button
		btnSearchDepartment = new JButton("Department");
		btnSearchDepartment.setBackground(new Color(176, 196, 222));
		btnSearchDepartment.setBounds(60, 170, 120, 30);
		btnSearchDepartment.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		btnSearchDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create and open Department search window
				openItemSearchPage(CSVTools.typeD, btnSearchDepartment);
			}

		});
		menuPanel.add(btnSearchDepartment);
		
		// Search Programs Button
		btnSearchPrograms = new JButton("Programs");
		btnSearchPrograms.setBackground(new Color(176, 196, 222));
		btnSearchPrograms.setBounds(60, 210, 120, 30);
		btnSearchPrograms.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		btnSearchPrograms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openItemSearchPage(CSVTools.typeP, btnSearchPrograms);
			}
		});
		menuPanel.add(btnSearchPrograms);

		// Search Course Button
		btnSearchCourses = new JButton("Courses");
		btnSearchCourses.setBackground(new Color(176, 196, 222));
		btnSearchCourses.setBounds(60, 250, 120, 30);
		btnSearchCourses.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		btnSearchCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openItemSearchPage(CSVTools.typeC, btnSearchCourses);
			}
		});
		menuPanel.add(btnSearchCourses);

		// White Panel behind search buttons
		searchWhitePanel = new JPanel();
		searchWhitePanel.setBounds(10, 86, 230, 230);
		menuPanel.add(searchWhitePanel);
		searchWhitePanel.setBackground(SystemColor.window);

		// Error Message for Search Buttons
		errorMessage = new JLabel();
		errorMessage.setBounds(10, 326, 200, 30);
		errorMessage.setVisible(false);
		menuPanel.add(errorMessage);

		// Logout Button
		btnLogout = new JButton("Logout");
		btnLogout.setBackground(new Color(176, 196, 222));
		btnLogout.setBounds(534, 11, 100, 30);
		btnLogout.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		btnLogout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Switch to Login Panel
				loginPanel.setVisible(true);
				menuPanel.setVisible(false);

			}
		});
		menuPanel.add(btnLogout);
		
		// Settings Button
		btnSettings = new JButton("Settings");
		btnSettings.setBackground(new Color(176, 196, 222));
		btnSettings.setBounds(534, 52, 100, 30);
		btnSettings.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		btnSettings.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				settingsPanel.setVisible(true);
				menuPanel.setVisible(false);
			}
		});
		menuPanel.add(btnSettings);

		// White Panel behind logout and settings
		menuTopRight = new JPanel();
		menuTopRight.setBounds(519, 0, 125, 95);
		menuPanel.add(menuTopRight);
		menuTopRight.setBackground(SystemColor.window);



		/**=============================================================================================================
		 * Create Settings Panel
		 */
		settingsPanel = new JPanel();
		settingsPanel.setBounds(0, 0, 645, 370);
		frmCourseAndProgram.getContentPane().add(settingsPanel);
		settingsPanel.setBackground(new Color(176, 196, 222));
		// Set Visibility so it will not show on start up, only when settings pressed 
		settingsPanel.setVisible(false);
		settingsPanel.setLayout(null);
		
		// Back button in settings page to go back to menu
		btnBack = new JButton("Back");
		btnBack.setBounds(520, 20, 85, 30);
		btnBack.setBackground(new Color(176, 196, 222));
		btnBack.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Hide username change error messages
				lblWrongCurrUser.setVisible(false);
				lblUserLength.setVisible(false);
				lblRepeatUserNoMatch.setVisible(false);
				lblUsernameChanged.setVisible(false);

				// Hide password change error messages
				lblWrongCurrPass.setVisible(false);
				lblPassLength.setVisible(false);
				lblRepeatPassNoMatch.setVisible(false);
				lblPWChanged.setVisible(false);

				// Swap visibility to menu page
				settingsPanel.setVisible(false);
				menuPanel.setVisible(true);

				// clear username change text boxes
				txtCurrentUsername.setText(null);
				txtNewUsername.setText(null);
				txtRepeatUsername.setText(null);

				// clear password change text boxes
				currentPasswordField.setText(null);
				newPasswordField.setText(null);
				reenteredPasswordField.setText(null);
			}
		});
		settingsPanel.add(btnBack);
		
		// White Panel on Settings Page
		settingsTopPanel = new JPanel();
		settingsTopPanel.setBackground(new Color(255, 255, 255));
		settingsTopPanel.setBounds(25, 11, 610, 47);
		settingsPanel.add(settingsTopPanel);
		settingsTopPanel.setLayout(null);
		
		// Text on top of Settings Page
		lblUsernameAndPassword = new JLabel("Username and Password Settings");
		lblUsernameAndPassword.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		lblUsernameAndPassword.setBounds(20, 9, 450, 30);
		settingsTopPanel.add(lblUsernameAndPassword);
		
		// White Panel on Username Side (Left)
		usernameSettingsPanel = new JPanel();
		usernameSettingsPanel.setBackground(Color.WHITE);
		usernameSettingsPanel.setBounds(25, 65, 275, 276);
		settingsPanel.add(usernameSettingsPanel);
		usernameSettingsPanel.setLayout(null);
		
		// Current Username label
		lblCurrentUsername = new JLabel("Current Username");
		lblCurrentUsername.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblCurrentUsername.setBounds(10, 10, 125, 20);
		usernameSettingsPanel.add(lblCurrentUsername);
		
		// Text field for user to type in current user
		txtCurrentUsername = new JTextField();
		txtCurrentUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCurrentUsername.setBounds(10, 35, 250, 25);
		usernameSettingsPanel.add(txtCurrentUsername);
		txtCurrentUsername.setColumns(10);
		
		// New Username Label
		lblNewUsername = new JLabel("New Username (5 or more characters)");
		lblNewUsername.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblNewUsername.setBounds(10, 70, 250, 20);
		usernameSettingsPanel.add(lblNewUsername);
		
		// Text field for new Username
		txtNewUsername = new JTextField();
		txtNewUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNewUsername.setColumns(10);
		txtNewUsername.setBounds(10, 95, 250, 25);
		usernameSettingsPanel.add(txtNewUsername);
		
		// Re-enter Username Label
		lblVerifyNewUsername = new JLabel("Re-Enter New Username");
		lblVerifyNewUsername.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblVerifyNewUsername.setBounds(10, 130, 140, 20);
		usernameSettingsPanel.add(lblVerifyNewUsername);
		
		// Repeat username text box
		txtRepeatUsername = new JTextField();
		txtRepeatUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRepeatUsername.setColumns(10);
		txtRepeatUsername.setBounds(10, 155, 250, 25);
		usernameSettingsPanel.add(txtRepeatUsername);
		
		// Button for change Username
		btnChangeUsername = new JButton("Change Username");
		btnChangeUsername.setBackground(new Color(176, 196, 222));
		btnChangeUsername.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		btnChangeUsername.setBounds(60, 195, 150, 30);
		btnChangeUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String currUser = txtCurrentUsername.getText();
				String newUser = txtNewUsername.getText();
				String repeatUser = txtRepeatUsername.getText();

				/**
				 * Try To Change Username and display messages accordingly
				 */
				int userResult = logins.changeUsername(currUser, newUser, repeatUser);
				if (userResult == 0) {
					lblWrongCurrUser.setVisible(true);
					lblUserLength.setVisible(false);
					lblRepeatUserNoMatch.setVisible(false);
					lblUsernameChanged.setVisible(false);
				} else if (userResult == 1) {
					lblWrongCurrUser.setVisible(false);
					lblUserLength.setVisible(true);
					lblRepeatUserNoMatch.setVisible(false);
					lblUsernameChanged.setVisible(false);
				} else if (userResult == 2) {
					lblWrongCurrUser.setVisible(false);
					lblUserLength.setVisible(false);
					lblRepeatUserNoMatch.setVisible(true);
					lblUsernameChanged.setVisible(false);
				} else if (userResult == 3) {
					lblWrongCurrUser.setVisible(false);
					lblUserLength.setVisible(false);
					lblRepeatUserNoMatch.setVisible(false);
					lblUsernameChanged.setVisible(true);
				}
			}
		});
		usernameSettingsPanel.add(btnChangeUsername);
		
		// Message if incorrect current username
		lblWrongCurrUser = new JLabel("Incorrect Current Username");
		lblWrongCurrUser.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblWrongCurrUser.setForeground(new Color(255, 0, 0));
		lblWrongCurrUser.setBounds(57, 230, 160, 20);
		lblWrongCurrUser.setVisible(false);
		usernameSettingsPanel.add(lblWrongCurrUser);
		
		// Message for if new username is too short
		lblUserLength = new JLabel("New Username Too Short");
		lblUserLength.setForeground(Color.RED);
		lblUserLength.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblUserLength.setBounds(65, 230, 160, 20);
		lblUserLength.setVisible(false);
		usernameSettingsPanel.add(lblUserLength);
		
		// Message for if new and repeat username does not match
		lblRepeatUserNoMatch = new JLabel("Repeated Doesn't Match New Username");
		lblRepeatUserNoMatch.setForeground(Color.RED);
		lblRepeatUserNoMatch.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblRepeatUserNoMatch.setBounds(30, 230, 240, 20);
		lblRepeatUserNoMatch.setVisible(false);
		usernameSettingsPanel.add(lblRepeatUserNoMatch);
		
		// Message for confirming username change
		lblUsernameChanged = new JLabel("Username Changed");
		lblUsernameChanged.setForeground(new Color(34, 139, 34));
		lblUsernameChanged.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblUsernameChanged.setBounds(70, 230, 160, 20);
		lblUsernameChanged.setVisible(false);
		usernameSettingsPanel.add(lblUsernameChanged);
		
		// Panel for password side in settings page (
		passwordPanel = new JPanel();
		passwordPanel.setLayout(null);
		passwordPanel.setBackground(Color.WHITE);
		passwordPanel.setBounds(345, 65, 275, 276);
		settingsPanel.add(passwordPanel);
		
		// Current Password label
		lblCurrentPassword = new JLabel("Current Password");
		lblCurrentPassword.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblCurrentPassword.setBounds(10, 10, 125, 20);
		passwordPanel.add(lblCurrentPassword);
		
		// New Password Label
		lblNewPassword = new JLabel("New Password (7 or more characters)");
		lblNewPassword.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblNewPassword.setBounds(10, 70, 250, 20);
		passwordPanel.add(lblNewPassword);
		
		// re-Enter new Password label
		lblReenterNewPassword = new JLabel("Re-Enter New Password");
		lblReenterNewPassword.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblReenterNewPassword.setBounds(10, 130, 140, 20);
		passwordPanel.add(lblReenterNewPassword);
		
		// Change Password Button
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		btnChangePassword.setBackground(new Color(176, 196, 222));
		btnChangePassword.setBounds(60, 195, 150, 30);
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String currPW = "";
				String newPW = "";
				String repeatPW = "";

				/**
				 * Get Password and convert to a string
				 */
				char[] currPWArr = currentPasswordField.getPassword();
				int currPWLength = currPWArr.length;
				for(int i =0; i < currPWLength; i++) {
					currPW += currPWArr[i];
				}

				/**
				 * Get Password and convert to a string
				 */
				char[] newPWArr = newPasswordField.getPassword();
				int newPWLength = newPWArr.length;
				for(int i =0; i < newPWLength; i++) {
					newPW += newPWArr[i];
				}

				/**
				 * Get Password and convert to a string
				 */
				char[] repeatPWArr = reenteredPasswordField.getPassword();
				int repeatPWLength = repeatPWArr.length;
				for(int i =0; i < repeatPWLength; i++) {
					repeatPW += repeatPWArr[i];
				}
				/**
				 Prints To Help Check Getting Proper Input
				 System.out.println(currPW);
				 System.out.println(newPW);
				 System.out.println(repeatPW);
				 */

				/**
				 * Try To Change Password and display messages accordingly
				 */
				int userPWResult = logins.changePassword(currPW, newPW, repeatPW);
				if (userPWResult == 0) {
					lblWrongCurrPass.setVisible(true);
					lblPassLength.setVisible(false);
					lblRepeatPassNoMatch.setVisible(false);
					lblPWChanged.setVisible(false);
				} else if (userPWResult == 1) {
					lblWrongCurrPass.setVisible(false);
					lblPassLength.setVisible(true);
					lblRepeatPassNoMatch.setVisible(false);
					lblPWChanged.setVisible(false);
				} else if (userPWResult == 2) {
					lblWrongCurrPass.setVisible(false);
					lblPassLength.setVisible(false);
					lblRepeatPassNoMatch.setVisible(true);
					lblPWChanged.setVisible(false);
				} else if (userPWResult == 3) {
					lblWrongCurrPass.setVisible(false);
					lblPassLength.setVisible(false);
					lblRepeatPassNoMatch.setVisible(false);
					lblPWChanged.setVisible(true);
				}
			}
		});
		passwordPanel.add(btnChangePassword);
		
		// Re-enter Passwordfield
		reenteredPasswordField = new JPasswordField();
		reenteredPasswordField.setBounds(10, 155, 250, 25);
		passwordPanel.add(reenteredPasswordField);
		
		// New Password Field
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(10, 95, 250, 25);
		passwordPanel.add(newPasswordField);
		
		// Current Password Field
		currentPasswordField = new JPasswordField();
		currentPasswordField.setBounds(10, 35, 250, 25);
		passwordPanel.add(currentPasswordField);
		
		// Message for if wrong current username is entered
		lblWrongCurrPass = new JLabel("Wrong Current Password");
		lblWrongCurrPass.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblWrongCurrPass.setForeground(new Color(255, 0, 0));
		lblWrongCurrPass.setBounds(62, 230, 160, 20);
		lblWrongCurrPass.setVisible(false);
		passwordPanel.add(lblWrongCurrPass);
		
		// Message for if new Password is too short
		lblPassLength = new JLabel("New Password Too Short");
		lblPassLength.setForeground(Color.RED);
		lblPassLength.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblPassLength.setBounds(65, 230, 160, 20);
		lblPassLength.setVisible(false);
		passwordPanel.add(lblPassLength);
		
		// Message for if repeated username is different from new username
		lblRepeatPassNoMatch = new JLabel("Repeated Doesn't Match New Password");
		lblRepeatPassNoMatch.setForeground(Color.RED);
		lblRepeatPassNoMatch.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblRepeatPassNoMatch.setBounds(30, 230, 230, 20);
		lblRepeatPassNoMatch.setVisible(false);
		passwordPanel.add(lblRepeatPassNoMatch);
		
		// Confirmation Message for Password Changed
		lblPWChanged = new JLabel("Password Changed");
		lblPWChanged.setForeground(new Color(34, 139, 34));
		lblPWChanged.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblPWChanged.setBounds(70, 230, 160, 20);
		lblPWChanged.setVisible(false);
		passwordPanel.add(lblPWChanged);

	}

	private void openItemSearchPage(String type, JButton btn){
		try {
			ItemManagementPage itemWindow = new ItemManagementPage(type);
			itemWindow.setVisible(true);
			itemWindow.addWindowListener(new SearchPageWindowListener(btn));

			// If log out pressed while window open
			btnLogout.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//Switch to Login Panel and close Item Search page
					loginPanel.setVisible(true);
					menuPanel.setVisible(false);
					itemWindow.dispose();
				}
			});
		} catch (Exception a){
			a.printStackTrace();
		}
	}



}
