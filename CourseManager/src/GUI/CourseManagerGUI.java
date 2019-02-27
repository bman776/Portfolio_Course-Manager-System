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
import java.awt.Button;


/**
 * GUI Class for Course + Program Manager
 * Currently contains User Login and Logout Functionalities
 * @author Michael Manila
 */

public class CourseManagerGUI {

	/*
	 * Default username and password
	 */
	private String testUsername = "admin";
	private String testPassword = "Password1";
	

	private JFrame frmCourseAndProgram;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
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
		frmCourseAndProgram.setTitle("Course and Program Manager");
		frmCourseAndProgram.setBounds(100, 100, 450, 300);
		frmCourseAndProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCourseAndProgram.getContentPane().setLayout(null);

		/*
		 * Create login Panel and add components
		 */
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 434, 261);
		frmCourseAndProgram.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(10, 35, 70, 14);
		loginPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 75, 70, 14);
		loginPanel.add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setBounds(90, 32, 86, 20);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 72, 86, 20);
		loginPanel.add(passwordField);
		
		JLabel loginMessage = new JLabel("Invalid Username/Password Combination");
		loginMessage.setBounds(90, 103, 345, 14);
		loginMessage.setForeground(Color.RED);
		loginPanel.add(loginMessage);
		loginMessage.setVisible(false);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(200, 140, 90, 23);
		loginPanel.add(btnLogin);
		
		
		/*
		 * Create menu panel and add components
		 */
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 434, 261);
		frmCourseAndProgram.getContentPane().add(menuPanel);
		menuPanel.setLayout(null);
		menuPanel.setVisible(false);
		
		JButton btnCourses = new JButton("Courses");
		btnCourses.setBounds(10, 215, 89, 23);
		menuPanel.add(btnCourses);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(10, 87, 46, 14);
		menuPanel.add(lblSearch);
		
		JButton btnFaculty = new JButton("Faculty");
		btnFaculty.setBounds(10, 112, 89, 23);
		menuPanel.add(btnFaculty);
		
		JButton btnDepartment = new JButton("Department");
		btnDepartment.setBounds(10, 146, 89, 23);
		menuPanel.add(btnDepartment);
		
		JButton btnPrograms = new JButton("Programs");
		btnPrograms.setBounds(10, 180, 89, 23);
		menuPanel.add(btnPrograms);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(335, 7, 89, 23);
		menuPanel.add(btnLogout);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBounds(335, 41, 89, 23);
		menuPanel.add(btnSettings);
		
		/**
		 * Action Listener for Login Button
		 */
		btnLogin.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        String usernameInput = usernameField.getText();
	        String pwInput = "";
	        
	        char[] passwordArr = passwordField.getPassword();
	        int pwLength = passwordArr.length;
	        for(int i =0; i < pwLength; i++) {
	        	pwInput += passwordArr[i];
	        }
	        
	        System.out.println(usernameInput);
	        System.out.println(pwInput);
	        if(usernameInput.equals(testUsername) && pwInput.equals(testPassword)) {
	        	System.out.println("Valid");
	        	
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
		 * Action Listener for when Logout Button pressed
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
		
	}
}
