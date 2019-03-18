package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPage extends JFrame {

    //Main menu components
    private JPanel addMenuPanel;
    private JLabel addMenuLabel;
    private JButton btnAddCourse;
    private JButton btnAddFaculty;
    private JButton btnAddDepartment;
    private JButton btnAddProgram;
    private JButton btnCreateItem;
    private JButton btnCreateCourse;
    private JLabel itemCreationConfirmation;
    private JButton btnCancel;
    private JLabel errorMessage;


    //add Course panel components
    private JPanel addCoursePanel;
    private JLabel newCourseLabel;
    private JLabel courseNameLabel;
    private JTextField courseNameField;
    private JLabel courseIDLabel;
    private JTextField courseIDField;
    private JLabel courseProgramLabel;
    private JTextField courseProgramField;
    private JLabel courseDescriptionLabel;
    private JScrollPane courseDescriptionPane;
    private JTextArea courseDescriptionField;
    private JLabel courseHoursLabel;
    private JTextField courseHoursField;
    private JLabel courseCreditsLabel;
    private JTextField courseCreditsField;
    private JLabel courseLabInfoLabel;
    private JScrollPane courseLabInfoPane;
    private JTextArea courseLabInfoField;
    private JLabel coursePrereqLabel;
    private JScrollPane coursePrereqPane;
    private JTextField coursePrereqField;
    private JLabel courseAntireqLabel;
    private JScrollPane courseAntireqPane;
    private JTextField courseAntireqField;


    //add Faculty/Department/Program panel components
    private JPanel addItemPanel;
    private JLabel newItemLabel;
    private JLabel itemNameLabel;
    private JTextField itemNameField;
    private JLabel itemIDLabel;
    private JTextField itemIDField;
    private JLabel itemParentLabel;
    private JTextField itemParentField;
    private JLabel itemDescriptionLabel;
    private JTextArea itemDescriptionField;
    private JScrollPane itemDescriptionPane;

    //Constructor
    public AddPage(){
        setTitle("Add New Faculty, Department, Program or Course");
        setBounds(100, 30, 900, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);


        //Create addMenuPanel==========================================================================================
        addMenuPanel = new JPanel();
        addMenuPanel.setBounds(0 ,0, 280, 800);
        addMenuPanel.setLayout(null);
        getContentPane().add(addMenuPanel);

        addMenuLabel = new JLabel("Please select the item you wish to add");
        addMenuLabel.setBounds(30, 25, 220, 50);
        addMenuPanel.add(addMenuLabel);

        btnAddFaculty = new JButton("Faculty");
        btnAddFaculty.setBounds(90, 100, 100, 50);
        btnAddFaculty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Disable/Enable Buttons
                selectionMade("Create Faculty");

                //Display addItemPanel
                newItemLabel.setText("New Faculty");
                itemNameLabel.setText("Faculty Name:");
                itemNameField.setText("Enter Name of Faculty");
                itemIDLabel.setText("Faculty ID:");
                itemIDField.setText("Enter 4-digit ID # of Faculty");
                itemParentLabel.setVisible(false);
                itemParentField.setVisible(false);
                itemDescriptionLabel.setText("Faculty Description");
                itemDescriptionField.setText("Enter Faculty Description");
                addItemPanel.setVisible(true);
            }
        });
        addMenuPanel.add(btnAddFaculty);

        btnAddDepartment = new JButton("Department");
        btnAddDepartment.setBounds(90, 175, 100, 50 );
        btnAddDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (CSVTools.fileIsEmpty("faculty")){
                    errorMessage.setText("<html>No Faculties created:"+"<br/>"+
                            "Faculties must be created for Dept.s</html>");
                    errorMessage.setVisible(true);
                    btnCancel.setVisible(true);

                } else {
                    selectionMade("Create Department");

                    newItemLabel.setText("New Department");
                    itemNameLabel.setText("Dept. Name:");
                    itemNameField.setText("Enter Name of Dept.");
                    itemIDLabel.setText("Dept. ID:");
                    itemIDField.setText("Enter 4-digit ID # of Dept.");
                    itemParentLabel.setVisible(true);
                    itemParentField.setVisible(true);
                    itemParentLabel.setText("Faculty of Dept.");
                    itemParentField.setText("Enter 4-digit ID # of Faculty of Dept.");
                    itemDescriptionLabel.setText("Department Description");
                    itemDescriptionField.setText("Enter Department Description");
                    addItemPanel.setVisible(true);
                }
            }
        });
        addMenuPanel.add(btnAddDepartment);

        btnAddProgram = new JButton("Program");
        btnAddProgram.setBounds(90, 250, 100, 50);
        btnAddProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (CSVTools.fileIsEmpty("department")){
                    errorMessage.setText("<html>No Dept.'s created:"+"<br/>"+
                            "Dept.s must be created for Programs</html>");
                    errorMessage.setVisible(true);
                    btnCancel.setVisible(true);
                } else {
                    selectionMade("Create Program");

                    newItemLabel.setText("New Program");
                    itemNameLabel.setText("Prgm. Name:");
                    itemNameField.setText("Enter Name of Program");
                    itemIDLabel.setText("Prgm. ID:");
                    itemIDField.setText("Enter 4-digit ID # of Program");
                    itemParentLabel.setVisible(true);
                    itemParentField.setVisible(true);
                    itemParentLabel.setText("Dept. of Program:");
                    itemParentField.setText("Enter 4-digit ID # of Dept. of program");
                    itemDescriptionLabel.setText("Program Description");
                    itemDescriptionField.setText("Enter Program Description");
                    addItemPanel.setVisible(true);
                }

            }
        });
        addMenuPanel.add(btnAddProgram);

        btnAddCourse = new JButton("Course");
        btnAddCourse.setBounds(90, 325, 100, 50);
        btnAddCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (CSVTools.fileIsEmpty("program")){
                    disableSelectionButtons();
                    errorMessage.setText("<html>No Programs created:"+"<br/>"+
                            "Programs must be created for Courses</html>");
                    errorMessage.setVisible(true);
                    btnCancel.setVisible(true);
                } else {
                    disableSelectionButtons();
                    btnCreateCourse.setVisible(true);
                    btnCancel.setVisible(true);
                    itemCreationConfirmation.setVisible(false);
                    addCoursePanel.setVisible(true);
                }
            }
        });
        addMenuPanel.add(btnAddCourse);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(65, 590, 150, 50);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableSelectionButtons();
                btnCreateItem.setVisible(false);
                errorMessage.setVisible(false);
                btnCancel.setVisible(false);
                addItemPanel.setVisible(false);
                addCoursePanel.setVisible(false);
                btnCreateCourse.setVisible(false);
            }
        });
        addMenuPanel.add(btnCancel);
        btnCancel.setVisible(false);

        btnCreateCourse = new JButton("Create Course");
        btnCreateCourse.setBounds(65, 650, 150, 50);
        btnCreateCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check for duplicate ID's
                int [] facultyIDs = CSVTools.getIDList("faculty");
                int [] departmentIDs = CSVTools.getIDList("department");
                int [] programIDs = CSVTools.getIDList("program");
                int [] courseIDs = CSVTools.getIDList("course");
                for (int i = 0; i<facultyIDs.length; i++){
                    if ( Integer.parseInt(courseIDField.getText()) == facultyIDs[i]){
                        errorMessage.setText("<html>Duplicate Faculty ID found,<br/> enter different ID</html>");
                        errorMessage.setVisible(true);
                        return;
                    }
                }
                for (int i = 0; i<departmentIDs.length; i++){
                    if ( Integer.parseInt(courseIDField.getText()) == departmentIDs[i]){
                        errorMessage.setText("<html>Duplicate Dept. ID found,<br/>enter different ID</html>");
                        errorMessage.setVisible(true);
                        return;
                    }
                }
                for (int i = 0; i<programIDs.length; i++){
                    if ( Integer.parseInt(courseIDField.getText()) == programIDs[i]){
                        errorMessage.setText("<html>Duplicate program ID found,<br/> enter different ID</html>");
                        errorMessage.setVisible(true);
                        return;
                    }
                }
                for (int i = 0; i<courseIDs.length; i++){
                    if ( Integer.parseInt(courseIDField.getText()) == courseIDs[i]){
                        errorMessage.setText("<html>Duplicate Course ID found,<br/> enter different ID</html>");
                        errorMessage.setVisible(true);
                        return;
                    }
                }
                Course createdCourse = new Course (courseNameField.getText(), Integer.parseInt(courseIDField.getText()),
                        courseDescriptionField.getText(), Integer.parseInt(courseProgramField.getText()),
                        Double.parseDouble(courseHoursField.getText()), Double.parseDouble(courseCreditsField.getText()),
                        courseDescriptionField.getText(), null, null);

                //set Prereqs and Antireqs
                if (!(coursePrereqField.getText().equals(""))) {
                    String[] prereqString = coursePrereqField.getText().split(" ");
                    int[] coursePrereqs = new int[prereqString.length];
                    for (int i = 0; i < coursePrereqs.length; i++)
                        coursePrereqs[i] = Integer.parseInt(prereqString[i]);
                    createdCourse.setPrerequisites(coursePrereqs);
                }
                if (!(courseAntireqField.getText().equals(""))){
                    String[] antireqString = courseAntireqField.getText().split(" ");
                    int[] courseAnitreqs = new int[antireqString.length];
                    for (int j = 0; j < courseAnitreqs.length; j++)
                        courseAnitreqs[j] = Integer.parseInt(antireqString[j]);
                    createdCourse.setAntirequisites(courseAnitreqs);
                }

                for (int i = 0; i< programIDs.length; i++){
                    if (Integer.parseInt(courseProgramField.getText()) == programIDs[i])
                        CSVTools.addChildIDtoParent(createdCourse);
                    else {
                        errorMessage.setText("<html>Invalid Program ID,<br/> enter different ID</html>");
                        errorMessage.setVisible(true);
                        return;
                    }
                }

                CSVTools.addCourse(createdCourse);
                itemCreationConfirmation.setText("Course Created");

                addCoursePanel.setVisible(false);
                addItemPanel.setVisible(false);
                enableSelectionButtons();
                btnCreateCourse.setVisible(false);
                errorMessage.setVisible(false);
                btnCancel.setVisible(false);
                itemCreationConfirmation.setVisible(true);
            }
        });
        addMenuPanel.add(btnCreateCourse);
        btnCreateCourse.setVisible(false);


        btnCreateItem = new JButton();
        btnCreateItem.setBounds(65, 650, 150 , 50 );
        btnCreateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemType = "";
                if (btnCreateItem.getText().equals("Create Faculty"))
                    itemType = "faculty";
                else if (btnCreateItem.getText().equals("Create Department"))
                    itemType= "department";
                else if (btnCreateItem.getText().equals("Create Program"))
                    itemType = "program";

                //check for duplicate ID's
                int [] facultyIDs = CSVTools.getIDList("faculty");
                int [] departmentIDs = CSVTools.getIDList("department");
                int [] programIDs = CSVTools.getIDList("program");
                int [] courseIDs = CSVTools.getIDList("course");
                for (int i = 0; i<facultyIDs.length; i++){
                    if ( Integer.parseInt(itemIDField.getText()) == facultyIDs[i]){
                        errorMessage.setText("<html>Duplicate Faculty ID found,<br/> enter different ID</html>");
                        errorMessage.setVisible(true);
                        return;
                    }
                }
                for (int i = 0; i<departmentIDs.length; i++){
                    if ( Integer.parseInt(itemIDField.getText()) == departmentIDs[i]){
                        errorMessage.setText("<html>Duplicate Dept. ID found,<br/>enter different ID</html>");
                        errorMessage.setVisible(true);
                        return;
                    }
                }
                for (int i = 0; i<programIDs.length; i++){
                    if ( Integer.parseInt(itemIDField.getText()) == programIDs[i]){
                        errorMessage.setText("<html>Duplicate program ID found,<br/> enter different ID</html>");
                        errorMessage.setVisible(true);
                        return;
                    }
                }
                for (int i = 0; i<courseIDs.length; i++){
                    if ( Integer.parseInt(itemIDField.getText()) == courseIDs[i]){
                        errorMessage.setText("<html>Duplicate Course ID found,<br/> enter different ID</html>");
                        errorMessage.setVisible(true);
                        return;
                    }
                }


                //Create Item and add into database
                if ( itemType.equals("faculty")){
                    Item createdItem = new Item(itemType, itemNameField.getText(), Integer.parseInt(itemIDField.getText()),
                            itemDescriptionField.getText(), null, 0);
                    CSVTools.addItem(createdItem);
                    itemCreationConfirmation.setText("Faculty Created");
                }
                else if ( itemType.equals("department")) {
                    Item createdItem = new Item(itemType, itemNameField.getText(), Integer.parseInt(itemIDField.getText()),
                            itemDescriptionField.getText(), null, Integer.parseInt(itemParentField.getText()));

                    for (int i = 0; i< facultyIDs.length; i++){
                        if (Integer.parseInt(itemParentField.getText()) == facultyIDs[i])
                            CSVTools.addChildIDtoParent(createdItem);
                        else {
                            errorMessage.setText("<html>Invalid Faculty ID,<br/> enter different ID</html>");
                            errorMessage.setVisible(true);
                            return;
                        }
                    }

                    CSVTools.addItem(createdItem);
                    itemCreationConfirmation.setText("Dept. Created");

                } else if (itemType.equals("program")) {
                    Item createdItem = new Item(itemType, itemNameField.getText(), Integer.parseInt(itemIDField.getText()),
                            itemDescriptionField.getText(), null, Integer.parseInt(itemParentField.getText()));
                    for (int i = 0; i< departmentIDs.length; i++){
                        if (Integer.parseInt(itemParentField.getText()) == departmentIDs[i])
                            CSVTools.addChildIDtoParent(createdItem);
                        else {
                            errorMessage.setText("<html>Invalid Department ID,<br/> enter different ID</html>");
                            errorMessage.setVisible(true);
                            return;
                        }
                    }

                    CSVTools.addItem(createdItem);
                    itemCreationConfirmation.setText("Prgm. Created");
                }


                addCoursePanel.setVisible(false);
                addItemPanel.setVisible(false);
                enableSelectionButtons();
                btnCreateItem.setVisible(false);
                errorMessage.setVisible(false);
                btnCancel.setVisible(false);
                itemCreationConfirmation.setVisible(true);

            }
        });
        addMenuPanel.add(btnCreateItem);
        btnCreateItem.setVisible(false);

        errorMessage = new JLabel();
        errorMessage.setBounds(30, 475, 250, 50);
        addMenuPanel.add(errorMessage);
        errorMessage.setVisible(false);

        itemCreationConfirmation = new JLabel();
        itemCreationConfirmation.setBounds(30,400, 250, 50);
        addMenuPanel.add(itemCreationConfirmation);
        itemCreationConfirmation.setVisible(false);

        //Create addCoursePanel========================================================================================
        addCoursePanel = new JPanel();
        addCoursePanel.setBounds(280,0,620, 800);
        addCoursePanel.setLayout(null);
        addCoursePanel.setVisible(false);
        getContentPane().add(addCoursePanel);

        newCourseLabel = new JLabel("New Course");
        newCourseLabel.setBounds(210, 2, 100, 30);
        addCoursePanel.add(newCourseLabel);

        courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setBounds(80, 35, 80, 30); //y was 100
        addCoursePanel.add(courseNameLabel);

        courseNameField = new JTextField("Enter name of Course");
        courseNameField.setBounds(190,35,240, 30 );
        addCoursePanel.add(courseNameField);

        courseIDLabel = new JLabel ("Course ID:");
        courseIDLabel.setBounds(80, 75, 60, 30);
        addCoursePanel.add(courseIDLabel);

        courseIDField = new JTextField("Enter 4-digit ID # of Course");
        courseIDField.setBounds(190, 75, 160, 30);
        addCoursePanel.add(courseIDField);

        courseProgramLabel = new JLabel("Course Program:");
        courseProgramLabel.setBounds(80, 115, 100, 30);
        addCoursePanel.add(courseProgramLabel);

        courseProgramField = new JTextField("Enter 4-digit ID # of Program of course");
        courseProgramField.setBounds(190, 115, 240, 30);
        addCoursePanel.add(courseProgramField);

        courseDescriptionLabel = new JLabel("Course Description:");
        courseDescriptionLabel.setBounds(60, 155 , 200, 30);
        addCoursePanel.add(courseDescriptionLabel);

        courseDescriptionField = new JTextArea("Enter Course Description");
        courseDescriptionField.setBounds(60,195, 400, 160);
        courseDescriptionPane = new JScrollPane(courseDescriptionField);
        courseDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        courseDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        courseDescriptionPane.setBounds(60, 195,400,160);
        addCoursePanel.add(courseDescriptionPane);

        courseHoursLabel = new JLabel("Course Hours:");
        courseHoursLabel.setBounds(80, 375, 100, 30);
        addCoursePanel.add(courseHoursLabel);

        courseHoursField = new JTextField("Enter # of hours of instruction per week");
        courseHoursField.setBounds(190, 375, 230, 30);
        addCoursePanel.add(courseHoursField);

        courseCreditsLabel = new JLabel("Course Credits:");
        courseCreditsLabel.setBounds(80, 415, 100, 30 );
        addCoursePanel.add(courseCreditsLabel);

        courseCreditsField = new JTextField("Enter credit value of course");
        courseCreditsField.setBounds(190, 415, 160, 30);
        addCoursePanel.add(courseCreditsField);

        courseLabInfoLabel = new JLabel("Lab Info:");
        courseLabInfoLabel.setBounds(60, 455, 200, 30);
        addCoursePanel.add(courseLabInfoLabel);

        courseLabInfoField = new JTextArea("Enter lab info for course");
        courseLabInfoField.setBounds(60, 495, 400, 100);
        courseLabInfoPane = new JScrollPane(courseLabInfoField);
        courseLabInfoPane.setBounds(60,495, 400, 100 );
        courseLabInfoPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        courseLabInfoPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        addCoursePanel.add(courseLabInfoPane);

        coursePrereqLabel = new JLabel("Course Prerequisites:");
        coursePrereqLabel.setBounds(50, 605, 130, 30);
        addCoursePanel.add(coursePrereqLabel);

        coursePrereqField = new JTextField("Enter course ID #'s of courses prerequisites separated by spaces");
        coursePrereqField.setBounds(190, 605, 310, 50);
        coursePrereqPane = new JScrollPane(coursePrereqField);
        coursePrereqPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        coursePrereqPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        coursePrereqPane.setBounds(190, 605,310,50);
        addCoursePanel.add(coursePrereqPane);

        courseAntireqLabel = new JLabel("Course Antirequisites");
        courseAntireqLabel.setBounds(50, 665, 130, 30 );
        addCoursePanel.add(courseAntireqLabel);

        courseAntireqField = new JTextField("Enter course ID #'s of courses anti-requisites separated by spaces");
        courseAntireqField.setBounds(190, 665, 310, 50);
        courseAntireqPane = new JScrollPane(courseAntireqField);
        courseAntireqPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        courseAntireqPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        courseAntireqPane.setBounds(190, 665,310,50);
        addCoursePanel.add(courseAntireqPane);


        //Create addItemPanel==========================================================================================
        addItemPanel = new JPanel();
        addItemPanel.setBounds(280,0,620, 800);
        addItemPanel.setLayout(null);
        addItemPanel.setVisible(false);
        getContentPane().add(addItemPanel);

        newItemLabel = new JLabel();
        newItemLabel.setBounds(210, 2, 100, 30);
        addItemPanel.add(newItemLabel);

        itemNameLabel = new JLabel();
        itemNameLabel.setBounds(80, 35, 80, 30); //y was 100
        addItemPanel.add(itemNameLabel);

        itemNameField = new JTextField();
        itemNameField.setBounds(190,35,240, 30 );
        addItemPanel.add(itemNameField);

        itemIDLabel = new JLabel ();
        itemIDLabel.setBounds(80, 75, 60, 30);
        addItemPanel.add(itemIDLabel);

        itemIDField = new JTextField();
        itemIDField.setBounds(190, 75, 160, 30);
        addItemPanel.add(itemIDField);

        itemParentLabel = new JLabel();
        itemParentLabel.setBounds(80, 115, 100, 30);
        addItemPanel.add(itemParentLabel);

        itemParentField = new JTextField();
        itemParentField.setBounds(190, 115, 240, 30);
        addItemPanel.add(itemParentField);

        itemDescriptionLabel = new JLabel();
        itemDescriptionLabel.setBounds(60, 155 , 200, 30);
        addItemPanel.add(itemDescriptionLabel);

        itemDescriptionField = new JTextArea();
        itemDescriptionField.setBounds(60,195, 400, 160);
        itemDescriptionPane = new JScrollPane(itemDescriptionField);
        itemDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        itemDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        itemDescriptionPane.setBounds(60, 195,400,160);
        addItemPanel.add(itemDescriptionPane);
    }

    private void selectionMade(String btnCreateItemText){
        disableSelectionButtons();
        btnCreateItem.setText(btnCreateItemText);
        btnCreateItem.setVisible(true);
        btnCancel.setVisible(true);
        itemCreationConfirmation.setVisible(false);
    }

    private void disableSelectionButtons(){
        btnAddFaculty.setEnabled(false);
        btnAddDepartment.setEnabled(false);
        btnAddProgram.setEnabled(false);
        btnAddCourse.setEnabled(false);
    }

    private void enableSelectionButtons(){
        btnAddFaculty.setEnabled(true);
        btnAddDepartment.setEnabled(true);
        btnAddProgram.setEnabled(true);
        btnAddCourse.setEnabled(true);
    }
}
