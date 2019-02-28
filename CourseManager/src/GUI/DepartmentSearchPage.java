package GUI;

import javax.swing.*;


public class DepartmentSearchPage extends JFrame {
    private JLabel departmentLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField departmentNameField;
    private JTextField departmentNumberField;
    private JComboBox departmentList;  //becomes scrollable after 8 elements
    private JPanel departmentPanel;
    private String[] dummyDepartments = {"Department1","Department2","Department3","Department4","Department5",
            "Department6","Department7","Department8","Department9"};
    private JButton searchButton;

    //constructor
    public DepartmentSearchPage(){
        setTitle("Department Page");
        setBounds(100,100, 500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        departmentPanel = new JPanel();
        departmentPanel.setBounds(0,0, 500, 500);
        getContentPane().add(departmentPanel);
        departmentPanel.setLayout(null);

        departmentLabel = new JLabel("Departments:");
        departmentLabel.setBounds(200,0,100,50);
        departmentPanel.add(departmentLabel);

        departmentList = new JComboBox(dummyDepartments);
        departmentList.setBounds(100,45,300, 20);
        departmentPanel.add(departmentList);

        searchLabel = new JLabel("Search for a Department");
        searchLabel.setBounds(175,245,150, 15); //y was 265
        departmentPanel.add(searchLabel);

        nameLabel = new JLabel("name of Department");
        nameLabel.setBounds(75,285,100,15 );
        departmentPanel.add(nameLabel);

        departmentNameField = new JTextField();
        departmentNameField.setBounds(200,285,250, 20);
        departmentPanel.add(departmentNameField);

        numberLabel = new JLabel("ID number of Department");
        numberLabel.setBounds( 75,310,100, 15);
        departmentPanel.add(numberLabel);

        departmentNumberField = new JTextField();
        departmentNumberField.setBounds( 200,310, 250, 20);
        departmentPanel.add(departmentNumberField);

        searchButton = new JButton("Search for Department");
        searchButton.setBounds(200,335, 100, 20);
        departmentPanel.add(searchButton);

    }


}
