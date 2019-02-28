package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class FacultySearchPage extends JFrame {
    private JLabel facultyLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField facultyNameField;
    private JTextField facultyNumberField;
    private JComboBox facultyList;  //becomes scrollable after 8 elements
    private JPanel facultyPanel;
    private String[] dummyFaculties = {"Faculty1","Faculty2","Faculty3","Faculty4","Faculty5","Faculty6","Faculty7",
            "Faculty8","Faculty9"};
    private JButton searchButton;

    //constructor
    public FacultySearchPage(){
        setTitle("Faculty Page");
        setBounds(100,100, 500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        facultyPanel = new JPanel();
        facultyPanel.setBounds(0,0, 500, 500);
        getContentPane().add(facultyPanel);
        facultyPanel.setLayout(null);

        facultyLabel = new JLabel("Faculties:");
        facultyLabel.setBounds(200,0,100,50);
        facultyPanel.add(facultyLabel);

        facultyList = new JComboBox(dummyFaculties);
        facultyList.setBounds(100,45,300, 20);
        facultyPanel.add(facultyList);

        searchLabel = new JLabel("Search for a Faculty");
        searchLabel.setBounds(175,245,150, 15); //y was 265
        facultyPanel.add(searchLabel);

        nameLabel = new JLabel("name of Faculty");
        nameLabel.setBounds(75,285,100,15 );
        facultyPanel.add(nameLabel);

        facultyNameField = new JTextField();
        facultyNameField.setBounds(200,285,250, 20);
        facultyPanel.add(facultyNameField);

        numberLabel = new JLabel("ID number of Faculty");
        numberLabel.setBounds( 75,310,100, 15);
        facultyPanel.add(numberLabel);

        facultyNumberField = new JTextField();
        facultyNumberField.setBounds( 200,310, 250, 20);
        facultyPanel.add(facultyNumberField);

        searchButton = new JButton("Search for Faculty");
        searchButton.setBounds(200,335, 100, 20);
        facultyPanel.add(searchButton);

    }


}
