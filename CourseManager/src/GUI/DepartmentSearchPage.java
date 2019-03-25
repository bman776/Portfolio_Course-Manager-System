package GUI;

import javax.swing.*;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;


public class DepartmentSearchPage extends JFrame {
    private JLabel departmentLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField departmentNameField;
    private JTextField departmentNumberField;
    private JComboBox departmentList;  //becomes scrollable after 8 elements
    private JPanel departmentSearchPanel;
    private String[] savedDepartments;
    private JButton searchButton;
    private JPanel outerPanel;

    /**
     * Constructor
     */
    public DepartmentSearchPage(){

        String[] savedDeptNames = CSVTools.getNameList("department");
        int[] savedDeptIDs = CSVTools.getIDList("department");
        savedDepartments = new String[savedDeptNames.length];
        for (int i = 0; i<savedDeptNames.length; i++){
            savedDepartments[i] = "ID:"+savedDeptIDs[i]+" Name: "+savedDeptNames[i];
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

        departmentSearchPanel = new JPanel();
        departmentSearchPanel.setBackground(SystemColor.window);
        departmentSearchPanel.setBounds(10,10, 465, 440);
        outerPanel.add(departmentSearchPanel);
        departmentSearchPanel.setLayout(null);

        departmentLabel = new JLabel("Courses:");
        departmentLabel.setBounds(170, 5, 100, 30);
        departmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        departmentSearchPanel.add(departmentLabel);

        departmentList = new JComboBox(savedDepartments);
        departmentList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        departmentList.setBounds(100,45,300, 30);
        departmentSearchPanel.add(departmentList);

        searchLabel = new JLabel("Search for a Course:");
        searchLabel.setBounds(40, 250, 150, 15);
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        departmentSearchPanel.add(searchLabel);

        nameLabel = new JLabel("Course Name:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        nameLabel.setBounds(40,288,120,15 );
        departmentSearchPanel.add(nameLabel);

        departmentNameField = new JTextField();
        departmentNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        departmentNameField.setBounds(165, 285, 250, 25);
        departmentSearchPanel.add(departmentNameField);

        numberLabel = new JLabel("Course ID Number:");
        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        numberLabel.setBounds( 40,328,120, 15);
        departmentSearchPanel.add(numberLabel);

        departmentNumberField = new JTextField();
        departmentNumberField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        departmentNumberField.setBounds( 165,325, 250, 25);
        departmentSearchPanel.add(departmentNumberField);

        searchButton = new JButton("Search for Course");
        searchButton.setBackground(new Color(176, 196, 222));
        searchButton.setBounds(100, 370, 175, 30);
        searchButton.setFont(new Font("Sitka Small", Font.PLAIN, 14));
        departmentSearchPanel.add(searchButton);

    }


}
