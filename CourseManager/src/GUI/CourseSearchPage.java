package GUI;

import javax.swing.*;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;


public class CourseSearchPage extends JFrame {
    private JLabel courseLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField courseNameField;
    private JTextField courseNumberField;
    private JComboBox courseList;  //becomes scrollable after 8 elements
    private JPanel coursePanel;
    private String[] dummyCourses = (String[]) CSVTools.findList("course");
    private JButton searchButton;
    private JPanel panel;

    //constructor
    public CourseSearchPage(){
    	setResizable(false);
        setTitle("Faculty Page");
        setBounds(100,100, 500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        coursePanel = new JPanel();
        coursePanel.setBackground(new Color(176, 196, 222));
        coursePanel.setBounds(0,0, 494, 471);
        getContentPane().add(coursePanel);
        coursePanel.setLayout(null);

        courseList = new JComboBox(dummyCourses);
        courseList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseList.setBounds(100,45,300, 30);
        coursePanel.add(courseList);

        nameLabel = new JLabel("Course Name:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        nameLabel.setBounds(67,300,108,15 );
        coursePanel.add(nameLabel);

        numberLabel = new JLabel("Course ID Number:");
        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        numberLabel.setBounds( 44,325,131, 15);
        coursePanel.add(numberLabel);

        courseNumberField = new JTextField();
        courseNumberField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        courseNumberField.setBounds( 175,325, 250, 25);
        coursePanel.add(courseNumberField);
        
        panel = new JPanel();
        panel.setBackground(SystemColor.window);
        panel.setBounds(10, 11, 474, 449);
        coursePanel.add(panel);
        panel.setLayout(null);
        
                courseNameField = new JTextField();
                courseNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
                courseNameField.setBounds(165, 285, 250, 25);
                panel.add(courseNameField);
                
                        searchButton = new JButton("Search for Course");
                        searchButton.setBackground(new Color(176, 196, 222));
                        searchButton.setBounds(165, 360, 175, 30);
                        panel.add(searchButton);
                        searchButton.setFont(new Font("Sitka Small", Font.PLAIN, 14));
                        
                                searchLabel = new JLabel("Search for a Course");
                                searchLabel.setBounds(111, 260, 150, 15);
                                panel.add(searchLabel);
                                searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
                                
                                        courseLabel = new JLabel("Courses:");
                                        courseLabel.setBounds(177, 0, 100, 30);
                                        panel.add(courseLabel);
                                        courseLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

    }


}
