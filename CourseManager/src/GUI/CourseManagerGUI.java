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
import Data.UsersManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * GUI Class for Course + Program Manager
 * Currently contains User Login and Logout Functionalities
 * @author Michael Manila
 */

public class CourseManagerGUI {

	
	//Create Instance of Login Class
	UsersManager logins = new UsersManager();

	//Run Main Program
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
	private JLabel errorMessage1;
    private JLabel errorMessage2;

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
	private JPanel signUpTopPanel;
	private JButton backSignUpBtn;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField usernameRegister;
	private JTextField passwordRegister1;
	private JTextField passwordRegister2;
	private JButton btnSignUp_1;
	private JLabel lblPleaseEnterName;
	private JLabel lblUsernameTooShort;
	private JLabel lblPasswordTooShort;
	private JLabel lblPasswordsDontMatch;
	private JLabel lblRegistrationComplete;
	private JLabel lblUsernameTaken;
	private JPanel panel;
	private JLabel lblCourseManagerMain;


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

		/**
		 * Create Main Panels Used In Window
		 */
		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 644, 371);
		loginPanel.setBackground(new Color(176, 196, 222));
		loginPanel.setLayout(null);
		frmCourseAndProgram.getContentPane().add(loginPanel);
		
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(176, 196, 222));
		menuPanel.setBounds(0, 0, 644, 371);
		//Set Visibility so login screen will show on start up and not menu
		menuPanel.setVisible(false);
		menuPanel.setLayout(null);
		frmCourseAndProgram.getContentPane().add(menuPanel);
		
		settingsPanel = new JPanel();
		settingsPanel.setBounds(0, 0, 645, 370);
		frmCourseAndProgram.getContentPane().add(settingsPanel);
		settingsPanel.setBackground(new Color(176, 196, 222));
		// Set Visibility so it will not show on start up, only when settings pressed 
		settingsPanel.setVisible(false);
		settingsPanel.setLayout(null);
		
		JPanel registrationPanel = new JPanel();
		registrationPanel.setBackground(new Color(176, 196, 222));
		registrationPanel.setBounds(0, 0, 644, 371);
		frmCourseAndProgram.getContentPane().add(registrationPanel);
		registrationPanel.setLayout(null);
		registrationPanel.setVisible(false);
		
		// White Panel behind search buttons in menu
		searchWhitePanel = new JPanel();
		searchWhitePanel.setBounds(40, 125, 230, 230);
		menuPanel.add(searchWhitePanel);
		searchWhitePanel.setBackground(SystemColor.window);
		searchWhitePanel.setLayout(null);
				
		// Search Course Button
		btnSearchCourses = new JButton("Courses");
		btnSearchCourses.setBounds(59, 165, 120, 30);
		searchWhitePanel.add(btnSearchCourses);
		btnSearchCourses.setBackground(new Color(176, 196, 222));
		btnSearchCourses.setFont(new Font("Sitka Small", Font.PLAIN, 14));
						
		// Search Programs Button
		btnSearchPrograms = new JButton("Programs");
		btnSearchPrograms.setBounds(59, 124, 120, 30);
		searchWhitePanel.add(btnSearchPrograms);
		btnSearchPrograms.setBackground(new Color(176, 196, 222));
		btnSearchPrograms.setFont(new Font("Sitka Small", Font.PLAIN, 14));
						
		// Search Faculty Button
		btnSearchFaculty = new JButton("Faculty");
		btnSearchFaculty.setBounds(59, 42, 120, 30);
		searchWhitePanel.add(btnSearchFaculty);
		btnSearchFaculty.setBackground(new Color(176, 196, 222));
		btnSearchFaculty.setFont(new Font("Sitka Small", Font.PLAIN, 14));
								
		// Search Label
		lblSearch = new JLabel("Search for:");
		lblSearch.setBounds(10, 11, 90, 20);
		searchWhitePanel.add(lblSearch);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
										
		// Search Department Button
		btnSearchDepartment = new JButton("Department");
		btnSearchDepartment.setBounds(59, 83, 120, 30);
		searchWhitePanel.add(btnSearchDepartment);
		btnSearchDepartment.setBackground(new Color(176, 196, 222));
		btnSearchDepartment.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		btnSearchDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create and open Department window (but also disable Department button)
				try {
					if (CSVTools.fileIsEmpty("department")){
						errorMessage2.setText("No Departments created");
						errorMessage2.setVisible(true);
					} else {
						DepartmentSearchPage departmentWindow = new DepartmentSearchPage();
						btnSearchDepartment.setEnabled(false);
						departmentWindow.setVisible(true);
						departmentWindow.addWindowListener(new SearchPageWindowListener(btnSearchDepartment));
						
						btnLogout.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								//Switch to Login Panel and close Faculty search page
								loginPanel.setVisible(true);
								menuPanel.setVisible(false);
								departmentWindow.dispose();
							}
						});
					}
				}
				catch (Exception a){
						a.printStackTrace();
				}
			}

		});
		
		btnSearchFaculty.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e){
				//create and open Faculty window (but also disable Faculty button)
				try {
					if (CSVTools.fileIsEmpty("faculty")){
						errorMessage2.setText("No Faculties created");
						errorMessage2.setVisible(true);
					} else {
						FacultySearchPage facultyWindow = new FacultySearchPage();
						facultyWindow.setVisible(true);
						facultyWindow.addWindowListener(new SearchPageWindowListener(btnSearchFaculty));
						
						// If log out pressed while window open
						btnLogout.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								//Switch to Login Panel and close Faculty search page
								loginPanel.setVisible(true);
								menuPanel.setVisible(false);
								facultyWindow.dispose();
							}
						});
					}
				} catch (Exception a){
					a.printStackTrace();
				}
			}
		});
		btnSearchPrograms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (CSVTools.fileIsEmpty("program")){
						errorMessage2.setText("No Programs created");
						errorMessage2.setVisible(true);
					} else {
						ProgramSearchPage programWindow = new ProgramSearchPage();
						btnSearchPrograms.setEnabled(false);
						programWindow.setVisible(true);
						programWindow.addWindowListener(new SearchPageWindowListener(btnSearchPrograms));
						
						// If logout pressed while window is open
						btnLogout.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e){
								//Switch to Login Panel and close Faculty search page
								loginPanel.setVisible(true);
								menuPanel.setVisible(false);
								programWindow.dispose();
							}
						});
					}
					
				}
				catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		btnSearchCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (CSVTools.fileIsEmpty("course")){
						errorMessage2.setText("No Courses created");
						errorMessage2.setVisible(true);
						
					} else {
						CourseSearchPage courseWindow = new CourseSearchPage();
						btnSearchCourses.setEnabled(false);
						courseWindow.setVisible(true);
						courseWindow.addWindowListener(new SearchPageWindowListener(btnSearchCourses));
						
						// If Logout is pressed while window open
						btnLogout.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e){
								//Switch to Login Panel and close Faculty search page
								loginPanel.setVisible(true);
								menuPanel.setVisible(false);
								courseWindow.dispose();
							}
						});
					}
				}
				catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		
		// Error Message for Search Buttons
		errorMessage2 = new JLabel();
		errorMessage2.setBounds(10, 326, 200, 30);
		errorMessage2.setVisible(false);
		menuPanel.add(errorMessage2);
		
		// White Panel behind Add Buttons
		addWhitePanel = new JPanel();
		addWhitePanel.setBounds(370, 125, 230, 230);
		menuPanel.add(addWhitePanel);
		addWhitePanel.setBackground(SystemColor.window);
		addWhitePanel.setLayout(null);
		
		// Add label
		lblAdd = new JLabel("Add New:");
		lblAdd.setBounds(10, 11, 90, 20);
		addWhitePanel.add(lblAdd);
		lblAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		// Add Faculty Button
		btnAddFaculty = new JButton("Faculty");
		btnAddFaculty.setBounds(59, 42, 120, 30);
		addWhitePanel.add(btnAddFaculty);
		btnAddFaculty.setBackground(new Color(176, 196, 222));
		btnAddFaculty.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		
		// Add Department Button
		btnAddDepartment = new JButton("Department");
		btnAddDepartment.setBounds(59, 83, 120, 30);
		addWhitePanel.add(btnAddDepartment);
		btnAddDepartment.setBackground(new Color(176, 196, 222));
		btnAddDepartment.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		
		// Add Program Button
		btnAddProgram = new JButton("Program");
		btnAddProgram.setBounds(59, 124, 120, 30);
		addWhitePanel.add(btnAddProgram);
		btnAddProgram.setBackground(new Color(176, 196, 222));
		btnAddProgram.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		
		// Add Course Button
		btnAddCourse = new JButton("Course");
		btnAddCourse.setBounds(59, 165, 120, 30);
		addWhitePanel.add(btnAddCourse);
		btnAddCourse.setBackground(new Color(176, 196, 222));
		btnAddCourse.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (CSVTools.fileIsEmpty("program")){
						errorMessage1.setText("No Programs Created for Courses");
						errorMessage1.setVisible(true);
					} else {
						
						//Open Add Course Window
						CourseAddPage courseWindow = new CourseAddPage();
						btnAddCourse.setEnabled(false);
						courseWindow.setVisible(true);
						courseWindow.addWindowListener(new SearchPageWindowListener(btnAddCourse));
						
						//Hide error message if displayed by previous button press
						errorMessage1.setVisible(false);
						
						// If Logout is pressed while window open
						btnLogout.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								//Switch to Login Panel and close Faculty search page
								loginPanel.setVisible(true);
								menuPanel.setVisible(false);
								courseWindow.dispose();
							}
						});
					}
				}
				catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		btnAddProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (CSVTools.fileIsEmpty("department")){
						errorMessage1.setText("No Departments Created for Programs");
						errorMessage1.setVisible(true);
					} else {
						
						//Open Add Programs Window
						ProgramAddPage programWindow = new ProgramAddPage();
						btnAddProgram.setEnabled(false);
						programWindow.setVisible(true);
						programWindow.addWindowListener(new SearchPageWindowListener(btnAddProgram));
						
						errorMessage1.setVisible(false);
						
						// If Logout is pressed while window open
						btnLogout.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e){
								//Switch to Login Panel and close Faculty search page
								loginPanel.setVisible(true);
								menuPanel.setVisible(false);
								programWindow.dispose();
							}
						});
					}
				}
				catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (CSVTools.fileIsEmpty("faculty")){
						errorMessage1.setText("No Faculties Created for Departments");
						errorMessage1.setVisible(true);
					} else {
						DepartmentAddPage departmentWindow = new DepartmentAddPage();
						btnAddDepartment.setEnabled(false);
						departmentWindow.setVisible(true);
						departmentWindow.addWindowListener(new SearchPageWindowListener(btnAddDepartment));
						
						errorMessage1.setVisible(false);
						
						// If Logout is pressed while window open
						btnLogout.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e){
								//Switch to Login Panel and close Faculty search page
								loginPanel.setVisible(true);
								menuPanel.setVisible(false);
								departmentWindow.dispose();
							}
						});
					}
				}
				catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		btnAddFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FacultyAddPage facultyWindow = new FacultyAddPage();
					btnAddFaculty.setEnabled(false);
					facultyWindow.setVisible(true);
					facultyWindow.addWindowListener(new SearchPageWindowListener(btnAddFaculty));
					
					errorMessage1.setVisible(false);
					
					// If Logout is pressed while window open
					btnLogout.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e){
							//Switch to Login Panel and close Faculty search page
							loginPanel.setVisible(true);
							menuPanel.setVisible(false);
							facultyWindow.dispose();
						}
					});
				}
				catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		
		// Error Message for Add Buttons
		errorMessage1 = new JLabel();
		errorMessage1.setBounds(280, 326, 250, 30);
		errorMessage1.setVisible(false);
		menuPanel.add(errorMessage1);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 624, 91);
		menuPanel.add(panel);
		panel.setLayout(null);
		
		// Logout Button
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(514, 11, 100, 30);
		panel.add(btnLogout);
		btnLogout.setBackground(new Color(176, 196, 222));
		btnLogout.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		
		// Settings Button
		btnSettings = new JButton("Settings");
		btnSettings.setBounds(514, 49, 100, 30);
		panel.add(btnSettings);
		btnSettings.setBackground(new Color(176, 196, 222));
		btnSettings.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		
		lblCourseManagerMain = new JLabel("Course Manager Main Menu");
		lblCourseManagerMain.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		lblCourseManagerMain.setBounds(30, 25, 400, 50);
		panel.add(lblCourseManagerMain);
		btnSettings.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				settingsPanel.setVisible(true);
				menuPanel.setVisible(false);
			}
		});
		btnLogout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Switch to Login Panel
				loginPanel.setVisible(true);
				menuPanel.setVisible(false);
				
				// Clear userLoggedIn from user manager
				logins.userLoggedOut();
				
			}
		});
			
			
			
			
		// Username Label
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(375, 35, 169, 31);
		loginPanel.add(lblUsername);
		
		// Password Label
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(375, 120, 169, 31);
		loginPanel.add(lblPassword);
		
		//Username Text Field
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameField.setBounds(375, 70, 230, 31);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		
		
		// Password Field
		passwordField = new JPasswordField();
		passwordField.setBounds(375, 155, 230, 31);
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
		btnLogin.setBounds(405, 205, 150, 30);
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
		
		//Sign Up Button On Login Page
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrationPanel.setVisible(true);
				loginPanel.setVisible(false);
				usernameField.setText(null);
				passwordField.setText(null);
				loginMessage.setVisible(false);
				lblPleaseEnterName.setVisible(false);
				lblUsernameTooShort.setVisible(false);
				lblPasswordTooShort.setVisible(false);
				lblPasswordsDontMatch.setVisible(false);
				lblRegistrationComplete.setVisible(false);
				lblUsernameTaken.setVisible(false);
			}
		});
		
		btnSignUp.setBounds(405, 245, 150, 30);
		loginPanel.add(btnSignUp);
		btnSignUp.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		btnSignUp.setBackground(new Color(176, 196, 222));
		
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
		loginMessage.setBounds(37, 275, 210, 48);
		whiteLoginPanel.add(loginMessage);
		loginMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		loginMessage.setHorizontalAlignment(SwingConstants.CENTER);
		loginMessage.setForeground(Color.RED);
		loginMessage.setVisible(false);		
				
		// Back Button in Sign up/ Registration Page
		backSignUpBtn = new JButton("Back");
		backSignUpBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			// Hide registration error messages
			lblPleaseEnterName.setVisible(false);
			lblUsernameTooShort.setVisible(false);
			lblPasswordTooShort.setVisible(false);
			lblPasswordsDontMatch.setVisible(false);
			lblRegistrationComplete.setVisible(false);
			lblUsernameTaken.setVisible(false);

			// Swap visibility to menu page
			registrationPanel.setVisible(false);
			loginPanel.setVisible(true);

			// clear text boxes
			firstName.setText(null);
			lastName.setText(null);
			usernameRegister.setText(null);
			passwordRegister1.setText(null);
			passwordRegister2.setText(null);
			}
		});
		
		// Back Button in Sign Up
		backSignUpBtn.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		backSignUpBtn.setBackground(new Color(176, 196, 222));
		backSignUpBtn.setBounds(520, 20, 85, 30);
		registrationPanel.add(backSignUpBtn);
		
		// white Panel at top of SignUp
		signUpTopPanel = new JPanel();
		signUpTopPanel.setBackground(new Color(255, 255, 255));
		signUpTopPanel.setBounds(25, 11, 609, 45);
		registrationPanel.add(signUpTopPanel);
		signUpTopPanel.setLayout(null);
		
		// Sign Up Page Top Label
		JLabel lblUserSignUp = new JLabel("User Sign Up / Registration");
		lblUserSignUp.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		lblUserSignUp.setBounds(20, 9, 450, 30);
		signUpTopPanel.add(lblUserSignUp);
		
		// Large white panel at sign up
		JPanel whiteSignUpPanel = new JPanel();
		whiteSignUpPanel.setBackground(new Color(255, 255, 255));
		whiteSignUpPanel.setBounds(25, 67, 596, 273);
		registrationPanel.add(whiteSignUpPanel);
		whiteSignUpPanel.setLayout(null);
				
		// Username Label
		JLabel lblUsername_1 = new JLabel("Username (5+ characters)");
		lblUsername_1.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblUsername_1.setBounds(25, 168, 200, 20);
		whiteSignUpPanel.add(lblUsername_1);
				
		// Password Label
		JLabel lblPassword_1 = new JLabel("Password (7+ characters)");
		lblPassword_1.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblPassword_1.setBounds(310, 11, 200, 20);
		whiteSignUpPanel.add(lblPassword_1);
				
		// Confirm Password Label
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblConfirmPassword.setBounds(310, 93, 140, 20);
		whiteSignUpPanel.add(lblConfirmPassword);
		
		// First Name Label
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblFirstName.setBounds(25, 11, 140, 20);
		whiteSignUpPanel.add(lblFirstName);
				
		// Last Name Label
		JLabel lblLastname = new JLabel("LastName");
		lblLastname.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblLastname.setBounds(25, 93, 140, 20);
		whiteSignUpPanel.add(lblLastname);
				
		// First name text box
		firstName = new JTextField();
		firstName.setBounds(25, 41, 200, 25);
		whiteSignUpPanel.add(firstName);
		firstName.setColumns(10);
				
		//Last name text box
		lastName = new JTextField();
		lastName.setBounds(25, 124, 200, 25);
		whiteSignUpPanel.add(lastName);
		lastName.setColumns(10);
		
		// Username text box
		usernameRegister = new JTextField();
		usernameRegister.setBounds(25, 199, 200, 25);
		whiteSignUpPanel.add(usernameRegister);
		usernameRegister.setColumns(10);
				
		// Password text box
		passwordRegister1 = new JTextField();
		passwordRegister1.setBounds(310, 42, 200, 25);
		whiteSignUpPanel.add(passwordRegister1);
		passwordRegister1.setColumns(10);
				
		// confirm password text box
		passwordRegister2 = new JTextField();
		passwordRegister2.setBounds(310, 124, 200, 25);
		whiteSignUpPanel.add(passwordRegister2);
		passwordRegister2.setColumns(10);
				
		// button used to confirm sign up
		btnSignUp_1 = new JButton("Sign Up / Register");
		btnSignUp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPleaseEnterName.setVisible(false);
				lblUsernameTooShort.setVisible(false);
				lblPasswordTooShort.setVisible(false);
				lblPasswordsDontMatch.setVisible(false);
				lblRegistrationComplete.setVisible(false);
				lblUsernameTaken.setVisible(false);
						
				String signUpFirst = firstName.getText();
				String signUpLast = lastName.getText();
				String signUpUser = usernameRegister.getText();
				String signUpPass1 = passwordRegister1.getText();
				String signUpPass2 = passwordRegister2.getText();
						
				/**
				 * 0 = username taken
				 * 1 = Empty first or last name
				 * 2 = Username is less than 5 char
				 * 3 = Password is less than 7 char
				 * 4 = Passwords Don't Match
				 * 5 = Sign Up success, user made.
				 */
				int registerStatus = logins.addUser(signUpFirst, signUpLast, signUpUser,
						signUpPass1, signUpPass2);
							if (registerStatus == 0) {
								lblUsernameTaken.setVisible(true);
							}
							if (registerStatus == 1) {
								lblPleaseEnterName.setVisible(true);
							}
							if (registerStatus == 2) {
								lblUsernameTooShort.setVisible(true);
							}
							if (registerStatus == 3) {
								lblPasswordTooShort.setVisible(true);
							}
							if (registerStatus == 4) {
								lblPasswordsDontMatch.setVisible(true);
							}
							if (registerStatus == 5) {
								lblRegistrationComplete.setVisible(true);
							}		
			}
		});
		btnSignUp_1.setBackground(new Color(176, 196, 222));
		btnSignUp_1.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		btnSignUp_1.setBounds(310, 190, 200, 30);
		whiteSignUpPanel.add(btnSignUp_1);
				
		/**
		 * Error Messages in Sign up Page		
		 */
		lblPleaseEnterName = new JLabel("Please Enter a First and Last Name");
		lblPleaseEnterName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPleaseEnterName.setForeground(new Color(255, 0, 0));
		lblPleaseEnterName.setBounds(310, 231, 225, 20);
		whiteSignUpPanel.add(lblPleaseEnterName);
				
		lblUsernameTooShort = new JLabel("Username Too Short");
		lblUsernameTooShort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsernameTooShort.setForeground(new Color(255, 0, 0));
		lblUsernameTooShort.setBounds(330, 231, 225, 20);
		whiteSignUpPanel.add(lblUsernameTooShort);
				
		lblUsernameTaken = new JLabel("Username Taken");
		lblUsernameTaken.setForeground(new Color(255, 0, 0));
		lblUsernameTaken.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsernameTaken.setBounds(325, 231, 225, 20);
		whiteSignUpPanel.add(lblUsernameTaken);
		
		lblPasswordTooShort = new JLabel("Password Too Short");
		lblPasswordTooShort.setForeground(Color.RED);
		lblPasswordTooShort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPasswordTooShort.setBounds(325, 231, 225, 20);
		whiteSignUpPanel.add(lblPasswordTooShort);
		
		lblPasswordsDontMatch = new JLabel("Passwords Don't Match");
		lblPasswordsDontMatch.setForeground(Color.RED);
		lblPasswordsDontMatch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPasswordsDontMatch.setBounds(325, 231, 225, 20);
		whiteSignUpPanel.add(lblPasswordsDontMatch);
		
		lblRegistrationComplete = new JLabel("Registration Complete");
		lblRegistrationComplete.setForeground(new Color(34, 139, 34));
		lblRegistrationComplete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRegistrationComplete.setBounds(325, 231, 225, 20);
		whiteSignUpPanel.add(lblRegistrationComplete);
		
		
		
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
}
