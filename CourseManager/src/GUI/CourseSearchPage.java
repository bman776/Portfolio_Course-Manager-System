package GUI;

import javax.swing.*;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;


public class CourseSearchPage extends JFrame {
    private JPanel courseSearchPanel;
    private JPanel outerPanel;
    private JLabel courseLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField courseNameField;
    private JTextField courseNumberField;
    private JComboBox courseList;
    private String[] savedCourses;
    private JButton searchButton;
   

    /**
     * CourseSearchPage Constructor 
     */
    public CourseSearchPage(){

        // Retrieve Saved Courses
        String[] savedCourseNames = CSVTools.getNameList("course");
        int[] savedCourseIDs = CSVTools.getIDList("course");
        savedCourses = new String[savedCourseNames.length];
        for (int i = 0; i<savedCourseNames.length; i++){
            savedCourses[i] = "ID:"+savedCourseIDs[i]+" Name: "+savedCourseNames[i];
        }

    	setResizable(false);
        setTitle("Course Search Page");
        setBounds(100,100, 500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        outerPanel = new JPanel();
        outerPanel.setBackground(new Color(176, 196, 222));
        outerPanel.setBounds(0, 0, 500, 500);
        getContentPane().add(outerPanel);
        outerPanel.setLayout(null);

        courseSearchPanel = new JPanel();
        courseSearchPanel.setBackground(SystemColor.window);
        courseSearchPanel.setBounds(10,10, 465, 440);
        outerPanel.add(courseSearchPanel);
        courseSearchPanel.setLayout(null);

        courseLabel = new JLabel("Courses:");
        courseLabel.setBounds(170, 5, 100, 30);
        courseLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseSearchPanel.add(courseLabel);

        courseList = new JComboBox(savedCourses);
        courseList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseList.setBounds(100,45,300, 30);
        courseSearchPanel.add(courseList);

        searchLabel = new JLabel("Search for a Course:");
        searchLabel.setBounds(40, 250, 150, 15);
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseSearchPanel.add(searchLabel);

        nameLabel = new JLabel("Course Name:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        nameLabel.setBounds(40,288,120,15 );
        courseSearchPanel.add(nameLabel);

        courseNameField = new JTextField();
        courseNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        courseNameField.setBounds(165, 285, 250, 25);
        courseSearchPanel.add(courseNameField);

        numberLabel = new JLabel("Course ID Number:");
        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        numberLabel.setBounds( 40,328,120, 15);
        courseSearchPanel.add(numberLabel);

        courseNumberField = new JTextField();
        courseNumberField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        courseNumberField.setBounds( 165,325, 250, 25);
        courseSearchPanel.add(courseNumberField);

        searchButton = new JButton("Search for Course");
        searchButton.setBackground(new Color(176, 196, 222));
        searchButton.setBounds(100, 370, 175, 30);
        searchButton.setFont(new Font("Sitka Small", Font.PLAIN, 14));
        courseSearchPanel.add(searchButton);

    }
}
