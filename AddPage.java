package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPage extends JFrame {
    private JPanel addMenuPanel;
    private JLabel addMenuLabel;
    private JButton btnAddCourse;
    private JButton btnAddFaculty;
    private JButton btnAddDepartment;
    private JButton btnAddProgram;

    private JPanel addCoursePanel;
    private JLabel newCourseLabel;
    private JLabel courseNameLabel;
    private JTextField courseNameField;
    private JLabel courseIDLabel;
    private JTextField courseIDField;
    private JLabel courseDescriptionLabel;
    private JTextArea courseDescriptionField;
    private JLabel courseHoursLabel;
    private JTextField courseHoursField;
    private JLabel courseCreditsLabel;
    private JTextField courseCreditsField;
    private JLabel courseLabInfoLabel;
    private JTextArea courseLabInfoField;
    private JLabel coursePrereqLabel;
    private JTextField coursePrereqField;
    private JLabel courseAntireqLabel;
    private JTextField courseAntireqField;
    private JButton createCourse;


    private JPanel addItemPanel;
    //Still need to finish

    //Constructor
    AddPage(){
        setTitle("Add New Faculty, Department, Program or Course");
        setBounds(100, 30, 800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);


        //Create addMenuPanel====================================================================================
        addMenuPanel = new JPanel();
        addMenuPanel.setBounds(0 ,0, 280, 500);
        addMenuPanel.setLayout(null);
        getContentPane().add(addMenuPanel);

        addMenuLabel = new JLabel("Please select the item you wish to add");
        addMenuLabel.setBounds(30, 25, 220, 50);
        addMenuPanel.add(addMenuLabel);

        btnAddFaculty = new JButton("Faculty");
        btnAddFaculty.setBounds(90, 100, 100, 50);
        addMenuPanel.add(btnAddFaculty);

        btnAddDepartment = new JButton("Department");
        btnAddDepartment.setBounds(90, 175, 100, 50 );
        addMenuPanel.add(btnAddDepartment);

        btnAddProgram = new JButton("Program");
        btnAddProgram.setBounds(90, 250, 100, 50);
        addMenuPanel.add(btnAddProgram);

        btnAddCourse = new JButton("Course");
        btnAddCourse.setBounds(90, 325, 100, 50);
        btnAddCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCoursePanel.setVisible(true);
            }
        });
        addMenuPanel.add(btnAddCourse);


        //Create addCoursePanel========================================================================================
        addCoursePanel = new JPanel();
        addCoursePanel.setBounds(280,0,520, 800);
        addCoursePanel.setLayout(null);
        addCoursePanel.setVisible(false);
        getContentPane().add(addCoursePanel);

        newCourseLabel = new JLabel("New Course");
        newCourseLabel.setBounds(210, 2, 100, 30);
        addCoursePanel.add(newCourseLabel);

        courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setBounds(80, 35, 100, 30); //y was 100
        addCoursePanel.add(courseNameLabel);

        courseNameField = new JTextField("Please enter the name of the Course");
        courseNameField.setBounds(190,35,240, 30 );
        addCoursePanel.add(courseNameField);

        courseIDLabel = new JLabel ("Course ID:");
        courseIDLabel.setBounds(80, 75, 100, 30);
        addCoursePanel.add(courseIDLabel);

        courseIDField = new JTextField("Please enter the ID number of the Course");
        courseIDField.setBounds(190, 75, 240, 30);
        addCoursePanel.add(courseIDField);

        courseDescriptionLabel = new JLabel("Course Description:");
        courseDescriptionLabel.setBounds(160, 115 , 200, 30);
        addCoursePanel.add(courseDescriptionLabel);

        courseDescriptionField = new JTextArea("Please enter the Course Description");
        courseDescriptionField.setBounds(60,155, 400, 200);
        addCoursePanel.add(courseDescriptionField);

        courseHoursLabel = new JLabel("Course Hours:");
        courseHoursLabel.setBounds(80, 375, 100, 30);
        addCoursePanel.add(courseHoursLabel);

        courseHoursField = new JTextField("Please enter the number of hours of instruction per week");
        courseHoursField.setBounds(190, 375, 310, 30);
        addCoursePanel.add(courseHoursField);

        courseCreditsLabel = new JLabel("Course Credits:");
        courseCreditsLabel.setBounds(80, 415, 100, 30 );
        addCoursePanel.add(courseCreditsLabel);

        courseCreditsField = new JTextField("Please enter the credit value for the course");
        courseCreditsField.setBounds(190, 415, 310, 30);
        addCoursePanel.add(courseCreditsField);

        courseLabInfoLabel = new JLabel("Lab Info:");
        courseLabInfoLabel.setBounds(60, 455, 200, 30);
        addCoursePanel.add(courseLabInfoLabel);

        courseLabInfoField = new JTextArea("Please enter lab info for the course");
        courseLabInfoField.setBounds(60, 495, 400, 100);
        addCoursePanel.add(courseLabInfoField);

        coursePrereqLabel = new JLabel("Course Prerequisites:");
        coursePrereqLabel.setBounds(50, 605, 130, 30);
        addCoursePanel.add(coursePrereqLabel);

        coursePrereqField = new JTextField("Please enter the course ID's of the courses prerequisites");
        coursePrereqField.setBounds(190, 605, 310, 30);
        addCoursePanel.add(coursePrereqField);

        courseAntireqLabel = new JLabel("Course Antirequisites");
        courseAntireqLabel.setBounds(50, 645, 130, 30 );
        addCoursePanel.add(courseAntireqLabel);

        courseAntireqField = new JTextField("Please enter the course ID's of the courses antirequisites");
        courseAntireqField.setBounds(190, 645, 310, 30);
        addCoursePanel.add(courseAntireqField);

        createCourse = new JButton("Create Course");
        createCourse.setBounds(210, 685, 100, 50);
        addCoursePanel.add(createCourse);


        //Create addItemPanel








    }

}
