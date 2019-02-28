package GUI;

import javax.swing.*;


public class CourseSearchPage extends JFrame {
    private JLabel courseLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField courseNameField;
    private JTextField courseNumberField;
    private JComboBox courseList;  //becomes scrollable after 8 elements
    private JPanel coursePanel;
    private String[] dummyCourses = {"Course1","Course2","Course3","Course4","Course5","Course6","Course7","Course8",
            "Course9",};
    private JButton searchButton;

    //constructor
    public CourseSearchPage(){
        setTitle("Faculty Page");
        setBounds(100,100, 500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        coursePanel = new JPanel();
        coursePanel.setBounds(0,0, 500, 500);
        getContentPane().add(coursePanel);
        coursePanel.setLayout(null);

        courseLabel = new JLabel("Courses:");
        courseLabel.setBounds(200,0,100,50);
        coursePanel.add(courseLabel);

        courseList = new JComboBox(dummyCourses);
        courseList.setBounds(100,45,300, 20);
        coursePanel.add(courseList);

        searchLabel = new JLabel("Search for a Course");
        searchLabel.setBounds(175,245,150, 15); //y was 265
        coursePanel.add(searchLabel);

        nameLabel = new JLabel("name of Course");
        nameLabel.setBounds(75,285,100,15 );
        coursePanel.add(nameLabel);

        courseNameField = new JTextField();
        courseNameField.setBounds(200,285,250, 20);
        coursePanel.add(courseNameField);

        numberLabel = new JLabel("ID number of Course");
        numberLabel.setBounds( 75,310,100, 15);
        coursePanel.add(numberLabel);

        courseNumberField = new JTextField();
        courseNumberField.setBounds( 200,310, 250, 20);
        coursePanel.add(courseNumberField);

        searchButton = new JButton("Search for Course");
        searchButton.setBounds(200,335, 100, 20);
        coursePanel.add(searchButton);

    }


}
