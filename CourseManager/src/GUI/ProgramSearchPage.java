package GUI;

import javax.swing.*;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;


public class ProgramSearchPage extends JFrame {
    private JLabel programLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField programNameField;
    private JTextField programNumberField;
    private JComboBox programList;  //becomes scrollable after 8 elements
    private JPanel programSearchPanel;
    private String[] savedPrograms;
    private JButton searchButton;
    private JPanel outerPanel;

    //constructor
    public ProgramSearchPage(){

        String[] savedPrgmNames = CSVTools.getNameList("program");
        int[] savedPrgmIDs = CSVTools.getIDList("program");
        savedPrograms = new String[savedPrgmNames.length];
        for (int i = 0; i<savedPrgmNames.length; i++){
            savedPrograms[i] = "ID:"+savedPrgmIDs[i]+" Name: "+savedPrgmNames[i];
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

        programSearchPanel = new JPanel();
        programSearchPanel.setBackground(SystemColor.window);
        programSearchPanel.setBounds(10,10, 465, 440);
        outerPanel.add(programSearchPanel);
        programSearchPanel.setLayout(null);

        programLabel = new JLabel("Programs:");
        programLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        programLabel.setBounds(170, 5, 100, 30);
        programSearchPanel.add(programLabel);

        programList = new JComboBox(savedPrograms);
        programList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        programList.setBounds(100,45,300, 30);
        programSearchPanel.add(programList);

        searchLabel = new JLabel("Search for a Faculty");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchLabel.setBounds(40,250,150, 15);
        programSearchPanel.add(searchLabel);

        nameLabel = new JLabel("Faculty Name:");
        nameLabel.setBounds(40, 288, 120, 15);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        programSearchPanel.add(nameLabel);

        programNameField = new JTextField();
        programNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        programNameField.setBounds(165,285,250, 25);
        programSearchPanel.add(programNameField);

        numberLabel = new JLabel("Faculty ID Number:");
        numberLabel.setBounds(40, 328, 120, 15);
        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        programSearchPanel.add(numberLabel);

        programNumberField = new JTextField();
        programNumberField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        programNumberField.setBounds( 165,325, 250, 25);
        programSearchPanel.add(programNumberField);

        searchButton = new JButton("Search for Faculty");
        searchButton.setBackground(new Color(176, 196, 222));
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchButton.setBounds(100,370, 175, 30);
        programSearchPanel.add(searchButton);
    }


}
