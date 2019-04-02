package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CourseAddPage extends JFrame {
    private JPanel outerPanel;
    private JPanel courseAddPanel;
    private JLabel addLabel;
    private JLabel courseNameLabel;
    private JTextField courseNameField;
    private JLabel courseIDLabel;
    private JTextField courseIDField;
    private JLabel parentIDLabel;
    private JTextField parentIDField;
    private JLabel courseDescriptionLabel;
    private JTextArea courseDescriptionField;
    private JScrollPane courseDescriptionPane;
    private JLabel courseLabInfoLabel;
    private JTextArea courseLabInfoField;
    private JScrollPane courseLabInfoPane;
    private JLabel coursePrereqLabel;
    private JTextField coursePrereqField;
    private JScrollPane coursePrereqPane;
    private JLabel courseAnitreqLabel;
    private JTextField courseAntireqField;
    private JScrollPane courseAntireqPane;
    private JLabel courseHoursLabel;
    private JTextField courseHoursField;
    private JLabel courseCreditsLabel;
    private JTextField courseCreditsField;
    private JButton btnCreateCourse;
    private JLabel errorMessage;
    private JLabel confirmationMessage;

    public CourseAddPage(){

        setResizable(false);
        setTitle("Course Add Page");
        setBounds(100,100, 900, 530);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        outerPanel = new JPanel();
        outerPanel.setBackground(new Color(176, 196, 222));
        outerPanel.setBounds(0,0, 900, 530);
        getContentPane().add(outerPanel);
        outerPanel.setLayout(null);

        courseAddPanel = new JPanel();
        courseAddPanel.setBackground(SystemColor.window);
        courseAddPanel.setBounds(10, 10, 865, 470);
        outerPanel.add(courseAddPanel);
        courseAddPanel.setLayout(null);

        addLabel = new JLabel("Create New Course");
        addLabel.setBounds(170, 5 , 110, 30);
        addLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseAddPanel.add(addLabel);

        courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseNameLabel.setBounds(60,50,110,30 );
        courseAddPanel.add(courseNameLabel);

        courseNameField = new JTextField("Enter name of new Course");
        courseNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        courseNameField.setBounds(165, 50, 250, 30);
        courseAddPanel.add(courseNameField);

        courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseIDLabel.setBounds( 60,100,80, 30);
        courseAddPanel.add(courseIDLabel);

        courseIDField = new JTextField("Enter 4-digit ID # of new Course");
        courseIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        courseIDField.setBounds( 165,100, 250, 30);
        courseAddPanel.add(courseIDField);

        parentIDLabel = new JLabel("Parent Prgm. ID:");
        parentIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        parentIDLabel.setBounds(60, 150, 100, 30);
        courseAddPanel.add(parentIDLabel);

        parentIDField = new JTextField("Enter 4-digit ID # of parent Program of Course");
        parentIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        parentIDField.setBounds(165, 150, 250, 30);
        courseAddPanel.add(parentIDField);

        courseDescriptionLabel = new JLabel("Course Description:");
        courseDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseDescriptionLabel.setBounds(60,200, 200, 30 );
        courseAddPanel.add(courseDescriptionLabel);

        courseDescriptionField = new JTextArea("Enter Course Description");
        courseDescriptionField.setBounds(35,250, 380, 200);
        courseDescriptionPane = new JScrollPane(courseDescriptionField);
        courseDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        courseDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        courseDescriptionPane.setBounds(courseDescriptionField.getBounds());
        courseAddPanel.add(courseDescriptionPane);

        coursePrereqLabel = new JLabel("Course Prerequisites:");
        coursePrereqLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        coursePrereqLabel.setBounds(450, 50, 130, 15);
        courseAddPanel.add(coursePrereqLabel);

        coursePrereqField = new JTextField("Enter ID #'s of Prerequisites of New Course separated by spaces");
        coursePrereqField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        coursePrereqField.setBounds(450, 75 , 380, 50);
        coursePrereqPane = new JScrollPane(coursePrereqField);
        coursePrereqPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        coursePrereqPane.setBounds(coursePrereqField.getBounds());
        courseAddPanel.add(coursePrereqPane);

        courseAnitreqLabel = new JLabel("Course Antirequisites:");
        courseAnitreqLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseAnitreqLabel.setBounds(450, 135, 130, 15);
        courseAddPanel.add(courseAnitreqLabel);

        courseAntireqField = new JTextField("Enter ID #'s of Antirequisites of New Course separated by spaces");
        courseAntireqField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseAntireqField.setBounds(450,160, 380, 50);
        courseAntireqPane = new JScrollPane(courseAntireqField);
        courseAntireqPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        courseAntireqPane.setBounds(courseAntireqField.getBounds());
        courseAddPanel.add(courseAntireqPane);

        courseLabInfoLabel = new JLabel("Lab Info:");
        courseLabInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseLabInfoLabel.setBounds(475, 220, 200, 30);
        courseAddPanel.add(courseLabInfoLabel);

        courseLabInfoField = new JTextArea("Enter Lab Info for course");
        courseLabInfoField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseLabInfoField.setBounds(455, 250, 360, 100 );
        courseLabInfoPane = new JScrollPane(courseLabInfoField);
        courseLabInfoPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        courseLabInfoPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        courseLabInfoPane.setBounds(courseLabInfoField.getBounds());
        courseAddPanel.add(courseLabInfoPane);

        courseHoursLabel = new JLabel("Course Hours:");
        courseHoursLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseHoursLabel.setBounds(450, 370, 90, 15);
        courseAddPanel.add(courseHoursLabel);

        courseHoursField = new JTextField("hrs of instruction per week");
        courseHoursField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseHoursField.setBounds(550, 365,170 , 30);
        courseAddPanel.add(courseHoursField);

        courseCreditsLabel = new JLabel("Course Credits:");
        courseCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseCreditsLabel.setBounds(450, 415, 90, 15);
        courseAddPanel.add(courseCreditsLabel);

        courseCreditsField = new JTextField("# of credits");
        courseCreditsField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseCreditsField.setBounds(550, 410, 100, 30);
        courseAddPanel.add(courseCreditsField);

        btnCreateCourse = new JButton("Add Course");
        btnCreateCourse.setBounds(725, 400, 120, 30 );
        btnCreateCourse.setFont(new Font("Sitka Small", Font.PLAIN, 14));
        btnCreateCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                 * Check for valid user input
                 */
                /**
                 * Check for valid user input
                 */
                int courseIDinput;
                int parentIDinput;
                int[] prereqInput;
                int[] antireqInput;
                double hrsInput;
                double creditsInput;
                List<Integer> savedCourseIDs = new ArrayList<Integer>();
                for (int i : CSVTools.getIDList("department")){
                    savedCourseIDs.add(i);
                }
                List<Integer> savedPrgmIDs = new ArrayList<Integer>();
                for (int i : CSVTools.getIDList("program")){
                    savedPrgmIDs.add(i);
                }

                // Validate Course ID input (check for integer input and duplicate Course ID's)
                try {
                    courseIDinput = Integer.parseInt(courseIDField.getText());
                } catch (NumberFormatException e1){
                    courseIDField.setText("*Invalid Course ID entered*");
                    return;
                }
                if (savedCourseIDs.contains(courseIDinput)){
                    courseIDField.setText("*Duplicate ID found, Use another ID*");
                    return;
                }

                // Validate Parent Program ID input (check for integer input and existence of corresponding program)
                try {
                    parentIDinput = Integer.parseInt(parentIDField.getText());
                } catch (NumberFormatException e1) {
                    parentIDField.setText("*Invalid Program ID entered*");
                    return;
                }
                if ( !(savedPrgmIDs.contains(parentIDinput)) ){
                    parentIDField.setText("*No Such Program*");
                    return;
                }

                // Validate Course Prerequisite ID inputs (check for integer input and existence of corresponding courses)
                try {
                    String[] coursePrereqStrings = coursePrereqField.getText().split(" ");
                    //Check for no prereq input
                    if ( !(coursePrereqStrings[0].equals("")) ){
                        prereqInput = new int[coursePrereqStrings.length];
                        for (int i=0; i<coursePrereqStrings.length; i++){
                            prereqInput[i] = Integer.parseInt(coursePrereqStrings[i]);
                        }
                    } else {
                        prereqInput = null;
                    }
                } catch (NumberFormatException e1){
                    coursePrereqField.setText("*Invalid Course ID #'s entered*");
                    return;
                }
                //only check for existence of corresponding courses if proper ID #'s are entered appropriately
                if (prereqInput != null) {
                    for (int i=0; i<prereqInput.length; i++){
                        if ( !(savedCourseIDs.contains(prereqInput[i])) ){
                            coursePrereqField.setText("*No such Courses*");
                            return;
                        } else{
                            continue;
                        }
                    }
                }

                // Validate Course Antirequisite ID inputs (check for integers and the existence of corresponding courses)
                try {
                    String[] courseAntireqStrings = courseAntireqField.getText().split(" ");
                    // Check for no antireq input
                    if ( !(courseAntireqStrings[0].equals("")) ){
                        antireqInput = new int[courseAntireqStrings.length];
                        for (int i=0; i<courseAntireqStrings.length; i++){
                            antireqInput[i] = Integer.parseInt(courseAntireqStrings[i]);
                        }
                    } else {
                        antireqInput = null;
                    }
                } catch (NumberFormatException e1){
                    courseAntireqField.setText("*Invalid Course ID #'s entered");
                    return;
                }
                // Only check for existence of corresponding courses if proper ID #'s are entered appropriately
                if (antireqInput != null){
                    for (int i=0; i<antireqInput.length; i++){
                        if ( !(savedCourseIDs.contains(antireqInput[i])) ){
                            courseAntireqField.setText("*No such Courses*");
                            return;
                        } else{
                            continue;
                        }
                    }
                }

                // Validate Course Hours input (check for double float #)
                try {
                    hrsInput = Double.parseDouble(courseHoursField.getText());
                } catch (NumberFormatException e1){
                    courseHoursField.setText("*Invalid*");
                    return;
                }

                // Validate Course credits input (check for double float #)
                try {
                    creditsInput = Double.parseDouble(courseCreditsField.getText());
                } catch (NumberFormatException e1) {
                    courseCreditsField.setText("*Invalid*");
                    return;
                }

                /**
                 * Valid Course inputs, create and add new Course
                 */
                Course createdCourse = new Course(courseNameField.getText(),
                        courseIDinput,
                        courseDescriptionField.getText(),
                        parentIDinput,
                        hrsInput,
                        creditsInput,
                        courseLabInfoField.getText(),
                        prereqInput,
                        antireqInput
                );

                CSVTools.addChildIDtoParent(createdCourse);
                CSVTools.addCourse(createdCourse);
                confirmationMessage.setText("Department Created");
                confirmationMessage.setVisible(true);
                return;
            }
        });
        courseAddPanel.add(btnCreateCourse);

        confirmationMessage = new JLabel();
        confirmationMessage.setBounds(710, 440, 120, 15);
        confirmationMessage.setVisible(false);
        courseAddPanel.add(confirmationMessage);
    }
}
