package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class CourseSearchPage extends JFrame {
    private JPanel courseSearchPanel;
    private JPanel outerPanel;
    private JLabel courseLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField courseNameField;
    private JTextField courseNumberField;
    private JList<String> courseList;
    private JScrollPane coursePane;
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
            savedCourses[i] = savedCourseNames[i]+"   "+savedCourseIDs[i];
        }

    	setResizable(false);
        setTitle("Course Search Page");
        setBounds(800,400, 500, 500);
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

        courseList = new JList(savedCourses);
        courseList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        courseList.setBounds(100,45,300, 185);
        coursePane = new JScrollPane(courseList);
        coursePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        coursePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        coursePane.setBounds(courseList.getBounds());
        courseSearchPanel.add(coursePane);
        courseList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /**
                 * IF statement prevents the infamous double fire event associated with JLists selections
                 * (when made with mouse) and makes the selection in the JList re-selectable to the user
                 */
                if (courseList.getSelectedValue() != null){
                    int courseID = Integer.parseInt(courseList.getSelectedValue().split("   ")[1]);
                    CourseInfoPage selectedCourse = new CourseInfoPage(CSVTools.findCourse(courseID));
                    courseList.clearSelection();
                    selectedCourse.setVisible(true);
                }
                return;
            }
        });

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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameInput;
                int IDInput;
                List<String> searchResults = new ArrayList<>();

                if (!courseNameField.getText().equals("") && courseNumberField.getText().equals("")){

                    /**
                     * Only name given for search
                     */

                    // Retrieve name
                    nameInput = courseNameField.getText();

                    //Search based on name
                    for (int i=0; i<savedCourseNames.length; i++){
                        if (savedCourseNames[i].equals(nameInput)){
                            searchResults.add(nameInput+"   "+CSVTools.findItem("course",nameInput).getID() );
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        courseList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        courseNameField.setText("No such Course with that name");
                    }

                } else if (courseNameField.getText().equals("") && !courseNumberField.getText().equals("")){

                    /**
                     * only id given for search
                     */

                    // Validate ID input
                    try {
                        IDInput = Integer.parseInt(courseNumberField.getText());
                    } catch (NumberFormatException e1){
                        courseNumberField.setText("*Invalid ID Input*");
                        return;
                    }

                    // Search based on ID
                    for (int i=0; i<savedCourseIDs.length; i++){
                        if (savedCourseIDs[i] == IDInput){
                            searchResults.add(CSVTools.findItem("course", IDInput).getName()+ "   "+IDInput);
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        courseList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        courseNumberField.setText("No such Program with that ID");
                    }

                } else if (  !courseNameField.getText().equals("") && !courseNumberField.getText().equals("") ){

                    /**
                     * Name and ID given for search
                     */

                    // Retrieve name
                    nameInput = courseNameField.getText();

                    // Validate ID input
                    try {
                        IDInput = Integer.parseInt(courseNumberField.getText());
                    } catch (NumberFormatException e1){
                        courseNumberField.setText("*Invalid ID Input*");
                        return;
                    }

                    //Search Based on ID and Name
                    for (int i=0; i<savedCourseIDs.length; i++){
                        if ( (savedCourseIDs[i] == IDInput) && (savedCourseNames[i] == nameInput) ){
                            searchResults.add(nameInput+"   "+IDInput);
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        courseList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        courseNameField.setText("No such Course with that name");
                        courseNumberField.setText("No such Course with that ID");
                    }

                } else {
                    // Neither Name or ID given for Search
                    courseNameField.setText("Provide Course name");
                    courseNumberField.setText("Provide Csourse number");
                }

            }
        });

    }
}
