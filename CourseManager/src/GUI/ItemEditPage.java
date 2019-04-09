package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ItemEditPage extends JFrame {

    /**
     *  Components used if Item is of type Faculty, Department, Program or Course
     */
    private JPanel outerPanel;
    private JPanel itemEditPanel;
    private JLabel editLabel;
    private JLabel itemNameLabel;
    private JTextField itemNameField;
    private JLabel itemIDLabel;
    private JTextField itemIDField;
    private JLabel itemDescriptionLabel;
    private JTextArea itemDescriptionField;
    private JScrollPane itemDescriptionPane;
    private JButton btnEditItem;

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
    private JButton btnEditCourse;

    public ItemEditPage(Item oldItem){

        if (oldItem.getType().equals(CSVTools.typeC)) {
            /**
             *  Item Add page is arranged to receive Course information
             */
            Course oldCourse = (Course)oldItem;
            setResizable(false);
            setTitle("Course Edit Page");
            setBounds(600,200, 900, 530);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);

            outerPanel = new JPanel();
            outerPanel.setBackground(new Color(176, 196, 222));
            outerPanel.setBounds(0,0, 900, 530);
            getContentPane().add(outerPanel);
            outerPanel.setLayout(null);

            itemEditPanel = new JPanel();
            itemEditPanel.setBackground(SystemColor.window);
            itemEditPanel.setBounds(10, 10, 865, 470);
            outerPanel.add(itemEditPanel);
            itemEditPanel.setLayout(null);

            editLabel = new JLabel("Edit Course");
            editLabel.setBounds(170, 5 , 110, 30);
            editLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemEditPanel.add(editLabel);

            itemNameLabel = new JLabel("Course Name:");
            itemNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemNameLabel.setBounds(60,50,110,30 );
            itemEditPanel.add(itemNameLabel);

            itemNameField = new JTextField(oldCourse.getName());
            itemNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
            itemNameField.setBounds(165, 50, 250, 30);
            itemEditPanel.add(itemNameField);

            itemIDLabel = new JLabel("Course ID:");
            itemIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemIDLabel.setBounds( 60,100,80, 30);
            itemEditPanel.add(itemIDLabel);

            itemIDField = new JTextField(Integer.toString(oldCourse.getID()));
            itemIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
            itemIDField.setBounds( 165,100, 250, 30);
            itemEditPanel.add(itemIDField);

            itemDescriptionLabel = new JLabel("Course Description:");
            itemDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemDescriptionLabel.setBounds(60,200, 200, 30 );
            itemEditPanel.add(itemDescriptionLabel);

            itemDescriptionField = new JTextArea(oldCourse.getDescription());
            itemDescriptionField.setBounds(35,250, 380, 200);
            itemDescriptionPane = new JScrollPane(itemDescriptionField);
            itemDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            itemDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            itemDescriptionPane.setBounds(itemDescriptionField.getBounds());
            itemEditPanel.add(itemDescriptionPane);

            coursePrereqLabel = new JLabel("Course Prerequisites:");
            coursePrereqLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            coursePrereqLabel.setBounds(450, 50, 130, 15);
            itemEditPanel.add(coursePrereqLabel);

            String oldCoursePrereqs = "";
            for (int i=0; i<oldCourse.getPrerequisites().length; i++){
                oldCoursePrereqs = oldCoursePrereqs+(Integer.toString(oldCourse.getPrerequisites()[i])+" ");
            }
            coursePrereqField = new JTextField(oldCoursePrereqs);
            coursePrereqField.setFont(new Font("Tahoma", Font.PLAIN, 13));
            coursePrereqField.setBounds(450, 75 , 380, 50);
            coursePrereqPane = new JScrollPane(coursePrereqField);
            coursePrereqPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            coursePrereqPane.setBounds(coursePrereqField.getBounds());
            itemEditPanel.add(coursePrereqPane);

            courseAnitreqLabel = new JLabel("Course Antirequisites:");
            courseAnitreqLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseAnitreqLabel.setBounds(450, 135, 130, 15);
            itemEditPanel.add(courseAnitreqLabel);

            String oldCourseAntireqs = "";
            for (int i=0; i<oldCourse.getAntirequisites().length; i++){
                oldCourseAntireqs = oldCourseAntireqs+(Integer.toString(oldCourse.getAntirequisites()[i])+" ");
            }
            courseAntireqField = new JTextField(oldCourseAntireqs);
            courseAntireqField.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseAntireqField.setBounds(450,160, 380, 50);
            courseAntireqPane = new JScrollPane(courseAntireqField);
            courseAntireqPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            courseAntireqPane.setBounds(courseAntireqField.getBounds());
            itemEditPanel.add(courseAntireqPane);

            courseLabInfoLabel = new JLabel("Lab Info:");
            courseLabInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseLabInfoLabel.setBounds(475, 220, 200, 30);
            itemEditPanel.add(courseLabInfoLabel);

            courseLabInfoField = new JTextArea(oldCourse.getLabinfo());
            courseLabInfoField.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseLabInfoField.setBounds(455, 250, 360, 100 );
            courseLabInfoPane = new JScrollPane(courseLabInfoField);
            courseLabInfoPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            courseLabInfoPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            courseLabInfoPane.setBounds(courseLabInfoField.getBounds());
            itemEditPanel.add(courseLabInfoPane);

            courseHoursLabel = new JLabel("Course Hours:");
            courseHoursLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseHoursLabel.setBounds(450, 370, 90, 15);
            itemEditPanel.add(courseHoursLabel);

            courseHoursField = new JTextField(Double.toString(oldCourse.getHours()));
            courseHoursField.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseHoursField.setBounds(550, 365,170 , 30);
            itemEditPanel.add(courseHoursField);

            courseCreditsLabel = new JLabel("Course Credits:");
            courseCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseCreditsLabel.setBounds(450, 415, 90, 15);
            itemEditPanel.add(courseCreditsLabel);

            courseCreditsField = new JTextField(Double.toString(oldCourse.getCredits()));
            courseCreditsField.setFont(new Font("Tahoma", Font.PLAIN, 13));
            courseCreditsField.setBounds(550, 410, 100, 30);
            itemEditPanel.add(courseCreditsField);

            btnEditCourse = new JButton("Edit");
            btnEditCourse.setBounds(725, 400, 120, 30 );
            btnEditCourse.setFont(new Font("Sitka Small", Font.PLAIN, 14));
            itemEditPanel.add(btnEditCourse);
            btnEditCourse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!(editCourse(oldCourse))){
                        //Invalid input entered by user somewhere in JFrame components
                        return;
                    } else {
                        //Course was successfully edited
                        dispose();
                        JFrame editConfirmation = new JFrame("Edit confirmation");
                        editConfirmation.setBounds(300, 300, 100,40);
                        JLabel confirmationMssg = new JLabel("Course successfully edited");
                        confirmationMssg.setFont(new Font("Sitka Small", Font.PLAIN, 14));
                        editConfirmation.getContentPane().add(confirmationMssg);
                    }
                }
            });
        }
        else {
            /**
             * Item Add page is arranged to receive Faculty, Department or program information (all have same format)
             */
            setResizable(false);
            setTitle(oldItem.getType().substring(0, 1).toUpperCase() + oldItem.getType().substring(1) + " Edit Page");
            setBounds(1000, 200, 500, 580);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);

            outerPanel = new JPanel();
            outerPanel.setBackground(new Color(176, 196, 222));
            outerPanel.setBounds(0, 0, 500, 580);
            getContentPane().add(outerPanel);
            outerPanel.setLayout(null);

            itemEditPanel = new JPanel();
            itemEditPanel.setBackground(SystemColor.window);
            itemEditPanel.setBounds(10, 10, 465, 520);
            outerPanel.add(itemEditPanel);
            itemEditPanel.setLayout(null);

            editLabel = new JLabel("Edit " + oldItem.getType().substring(0, 1).toUpperCase() + oldItem.getType().substring(1));
            editLabel.setBounds(170, 5, 110, 30);
            editLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemEditPanel.add(editLabel);

            itemNameLabel = new JLabel(oldItem.getType().substring(0, 1).toUpperCase() + oldItem.getType().substring(1) + " Name:");
            itemNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemNameLabel.setBounds(60, 50, 110, 30);
            itemEditPanel.add(itemNameLabel);

            itemNameField = new JTextField(oldItem.getName());
            itemNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
            itemNameField.setBounds(175, 50, 240, 30);
            itemEditPanel.add(itemNameField);

            itemIDLabel = new JLabel(oldItem.getType().substring(0, 1).toUpperCase() + oldItem.getType().substring(1) + " ID:");
            itemIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemIDLabel.setBounds(60, 100, 90, 30);
            itemEditPanel.add(itemIDLabel);

            itemIDField = new JTextField(Integer.toString(oldItem.getID()));
            itemIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
            itemIDField.setBounds(165, 100, 250, 30);
            itemEditPanel.add(itemIDField);

            itemDescriptionLabel = new JLabel(oldItem.getType().substring(0, 1).toUpperCase() + oldItem.getType().substring(1) + " Description:");
            itemDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            itemDescriptionLabel.setBounds(60, 200, 200, 30);
            itemEditPanel.add(itemDescriptionLabel);

            itemDescriptionField = new JTextArea(oldItem.getDescription());
            itemDescriptionField.setBounds(35, 250, 400, 200);
            itemDescriptionPane = new JScrollPane(itemDescriptionField);
            itemDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            itemDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            itemDescriptionPane.setBounds(itemDescriptionField.getBounds());
            itemEditPanel.add(itemDescriptionPane);

            btnEditItem = new JButton("Edit");
            btnEditItem.setBounds(265, 470, 120, 30 );
            btnEditItem.setFont(new Font("Sitka Small", Font.PLAIN, 14));
            itemEditPanel.add(btnEditItem);
            btnEditItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!(editItem(oldItem))){
                        //Invalid input entered by user somewhere in JFrame components
                        return;
                    } else {

                        //Item was successfully edited
                        dispose();

                        // Display confirmation message
                        JFrame editConfirmation = new JFrame("Edit confirmation");
                        editConfirmation.setBounds(300, 300, 100,40);
                        JLabel confirmationMssg = new JLabel("Item successfully edited");
                        confirmationMssg.setFont(new Font("Sitka Small", Font.PLAIN, 14));
                        editConfirmation.getContentPane().add(confirmationMssg);
                    }


                }
            });


        }
    }

    private boolean editCourse(Course oldCourse){
        /**
         * Validate user input
         */
        int courseIDinput;
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
        if ((savedCourseIDs.contains(courseIDinput)) && (courseIDinput != oldCourse.getID())){
            itemIDField.setText("*Duplicate ID found, Use another ID*");
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
         * Valid Course inputs, Edit Course
         */
        Course newCourse = new Course(itemNameField.getText(),
                courseIDinput,
                itemDescriptionField.getText(),
                oldCourse.getParent(),
                hrsInput,
                creditsInput,
                courseLabInfoField.getText(),
                prereqInput,
                antireqInput
        );
        //changeItemIDinSystem MUST come before removeData
        CSVTools.changeItemIDinSystem(oldCourse.getID(), newCourse.getID(), CSVTools.typeC);
        CSVTools.addCourse(newCourse,CSVTools.toCreated);
        CSVTools.removeData(oldCourse);
        return true;
    }

    private boolean editItem(Item oldItem){

        /**
         * Validate User Input
         */
        int itemIDinput;

        // Validate Item ID input (check for integer input and duplicate item ID's)
        try {
            itemIDinput = Integer.parseInt(itemIDField.getText());
        } catch (NumberFormatException e1){
            itemIDField.setText("*Invalid "+oldItem.getType().substring(0,1).toUpperCase()+oldItem.getType().substring(1)+" ID entered*");
            return false;
        }
        java.util.List<Integer> savedItemIDs = new ArrayList<Integer>();
        for (int i : CSVTools.getIDList(oldItem.getType(), CSVTools.fromCreated)){
            savedItemIDs.add(i);
        }
        if ( (savedItemIDs.contains(itemIDinput)) && (itemIDinput != oldItem.getID()) ){
            itemIDField.setText("*Duplicate ID found, Use another ID*");
            return false;
        }


        /**
         * Valid Item ID entered, edit Item
         */
        Item newItem = new Item(oldItem.getType(), itemNameField.getText(),
                itemIDinput,
                itemDescriptionField.getText(),
                null,
                oldItem.getParent()
        );
        //changeItemIDinSystem MUST come before removeData
        CSVTools.changeItemIDinSystem(oldItem.getID(), newItem.getID(), oldItem.getType());
        CSVTools.removeData(oldItem);
        CSVTools.addItem(newItem, CSVTools.toCreated);
        return true;
    }

}
