package GUI;

import java.awt.*;

import javax.swing.*;


public class FacultySearchPage extends JFrame {
    private JPanel facultySearchPanel;
    private JPanel outerPanel;
    private JLabel facultyLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField facultyNameField;
    private JTextField facultyNumberField;
    private JComboBox facultyList;
    private String[] savedFaculties;
    private JButton searchButton;

    //constuctor
    public FacultySearchPage(){

        String[] savedFacNames = CSVTools.getNameList("faculty");
        int[] savedFacIDs = CSVTools.getIDList("faculty");
        savedFaculties = new String[savedFacNames.length];
        for (int i = 0; i<savedFacNames.length; i++){
            savedFaculties[i] = "ID:"+savedFacIDs[i]+" Name: "+savedFacNames[i];
        }

    	setResizable(false);
        setTitle("Faculty Page");
        setBounds(100,100, 500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        outerPanel = new JPanel();
        outerPanel.setBackground(new Color(176, 196, 222));
        outerPanel.setBounds(0, 0, 500, 500);
        getContentPane().add(outerPanel);
        outerPanel.setLayout(null);

        facultySearchPanel = new JPanel();
        facultySearchPanel.setBackground(SystemColor.window);
        facultySearchPanel.setBounds(10,10, 465, 440);
        outerPanel.add(facultySearchPanel);
        facultySearchPanel.setLayout(null);

        facultyLabel = new JLabel("Faculties:");
        facultyLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        facultyLabel.setBounds(170, 5, 100, 30);
        facultySearchPanel.add(facultyLabel);

        facultyList = new JComboBox(savedFaculties);
        facultyList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        facultyList.setBounds(100,45,300, 30);
        facultySearchPanel.add(facultyList);

        searchLabel = new JLabel("Search for a Faculty");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchLabel.setBounds(40,250,150, 15);
        facultySearchPanel.add(searchLabel);

        nameLabel = new JLabel("Faculty Name:");
        nameLabel.setBounds(40, 288, 120, 15);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        facultySearchPanel.add(nameLabel);

        facultyNameField = new JTextField();
        facultyNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        facultyNameField.setBounds(165,285,250, 25);
        facultySearchPanel.add(facultyNameField);

        numberLabel = new JLabel("Faculty ID Number:");
        numberLabel.setBounds(40, 328, 120, 15);
        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        facultySearchPanel.add(numberLabel);

        facultyNumberField = new JTextField();
        facultyNumberField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        facultyNumberField.setBounds( 165,325, 250, 25);
        facultySearchPanel.add(facultyNumberField);

        searchButton = new JButton("Search for Faculty");
        searchButton.setBackground(new Color(176, 196, 222));
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchButton.setBounds(100,370, 175, 30);
        facultySearchPanel.add(searchButton);

    }


}

