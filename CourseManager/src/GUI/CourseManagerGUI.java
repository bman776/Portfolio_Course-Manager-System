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
		frmCourseAndProgram.setResizable(false);
		frmCourseAndProgram.setTitle("Course and Program Manager");
		frmCourseAndProgram.setBounds(100, 100, 650, 400);
		frmCourseAndProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCourseAndProgram.getContentPane().setLayout(null);
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 644, 371);
		frmCourseAndProgram.getContentPane().add(loginPanel);
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
		
		// Username Text Field
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
		btnLogin.setBackground(SystemColor.activeCaption);
		btnLogin.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		btnLogin.setBounds(413, 247, 146, 31);
		loginPanel.add(btnLogin);
		
		
		JPanel blueLoginPanel = new JPanel();
		blueLoginPanel.setBackground(SystemColor.activeCaption);
		blueLoginPanel.setBounds(0, 0, 345, 371);
		loginPanel.add(blueLoginPanel);
		blueLoginPanel.setLayout(null);
		
		JLabel lblCourseAndProgram = new JLabel("<html>Course and <br>Program Manager</html>");
		lblCourseAndProgram.setFont(new Font("Sitka Small", Font.PLAIN, 25));
		lblCourseAndProgram.setBounds(28, 72, 278, 182);
		blueLoginPanel.add(lblCourseAndProgram);
		
		JPanel whiteLoginPanel = new JPanel();
		whiteLoginPanel.setBackground(Color.WHITE);
		whiteLoginPanel.setBounds(342, 0, 302, 371);
		loginPanel.add(whiteLoginPanel);
		whiteLoginPanel.setLayout(null);
		
		JLabel loginMessage = new JLabel("<html>Wrong Username and <br>Password Combination</html>");
		loginMessage.setBounds(38, 289, 210, 48);
		whiteLoginPanel.add(loginMessage);
		loginMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		loginMessage.setHorizontalAlignment(SwingConstants.CENTER);
		loginMessage.setForeground(Color.RED);
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 644, 371);
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
		btnLogout.setBounds(545, 11, 89, 23);
		menuPanel.add(btnLogout);
		
		loginMessage.setVisible(false);
		
		
		/*
		 * Create menu panel and add components
		 */
		
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
	        
	        
	        // Prints to help check getting proper input
	        //System.out.println(usernameInput);
	        //System.out.println(pwInput);
	        
	        if(usernameInput.equals(testUsername) && pwInput.equals(testPassword)) {
	        	
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
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBounds(545, 45, 89, 23);
		menuPanel.add(btnSettings);
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
