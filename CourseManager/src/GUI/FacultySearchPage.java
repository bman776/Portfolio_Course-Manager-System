package GUI;

import java.awt.*;

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
    private String[] savedFaculties;
    private JButton searchButton;
    private JPanel panel;
    

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

        facultyPanel = new JPanel();
        facultyPanel.setBackground(new Color(176, 196, 222));
        facultyPanel.setBounds(0,0, 494, 471);
        getContentPane().add(facultyPanel);
        facultyPanel.setLayout(null);

        facultyList = new JComboBox(savedFaculties);
        facultyList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        facultyList.setBounds(100,45,300, 30);
        facultyPanel.add(facultyList);

        searchLabel = new JLabel("Search for a Faculty");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchLabel.setBounds(110,260,150, 15); //y was 265
        facultyPanel.add(searchLabel);

        facultyNameField = new JTextField();
        facultyNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        facultyNameField.setBounds(165,285,250, 25);
        facultyPanel.add(facultyNameField);

        facultyNumberField = new JTextField();
        facultyNumberField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        facultyNumberField.setBounds( 165,325, 250, 25);
        facultyPanel.add(facultyNumberField);

        searchButton = new JButton("Search for Faculty");
        searchButton.setBackground(new Color(176, 196, 222));
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchButton.setBounds(165,360, 175, 30);
        facultyPanel.add(searchButton);
        
        panel = new JPanel();
        panel.setBackground(SystemColor.window);
        panel.setBounds(10, 11, 474, 449);
        facultyPanel.add(panel);
        panel.setLayout(null);
        
                facultyLabel = new JLabel("Faculties:");
                facultyLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
                facultyLabel.setBounds(166, 0, 100, 30);
                panel.add(facultyLabel);
                
                        nameLabel = new JLabel("Faculty Name:");
                        nameLabel.setBounds(62, 279, 108, 15);
                        panel.add(nameLabel);
                        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
                        
                                numberLabel = new JLabel("Faculty ID Number:");
                                numberLabel.setBounds(40, 318, 131, 15);
                                panel.add(numberLabel);
                                numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

    }


}
