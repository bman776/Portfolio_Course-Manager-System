package GUI;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;

import Data.Login;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * GUI Class for Course + Program Manager
 * Currently contains User Login and Logout Functionalities
 * @author Michael Manila
 */

public class CourseManagerGUI {

	
	//Create Instance of Login Class
	Login logins = new Login();

	
	/**
	 * Frame, TextFields, and PasswordFields to be used Throughtout Program
	 */
	private JFrame frmCourseAndProgram;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField txtCurrentUsername;
	private JTextField txtNewUsername;
	private JTextField txtRepeatUsername;
	private JPasswordField reenteredPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField currentPasswordField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseManagerGUI window = new CourseManagerGUI();
					window.frmCourseAndProgram.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		/**
		 * Create Login Panel (Login Page)
		 */
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 644, 371);
		frmCourseAndProgram.getContentPane().add(loginPanel);
		loginPanel.setBackground(new Color(176, 196, 222));
		loginPanel.setLayout(null);
		
		// Username Label
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(375, 61, 169, 31);
		loginPanel.add(lblUsername);
		
		// Password Label
		JLabel lblPassword = new JLabel("Password");
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
		loginPanel.add(passwordField);
		
		//Log in Button
		JButton btnLogin = new JButton("Log In");
		btnLogin.setBackground(new Color(176, 196, 222));
		btnLogin.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		btnLogin.setBounds(400, 250, 150, 30);
		loginPanel.add(btnLogin);
		
		//Label on the left side of Login Screen
		JLabel lblCourseAndProgram = new JLabel("<html>Course and <br>Program Manager</html>");
		lblCourseAndProgram.setBackground(new Color(176, 196, 222));
		lblCourseAndProgram.setBounds(28, 93, 278, 182);
		loginPanel.add(lblCourseAndProgram);
		lblCourseAndProgram.setFont(new Font("Sitka Small", Font.PLAIN, 25));
		
		//Another panel on Login Screen for aesthetics
		JPanel whiteLoginPanel = new JPanel();
		whiteLoginPanel.setBackground(Color.WHITE);
		whiteLoginPanel.setBounds(342, 0, 302, 371);
		loginPanel.add(whiteLoginPanel);
		whiteLoginPanel.setLayout(null);
		
		// Login Message for when user enters incorrect combination
		JLabel loginMessage = new JLabel("<html>Wrong Username and <br>Password Combination</html>");
		loginMessage.setBounds(37, 293, 210, 48);
		whiteLoginPanel.add(loginMessage);
		loginMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		loginMessage.setHorizontalAlignment(SwingConstants.CENTER);
		loginMessage.setForeground(Color.RED);
		loginMessage.setVisible(false);

		/**
		 * Create Menu Panel (Panel after login)
		 */
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(176, 196, 222));
		menuPanel.setBounds(0, 0, 644, 371);
		frmCourseAndProgram.getContentPane().add(menuPanel);
		//Set Visibility so login screen will show on start up and not menu
		menuPanel.setVisible(false);
		menuPanel.setLayout(null);
		
		// Search Label
		JLabel lblSearch = new JLabel("Search for:");
		lblSearch.setBounds(25, 100, 90, 20);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuPanel.add(lblSearch);
		
		// Course Button
		JButton btnCourses = new JButton("Courses");
		btnCourses.setBackground(new Color(176, 196, 222));
		btnCourses.setBounds(110, 250, 120, 30);
		btnCourses.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		menuPanel.add(btnCourses);
		
		// Faculty Button
		JButton btnFaculty = new JButton("Faculty");
		btnFaculty.setBackground(new Color(176, 196, 222));
		btnFaculty.setBounds(110, 130, 120, 30);
		btnFaculty.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		menuPanel.add(btnFaculty);
		
		// Department Button
		JButton btnDepartment = new JButton("Department");
		btnDepartment.setBackground(new Color(176, 196, 222));
		btnDepartment.setBounds(110, 170, 120, 30);
		btnDepartment.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		menuPanel.add(btnDepartment);
		
		// Programs Button
		JButton btnPrograms = new JButton("Programs");
		btnPrograms.setBackground(new Color(176, 196, 222));
		btnPrograms.setBounds(110, 210, 120, 30);
		btnPrograms.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		menuPanel.add(btnPrograms);
		
		// Logout Button
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBackground(new Color(176, 196, 222));
		btnLogout.setBounds(534, 11, 100, 30);
		btnLogout.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		menuPanel.add(btnLogout);
		
		// Settings Button
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBackground(new Color(176, 196, 222));
		btnSettings.setBounds(534, 52, 100, 30);
		btnSettings.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		menuPanel.add(btnSettings);
		
		// White Panel behind search buttons
		JPanel searchWhitePanel = new JPanel();
		searchWhitePanel.setBounds(10, 86, 324, 227);
		menuPanel.add(searchWhitePanel);
		searchWhitePanel.setBackground(SystemColor.window);
		
		// White Panel behind logout and settings
		JPanel menuTopRight = new JPanel();
		menuTopRight.setBounds(519, 0, 125, 95);
		menuPanel.add(menuTopRight);
		menuTopRight.setBackground(SystemColor.window);
		
		// White Panel behind Add Button
		JPanel addWhitePanel = new JPanel();
		addWhitePanel.setBounds(384, 130, 220, 147);
		menuPanel.add(addWhitePanel);
		addWhitePanel.setBackground(SystemColor.window);
		addWhitePanel.setLayout(null);
		
		// Add Button
		JButton btnAddNew = new JButton("<html>Add Faculty,<br> Department, Program, <br>or Course</html>");
		btnAddNew.setBounds(13, 35, 190, 85);
		addWhitePanel.add(btnAddNew);
		btnAddNew.setBackground(SystemColor.activeCaption);
		btnAddNew.setHorizontalAlignment(SwingConstants.LEADING);
		btnAddNew.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		
		/**
		 * Create Settings Panel (after pressing Setting button)
		 */
		JPanel settingsPanel = new JPanel();
		settingsPanel.setBounds(0, 0, 645, 370);
		frmCourseAndProgram.getContentPane().add(settingsPanel);
		settingsPanel.setBackground(new Color(176, 196, 222));
		// Set Visibility so it will not show on start up, only when settings pressed 
		settingsPanel.setVisible(false);
		settingsPanel.setLayout(null);
		
		// Back button in settings page to go back to menu
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(520, 20, 85, 30);
		settingsPanel.add(btnBack);
		btnBack.setBackground(new Color(176, 196, 222));
		btnBack.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		
		// White Panel on Settings Page
		JPanel settingsTopPanel = new JPanel();
		settingsTopPanel.setBackground(new Color(255, 255, 255));
		settingsTopPanel.setBounds(25, 11, 610, 47);
		settingsPanel.add(settingsTopPanel);
		settingsTopPanel.setLayout(null);
		
		// Text on top of Settings Page
		JLabel lblUsernameAndPassword = new JLabel("Username and Password Settings");
		lblUsernameAndPassword.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		lblUsernameAndPassword.setBounds(20, 9, 450, 30);
		settingsTopPanel.add(lblUsernameAndPassword);
		
		// White Panel on Username Side (Left)
		JPanel usernameSettingsPanel = new JPanel();
		usernameSettingsPanel.setBackground(Color.WHITE);
		usernameSettingsPanel.setBounds(25, 65, 275, 276);
		settingsPanel.add(usernameSettingsPanel);
		usernameSettingsPanel.setLayout(null);
		
		// Current Username label
		JLabel lblCurrentUsername = new JLabel("Current Username");
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
		JLabel lblNewUsername = new JLabel("New Username (5 or more characters)");
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
		JLabel lblVerifyNewUsername = new JLabel("Re-Enter New Username");
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
		JButton btnChangeUsername = new JButton("Change Username");
		btnChangeUsername.setBackground(new Color(176, 196, 222));
		btnChangeUsername.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		btnChangeUsername.setBounds(60, 195, 150, 30);
		usernameSettingsPanel.add(btnChangeUsername);
		
		// Message if incorrect curent username
		JLabel lblWrongCurrUser = new JLabel("Incorrect Current Username");
		lblWrongCurrUser.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblWrongCurrUser.setForeground(new Color(255, 0, 0));
		lblWrongCurrUser.setBounds(57, 230, 160, 20);
		lblWrongCurrUser.setVisible(false);
		usernameSettingsPanel.add(lblWrongCurrUser);
		
		// Message for if new username is too short
		JLabel lblUserLength = new JLabel("New Username Too Short");
		lblUserLength.setForeground(Color.RED);
		lblUserLength.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblUserLength.setBounds(65, 230, 160, 20);
		lblUserLength.setVisible(false);
		usernameSettingsPanel.add(lblUserLength);
		
		// Message for if new and repeat username does not match
		JLabel lblRepeatUserNoMatch = new JLabel("Repeated Doesn't Match New Username");
		lblRepeatUserNoMatch.setForeground(Color.RED);
		lblRepeatUserNoMatch.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblRepeatUserNoMatch.setBounds(30, 230, 240, 20);
		lblRepeatUserNoMatch.setVisible(false);
		usernameSettingsPanel.add(lblRepeatUserNoMatch);
		
		// Message for confirming username change
		JLabel lblUsernameChanged = new JLabel("Username Changed");
		lblUsernameChanged.setForeground(new Color(34, 139, 34));
		lblUsernameChanged.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblUsernameChanged.setBounds(70, 230, 160, 20);
		lblUsernameChanged.setVisible(false);
		usernameSettingsPanel.add(lblUsernameChanged);
		
		// Panel for password side in settings page (
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(null);
		passwordPanel.setBackground(Color.WHITE);
		passwordPanel.setBounds(345, 65, 275, 276);
		settingsPanel.add(passwordPanel);
		
		// Current Password label
		JLabel lblCurrentPassword = new JLabel("Current Password");
		lblCurrentPassword.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblCurrentPassword.setBounds(10, 10, 125, 20);
		passwordPanel.add(lblCurrentPassword);
		
		// New Password Label
		JLabel lblNewPassword = new JLabel("New Password (7 or more characters)");
		lblNewPassword.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblNewPassword.setBounds(10, 70, 250, 20);
		passwordPanel.add(lblNewPassword);
		
		// re-Enter new Password label
		JLabel lblReenterNewPassword = new JLabel("Re-Enter New Password");
		lblReenterNewPassword.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblReenterNewPassword.setBounds(10, 130, 140, 20);
		passwordPanel.add(lblReenterNewPassword);
		
		// Change Password Button
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		btnChangePassword.setBackground(new Color(176, 196, 222));
		btnChangePassword.setBounds(60, 195, 150, 30);
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
		JLabel lblWrongCurrPass = new JLabel("Wrong Current Password");
		lblWrongCurrPass.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblWrongCurrPass.setForeground(new Color(255, 0, 0));
		lblWrongCurrPass.setBounds(62, 230, 160, 20);
		lblWrongCurrPass.setVisible(false);
		passwordPanel.add(lblWrongCurrPass);
		
		// Message for if new Password is too short
		JLabel lblPassLength = new JLabel("New Password Too Short");
		lblPassLength.setForeground(Color.RED);
		lblPassLength.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblPassLength.setBounds(65, 230, 160, 20);
		lblPassLength.setVisible(false);
		passwordPanel.add(lblPassLength);
		
		// Message for if repeated username is different from new username
		JLabel lblRepeatPassNoMatch = new JLabel("Repeated Doesn't Match New Password");
		lblRepeatPassNoMatch.setForeground(Color.RED);
		lblRepeatPassNoMatch.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblRepeatPassNoMatch.setBounds(30, 230, 230, 20);
		lblRepeatPassNoMatch.setVisible(false);
		passwordPanel.add(lblRepeatPassNoMatch);
		
		// Confirmation Message for Password Changed
		JLabel lblPWChanged = new JLabel("Password Changed");
		lblPWChanged.setForeground(new Color(34, 139, 34));
		lblPWChanged.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblPWChanged.setBounds(70, 230, 160, 20);
		lblPWChanged.setVisible(false);
		passwordPanel.add(lblPWChanged);
		
		/**
		 * Back Button in settings page action Listener
		 */
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
		
		/**
		 * Action Listener for Login Button
		 */
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
		
		/**
		 * Action Listener For when user presses enter on login Password field
		 */
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			        String usernameInput = usernameField.getText();
			        String pwInput = "";
			        
			        // Get password from password field and make a string
			        char[] passwordArr = passwordField.getPassword();
			        int pwLength = passwordArr.length;
			        for(int i =0; i < pwLength; i++) {
			        	pwInput += passwordArr[i];
			        }
			        

			        
			        // Prints to help check getting proper input
			        //System.out.println(usernameInput);
			        //System.out.println(pwInput);

			        // Check if proper username and password
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
		
		/**
		 * Action Listener for Add Button on menu
		 */
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// Make a Add page and make it visible
					AddPage addWindow = new AddPage();
					addWindow.setVisible(true);
					addWindow.addWindowListener(new SearchPageWindowListener(btnAddNew));

					/**
					 * Action Listener for when Logout Button pressed while add page is open
					 */
					btnLogout.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							//Switch to Login Panel and close add page
							loginPanel.setVisible(true);
							menuPanel.setVisible(false);
							addWindow.dispose();
						}
					});

				} catch (Exception a){
					a.printStackTrace();
				}
			}
		});
		
		/**
		 * Logout button action listener
		 */
		btnLogout.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        	//Switch to Login Panel
	        	loginPanel.setVisible(true);
	        	menuPanel.setVisible(false);
	        
	      }
	    });
		

		/**
		* Action Listeners for when the Faculty, Department, Program and Course buttons under search are hit
		*/
		btnFaculty.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e){
			//create and open Faculty window (but also disable Faculty button)
				try {
					FacultySearchPage facultyWindow = new FacultySearchPage();
					facultyWindow.setVisible(true);
					facultyWindow.addWindowListener(new SearchPageWindowListener(btnFaculty));
					
					// If log out pressed while window open
					btnLogout.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							//Switch to Login Panel and close Faculty search page
							loginPanel.setVisible(true);
							menuPanel.setVisible(false);
							facultyWindow.dispose();
						}
						});	
				} catch (Exception a){
				            a.printStackTrace();
				        }
				}
		});
		
		/**
		 * Action Listener for Department Button
		 */
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create and open Department window (but also disable Department button)
				try {
					DepartmentSearchPage departmentWindow = new DepartmentSearchPage();
					btnDepartment.setEnabled(false);
					departmentWindow.setVisible(true);
					departmentWindow.addWindowListener(new SearchPageWindowListener(btnDepartment));
					
				    btnLogout.addActionListener(new ActionListener() {			    
				    	public void actionPerformed(ActionEvent e) {
				    		//Switch to Login Panel and close Faculty search page
				    		loginPanel.setVisible(true);
				    		menuPanel.setVisible(false);
				    		departmentWindow.dispose();
				    		}
				    	});
					} 
				catch (Exception a){
				            a.printStackTrace();
					}
				}
			
			});
		
		/**
		 * Programs button action Listener
		 */
		btnPrograms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProgramSearchPage programWindow = new ProgramSearchPage();
					btnPrograms.setEnabled(false);
					programWindow.setVisible(true);
					programWindow.addWindowListener(new SearchPageWindowListener(btnPrograms));
					
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
				catch (Exception a) {
				            a.printStackTrace();
				        }
				    }
				});
		
		/**
		 * Course Button Listener
		 */
		btnCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CourseSearchPage courseWindow = new CourseSearchPage();
					btnCourses.setEnabled(false);
					courseWindow.setVisible(true);
					courseWindow.addWindowListener(new SearchPageWindowListener(btnCourses));
					
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
				catch (Exception a) {
				            a.printStackTrace();
				        }
				    }
				});
	            
				/**
				 * Action Listener For Settings
				 */
	            btnSettings.addActionListener(new ActionListener()
	            {
	                public void actionPerformed(ActionEvent e)
	                {
	                    settingsPanel.setVisible(true);
	                    menuPanel.setVisible(false);
	                }
	            });
	            
	            /**
	             * Action Listener For Change Password
	             */
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
	    				/*
	    				 * Prints To Help Check Getting Proper Input
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
	    				}
	    				
	    				else if (userPWResult == 1) {
	    					lblWrongCurrPass.setVisible(false);
	    					lblPassLength.setVisible(true);
	    					lblRepeatPassNoMatch.setVisible(false);
	    					lblPWChanged.setVisible(false);
	    				}
	    				else if (userPWResult == 2) {
	    					lblWrongCurrPass.setVisible(false);
	    					lblPassLength.setVisible(false);
	    					lblRepeatPassNoMatch.setVisible(true);
	    					lblPWChanged.setVisible(false);	
	    				}
	    				else if (userPWResult == 3) {
	    					lblWrongCurrPass.setVisible(false);
	    					lblPassLength.setVisible(false);
	    					lblRepeatPassNoMatch.setVisible(false);
	    					lblPWChanged.setVisible(true);
	    				}
	    			}
	    	    });

	            
	            /**
	             * Action Listener For Change Username
	             */
	    		btnChangeUsername.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e){
	    				String currUser = txtCurrentUsername.getText();
	    				String newUser = txtNewUsername.getText();
	    				String repeatUser = txtRepeatUsername.getText();
	    				
	    				/*
	    				 * Prints To Help Check Getting Proper Input
	    				System.out.println(currUser);
	    				System.out.println(newUser);
	    				System.out.println(repeatUser);
	    				*/
	    				
	    				/**
	    				 * Try To Change Username and display messages accordingly
	    				 */
	    				int userResult = logins.changeUsername(currUser, newUser, repeatUser);
	    				if (userResult == 0) {
	    					lblWrongCurrUser.setVisible(true);
	    					lblUserLength.setVisible(false);
	    					lblRepeatUserNoMatch.setVisible(false);
	    					lblUsernameChanged.setVisible(false);	    					
	    				}
	    				
	    				else if (userResult == 1) {
	    					lblWrongCurrUser.setVisible(false);
	    					lblUserLength.setVisible(true);
	    					lblRepeatUserNoMatch.setVisible(false);
	    					lblUsernameChanged.setVisible(false);
	    				}
	    				else if (userResult == 2) {
	    					lblWrongCurrUser.setVisible(false);
	    					lblUserLength.setVisible(false);
	    					lblRepeatUserNoMatch.setVisible(true);
	    					lblUsernameChanged.setVisible(false);	
	    				}
	    				else if (userResult == 3) {
	    					lblWrongCurrUser.setVisible(false);
	    					lblUserLength.setVisible(false);
	    					lblRepeatUserNoMatch.setVisible(false);
	    					lblUsernameChanged.setVisible(true);
	    				}
	    			}
	    	    });
	            
	            

}
}
