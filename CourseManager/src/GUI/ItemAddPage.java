package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ItemAddPage extends JFrame {

    /**
     *  Components used if Item is of type Faculty, Department, Program or Course
     */
    private JPanel outerPanel;
    private JPanel itemAddPanel;
    private JLabel addLabel;
    private JLabel itemNameLabel;
    private JTextField itemNameField;
    private JLabel itemIDLabel;
    private JTextField itemIDField;
    private JLabel parentIDLabel;
    private JTextField parentIDField;
    private JLabel itemDescriptionLabel;
    private JTextArea itemDescriptionField;
    private JScrollPane itemDescriptionPane;
    private JButton btnCreateItem;

    /**
     *  Components used if Item is of type Course
     */
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

    public ItemAddPage(String type, JLabel searchPageMssg){

        if (type.equals(CSVTools.typeC)) {
            /**
             *  Item Add page is arranged to receive Course information
             */
            setResizable(false);
            setTitle("Course Add Page");
            setBounds(600,400, 900, 530);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);

            outerPanel = new JPanel();
            outerPanel.setBackground(new Color(176, 196, 222));
            outerPanel.setBounds(0,0, 900, 530);
            getContentPane().add(outerPanel);
            outerPanel.setLayout(null);

            itemAddPanel = new JPanel();
            itemAddPanel.setBackground(SystemColor.window);
            itemAddPanel.setBounds(10, 10, 865, 470);
            outerPanel.add(itemAddPanel);
            itemAddPanel.setLayout(null);

            addLabel = new JLabel("Create New Course");
            addLabel.setBounds(170, 5 , 110, 30);
            addLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemAddPanel.add(addLabel);

            itemNameLabel = new JLabel("Course Name:");
            itemNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemNameLabel.setBounds(60,50,110,30 );
            itemAddPanel.add(itemNameLabel);

            itemNameField = new JTextField("Enter name of new Course");
            itemNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
            itemNameField.setBounds(165, 50, 250, 30);
            itemAddPanel.add(itemNameField);

            itemIDLabel = new JLabel("Course ID:");
            itemIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemIDLabel.setBounds( 60,100,80, 30);
            itemAddPanel.add(itemIDLabel);

            itemIDField = new JTextField("Enter 4-digit ID # of new Course");
            itemIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
            itemIDField.setBounds( 165,100, 250, 30);
            itemAddPanel.add(itemIDField);

            parentIDLabel = new JLabel("Parent Prgm. ID:");
            parentIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
            parentIDLabel.setBounds(60, 150, 100, 30);
            itemAddPanel.add(parentIDLabel);

            parentIDField = new JTextField("Enter 4-digit ID # of parent Program of Course");
            parentIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
            parentIDField.setBounds(165, 150, 250, 30);
            itemAddPanel.add(parentIDField);

            itemDescriptionLabel = new JLabel("Course Description:");
            itemDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemDescriptionLabel.setBounds(60,200, 200, 30 );
            itemAddPanel.add(itemDescriptionLabel);

            itemDescriptionField = new JTextArea("Enter Course Description");
            itemDescriptionField.setBounds(35,250, 380, 200);
            itemDescriptionPane = new JScrollPane(itemDescriptionField);
            itemDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            itemDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            itemDescriptionPane.setBounds(itemDescriptionField.getBounds());
            itemAddPanel.add(itemDescriptionPane);

            coursePrereqLabel = new JLabel("Course Prerequisites:");
            coursePrereqLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            coursePrereqLabel.setBounds(450, 50, 130, 15);
            itemAddPanel.add(coursePrereqLabel);

            coursePrereqField = new JTextField("Enter ID #'s of Prerequisites of New Course separated by spaces");
            coursePrereqField.setFont(new Font("Tahoma", Font.PLAIN, 13));
            coursePrereqField.setBounds(450, 75 , 380, 50);
            coursePrereqPane = new JScrollPane(coursePrereqField);
            coursePrereqPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            coursePrereqPane.setBounds(coursePrereqField.getBounds());
            itemAddPanel.add(coursePrereqPane);

            courseAnitreqLabel = new JLabel("Course Antirequisites:");
            courseAnitreqLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseAnitreqLabel.setBounds(450, 135, 130, 15);
            itemAddPanel.add(courseAnitreqLabel);

            courseAntireqField = new JTextField("Enter ID #'s of Antirequisites of New Course separated by spaces");
            courseAntireqField.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseAntireqField.setBounds(450,160, 380, 50);
            courseAntireqPane = new JScrollPane(courseAntireqField);
            courseAntireqPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            courseAntireqPane.setBounds(courseAntireqField.getBounds());
            itemAddPanel.add(courseAntireqPane);

            courseLabInfoLabel = new JLabel("Lab Info:");
            courseLabInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseLabInfoLabel.setBounds(475, 220, 200, 30);
            itemAddPanel.add(courseLabInfoLabel);

            courseLabInfoField = new JTextArea("Enter Lab Info for course");
            courseLabInfoField.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseLabInfoField.setBounds(455, 250, 360, 100 );
            courseLabInfoPane = new JScrollPane(courseLabInfoField);
            courseLabInfoPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            courseLabInfoPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            courseLabInfoPane.setBounds(courseLabInfoField.getBounds());
            itemAddPanel.add(courseLabInfoPane);

            courseHoursLabel = new JLabel("Course Hours:");
            courseHoursLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseHoursLabel.setBounds(450, 370, 90, 15);
            itemAddPanel.add(courseHoursLabel);

            courseHoursField = new JTextField("hrs of instruction per week");
            courseHoursField.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseHoursField.setBounds(550, 365,170 , 30);
            itemAddPanel.add(courseHoursField);

            courseCreditsLabel = new JLabel("Course Credits:");
            courseCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseCreditsLabel.setBounds(450, 415, 90, 15);
            itemAddPanel.add(courseCreditsLabel);

            courseCreditsField = new JTextField("# of credits");
            courseCreditsField.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseCreditsField.setBounds(550, 410, 100, 30);
            itemAddPanel.add(courseCreditsField);

            btnCreateCourse = new JButton("Add Course");
            btnCreateCourse.setBounds(725, 400, 120, 30 );
            btnCreateCourse.setFont(new Font("Sitka Small", Font.PLAIN, 14));
            btnCreateCourse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /**
                     * Creates new Course based on user input in JTextField and JTextArea components in this JFrame
                     * Returns false if user has entered invalid input in any of the JTextField and JTextArea components
                     * Returns true and creates new Course in CSV files if user entered valid input
                     */
                    if (!(createCourse())){
                        //Invalid Input entered by user somewhere in JFrame components
                        return;
                    } else {
                        dispose();
                        searchPageMssg.setText("Course Created");
                        searchPageMssg.setVisible(true);
                    }
                }
            });
            itemAddPanel.add(btnCreateCourse);
        }
        else {
            /**
             * Item Add page is arranged to receive Faculty, Department or program information (all have same format)
             */
            setResizable(false);
            setTitle(type.substring(0,1).toUpperCase()+type.substring(1)+" Add Page");
            setBounds(1000,200, 500, 580);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);

            outerPanel = new JPanel();
            outerPanel.setBackground(new Color(176, 196, 222));
            outerPanel.setBounds(0,0, 500, 580);
            getContentPane().add(outerPanel);
            outerPanel.setLayout(null);

            itemAddPanel = new JPanel();
            itemAddPanel.setBackground(SystemColor.window);
            itemAddPanel.setBounds(10, 10, 465, 520);
            outerPanel.add(itemAddPanel);
            itemAddPanel.setLayout(null);

            addLabel = new JLabel("Create New "+type.substring(0,1).toUpperCase()+type.substring(1));
            addLabel.setBounds(170, 5 , 110, 30);
            addLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemAddPanel.add(addLabel);

            itemNameLabel = new JLabel(type.substring(0,1).toUpperCase()+type.substring(1)+" Name:");
            itemNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemNameLabel.setBounds(60,50,110,30 );
            itemAddPanel.add(itemNameLabel);

            itemNameField = new JTextField("Enter name of new "+type.substring(0,1).toUpperCase()+type.substring(1));
            itemNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
            itemNameField.setBounds(175, 50, 240, 30);
            itemAddPanel.add(itemNameField);

            itemIDLabel = new JLabel(type.substring(0,1).toUpperCase()+type.substring(1)+" ID:");
            itemIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemIDLabel.setBounds( 60,100,90, 30);
            itemAddPanel.add(itemIDLabel);

            itemIDField = new JTextField("Enter 4-digit ID # of new "+type.substring(0,1).toUpperCase()+type.substring(1));
            itemIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
            itemIDField.setBounds( 165,100, 250, 30);
            itemAddPanel.add(itemIDField);

            parentIDLabel = new JLabel();
            parentIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
            parentIDLabel.setBounds(60, 150, 100, 30);
            if (type.equals(CSVTools.typeD)){
                parentIDLabel.setText("Parent Faculty ID:");
                itemAddPanel.add(parentIDLabel);
            } else if (type.equals(CSVTools.typeP)){
                parentIDLabel.setText("Parent Dept. ID:");
                itemAddPanel.add(parentIDLabel);
            } else{
                //Faculties have no parent items so parent ID not needed
            }

            parentIDField = new JTextField("Enter 4-digit ID # of parent Faculty of dept.");
            parentIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
            parentIDField.setBounds(165, 150, 250, 30);
            if (type.equals(CSVTools.typeD)){
                parentIDField.setText("Enter 4-digit ID # of parent Faculty of Dept.");
                itemAddPanel.add(parentIDField);
            } else if (type.equals(CSVTools.typeP)){
                parentIDField.setText("Enter 4-digit ID # of parent Dept. of program");
                itemAddPanel.add(parentIDField);
            } else {
                //Faculties have no parent items so parent ID not needed
            }


            itemDescriptionLabel = new JLabel(type.substring(0,1).toUpperCase()+type.substring(1)+" Description:");
            itemDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemDescriptionLabel.setBounds(60,200, 200, 30 );
            itemAddPanel.add(itemDescriptionLabel);

            itemDescriptionField = new JTextArea("Enter "+type.substring(0,1).toUpperCase()+type.substring(1)+" Description");
            itemDescriptionField.setBounds(35,250, 400, 200);
            itemDescriptionPane = new JScrollPane(itemDescriptionField);
            itemDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            itemDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            itemDescriptionPane.setBounds(itemDescriptionField.getBounds());
            itemAddPanel.add(itemDescriptionPane);

            btnCreateItem = new JButton("Add "+type.substring(0,1).toUpperCase()+type.substring(1));
            btnCreateItem.setBounds(265, 470, 120, 30 );
            btnCreateItem.setFont(new Font("Sitka Small", Font.PLAIN, 14));
            btnCreateItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /**
                     * Creates new Item based on user input in JTextField and JTextArea components in this JFrame
                     * Returns false if user has entered invalid input in any of the JTextField and JTextArea components
                     * Returns true and creates new Item in CSV files if user entered valid input
                     */
                    if (!(createItem(type))){
                        //Invalid Input entered by user somewhere in JFrame components
                        return;
                    } else {
                        dispose();
                        searchPageMssg.setText(type.substring(0,1).toUpperCase()+type.substring(1)+" Created");
                        searchPageMssg.setVisible(true);
                    }
                }
            });
            itemAddPanel.add(btnCreateItem);
        }
    }

    private boolean createCourse(){
        /**
         * Validate user input
         */
        int courseIDinput;
        int parentIDinput;
        int[] prereqInput;
        int[] antireqInput;
        double hrsInput;
        double creditsInput;
        List<Integer> savedCourseIDs = new ArrayList<Integer>();
        for (int i : CSVTools.getIDList(CSVTools.typeC, CSVTools.fromCreated)){
            savedCourseIDs.add(i);
        }
        List<Integer> savedPrgmIDs = new ArrayList<Integer>();
        for (int i : CSVTools.getIDList(CSVTools.typeP, CSVTools.fromCreated)){
            savedPrgmIDs.add(i);
        }

        // Validate Course ID input (check for integer input and duplicate Course ID's)
        try {
            courseIDinput = Integer.parseInt(itemIDField.getText());
        } catch (NumberFormatException e1){
            itemIDField.setText("*Invalid Course ID entered*");
            return false;
        }
        if (savedCourseIDs.contains(courseIDinput)){
            itemIDField.setText("*Duplicate ID found, Use another ID*");
            return false;
        }

        // Validate Parent Program ID input (check for integer input and existence of corresponding program)
        try {
            parentIDinput = Integer.parseInt(parentIDField.getText());
        } catch (NumberFormatException e1) {
            parentIDField.setText("*Invalid Program ID entered*");
            return false;
        }
        if ( !(savedPrgmIDs.contains(parentIDinput)) ){
            parentIDField.setText("*No Such Program*");
            return false;
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
            return false;
        }
        //only check for existence of corresponding courses if proper ID #'s are entered appropriately
        if (prereqInput != null) {
            for (int i=0; i<prereqInput.length; i++){
                if ( !(savedCourseIDs.contains(prereqInput[i])) ){
                    coursePrereqField.setText("*No such Courses*");
                    return false;
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
            return false;
        }
        // Only check for existence of corresponding courses if proper ID #'s are entered appropriately
        if (antireqInput != null){
            for (int i=0; i<antireqInput.length; i++){
                if ( !(savedCourseIDs.contains(antireqInput[i])) ){
                    courseAntireqField.setText("*No such Courses*");
                    return false;
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
            return false;
        }

        // Validate Course credits input (check for double float #)
        try {
            creditsInput = Double.parseDouble(courseCreditsField.getText());
        } catch (NumberFormatException e1) {
            courseCreditsField.setText("*Invalid*");
            return false;
        }

        /**
         * Valid Course inputs, create and add new Course
         */
        Course createdCourse = new Course(itemNameField.getText(),
                courseIDinput,
                itemDescriptionField.getText(),
                parentIDinput,
                hrsInput,
                creditsInput,
                courseLabInfoField.getText(),
                prereqInput,
                antireqInput
        );

        CSVTools.addChildIDtoParent(createdCourse);
        CSVTools.addCourse(createdCourse,CSVTools.toCreated);
        return true;

    }
    private boolean createItem(String type){
        /**
         * Validate User Input
         */
        int itemIDinput;
        int parentIDinput;
        String parentType;
        if (type.equals(CSVTools.typeD)){
            parentType = CSVTools.typeF;
        } else if (type.equals(CSVTools.typeP)){
            parentType = CSVTools.typeD;
        } else {
            // Type must be Faculty which has no parent type
            parentType = null;
        }

        // Validate Item ID input (check for integer input and duplicate item ID's)
        try {
            itemIDinput = Integer.parseInt(itemIDField.getText());
        } catch (NumberFormatException e1){
            itemIDField.setText("*Invalid "+type.substring(0,1).toUpperCase()+type.substring(1)+" ID entered*");
            return false;
        }
        List<Integer> savedItemIDs = new ArrayList<Integer>();
        for (int i : CSVTools.getIDList(type, CSVTools.fromCreated)){
            savedItemIDs.add(i);
        }
        if (savedItemIDs.contains(itemIDinput)){
            itemIDField.setText("*Duplicate ID found, Use another ID*");
            return false;
        }

        // Validate Parent ID input (check for integer input and existence of corresponding parent)
        // Unless type of item to be created is Faculty in which case we dont need to check for valid
        // parent ID's as Faculty items will not have parent items
        if (!(type.equals(CSVTools.typeF))){
            // Item to be created is of type Department or Program

            // Try to parse user input for parent ID as integer, number format exception means that user
            // entered invalid parent ID input
            try {
                parentIDinput = Integer.parseInt(parentIDField.getText());
            } catch (NumberFormatException e1){
                parentIDField.setText("*Invalid "+parentType.substring(0,1).toUpperCase()+parentType.substring(1)+" ID entered*");
                return false;
            }
            // Check for existence of parent item with corresponding ID entered by user
            List<Integer> savedParentIDs = new ArrayList<Integer>();
            for (int i : CSVTools.getIDList(parentType, CSVTools.fromCreated)){
                savedParentIDs.add(i);
            }
            if ( !(savedParentIDs.contains(parentIDinput)) ){
                parentIDField.setText("*No Such "+parentType.substring(0,1).toUpperCase()+parentType.substring(1)+"*");
                return false;
            }
        } else {
            //Assign that weird faculty parent ID thing (should be something else like 99999 but watevs)
            parentIDinput = 65535;
        }

        /**
         * Valid Item and Parent ID entered, create and add new Item
         */
        Item createdItem = new Item(type, itemNameField.getText(),
                itemIDinput,
                itemDescriptionField.getText(),
                null,
                parentIDinput
        );
        CSVTools.addChildIDtoParent(createdItem);
        CSVTools.addItem(createdItem, CSVTools.toCreated);
        return true;
    }
}
