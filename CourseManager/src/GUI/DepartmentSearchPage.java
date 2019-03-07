package GUI;

import javax.swing.*;
import java.awt.Font;
import java.awt.SystemColor;


public class DepartmentSearchPage extends JFrame {
    private JLabel departmentLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField departmentNameField;
    private JTextField departmentNumberField;
    private JComboBox departmentList;  //becomes scrollable after 8 elements
    private JPanel departmentPanel;
    private String[] dummyDepartments = (String[]) CSVTools.findList("department");
    private JButton searchButton;
    private JPanel panel;

    //constructor
    public DepartmentSearchPage(){
    	setResizable(false);
        setTitle("Department Page");
        setBounds(100,100, 500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        departmentPanel = new JPanel();
        departmentPanel.setBackground(SystemColor.activeCaption);
        departmentPanel.setBounds(0,0, 494, 471);
        getContentPane().add(departmentPanel);
        departmentPanel.setLayout(null);

        departmentList = new JComboBox(dummyDepartments);
        departmentList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        departmentList.setBounds(100,45,300, 30);
        departmentPanel.add(departmentList);

        searchLabel = new JLabel("Search for a Department");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchLabel.setBounds(110,260,150, 15); //y was 265
        departmentPanel.add(searchLabel);

        departmentNameField = new JTextField();
        departmentNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        departmentNameField.setBounds(165,286,250, 25);
        departmentPanel.add(departmentNameField);

        departmentNumberField = new JTextField();
        departmentNumberField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        departmentNumberField.setBounds( 165,325, 250, 25);
        departmentPanel.add(departmentNumberField);

        searchButton = new JButton("Search for Department");
        searchButton.setBackground(SystemColor.activeCaption);
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchButton.setBounds(165,360, 175, 30);
        departmentPanel.add(searchButton);
        
        panel = new JPanel();
        panel.setBackground(SystemColor.window);
        panel.setBounds(10, 11, 474, 449);
        departmentPanel.add(panel);
        panel.setLayout(null);
        
                departmentLabel = new JLabel("Departments:");
                departmentLabel.setBounds(165, 0, 100, 30);
                panel.add(departmentLabel);
                departmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
                
                        numberLabel = new JLabel("Department ID Number:");
                        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
                        numberLabel.setBounds(10, 316, 154, 15);
                        panel.add(numberLabel);
                        
                                nameLabel = new JLabel("Department Name:");
                                nameLabel.setBounds(37, 279, 135, 15);
                                panel.add(nameLabel);
                                nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

    }


}
