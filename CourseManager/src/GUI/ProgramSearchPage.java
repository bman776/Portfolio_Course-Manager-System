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
    private JPanel programPanel;
    private String[] savedPrograms;
    private JButton searchButton;
    private JPanel panel;

    //constructor
    public ProgramSearchPage(){

        String[] savedPrgmNames = CSVTools.getNameList("programs");
        int[] savedPrgmIDs = CSVTools.getIDList("programs");
        savedPrograms = new String[savedPrgmNames.length];
        for (int i = 0; i<savedPrgmNames.length; i++){
            savedPrograms[i] = "ID:"+savedPrgmIDs[i]+" Name: "+savedPrgmNames[i];
        }

    	setResizable(false);
        setTitle("Program Page");
        setBounds(100,100, 500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        programPanel = new JPanel();
        programPanel.setBackground(new Color(176, 196, 222));
        programPanel.setBounds(0,0, 494, 471);
        getContentPane().add(programPanel);
        programPanel.setLayout(null);

        programLabel = new JLabel("Programs:");
        programLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        programLabel.setBounds(200,0,100,50);
        programPanel.add(programLabel);

        programList = new JComboBox(savedPrograms);
        programList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        programList.setBounds(100,45,300, 30);
        programPanel.add(programList);

        searchLabel = new JLabel("Search for a Program");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchLabel.setBounds(110,260,150, 15); //y was 265
        programPanel.add(searchLabel);

        programNameField = new JTextField();
        programNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        programNameField.setBounds(165,285,250, 25);
        programPanel.add(programNameField);

        programNumberField = new JTextField();
        programNumberField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        programNumberField.setBounds( 165,325, 250, 25);
        programPanel.add(programNumberField);

        searchButton = new JButton("Search for Program");
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchButton.setBackground(new Color(176, 196, 222));
        searchButton.setBounds(165,360, 175, 30);
        programPanel.add(searchButton);
        
        panel = new JPanel();
        panel.setBackground(SystemColor.window);
        panel.setBounds(10, 11, 474, 449);
        programPanel.add(panel);
        panel.setLayout(null);
        
                nameLabel = new JLabel("Program Name:");
                nameLabel.setBounds(57, 281, 108, 15);
                panel.add(nameLabel);
                nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
                
                        numberLabel = new JLabel("Program ID Number:");
                        numberLabel.setBounds(30, 319, 131, 15);
                        panel.add(numberLabel);
                        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

    }


}
