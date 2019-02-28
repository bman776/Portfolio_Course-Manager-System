package GUI;

import javax.swing.*;


public class ProgramSearchPage extends JFrame {
    private JLabel programLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField programNameField;
    private JTextField programNumberField;
    private JComboBox programList;  //becomes scrollable after 8 elements
    private JPanel programPanel;
    private String[] dummyPrograms = {"Program1","Program2","Program3","Program4","Program5","Program6","Program7",
            "Program8", "Program9"};
    private JButton searchButton;

    //constructor
    public ProgramSearchPage(){
        setTitle("Program Page");
        setBounds(100,100, 500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        programPanel = new JPanel();
        programPanel.setBounds(0,0, 500, 500);
        getContentPane().add(programPanel);
        programPanel.setLayout(null);

        programLabel = new JLabel("Programs:");
        programLabel.setBounds(200,0,100,50);
        programPanel.add(programLabel);

        programList = new JComboBox(dummyPrograms);
        programList.setBounds(100,45,300, 20);
        programPanel.add(programList);

        searchLabel = new JLabel("Search for a Program");
        searchLabel.setBounds(175,245,150, 15); //y was 265
        programPanel.add(searchLabel);

        nameLabel = new JLabel("name of Program");
        nameLabel.setBounds(75,285,100,15 );
        programPanel.add(nameLabel);

        programNameField = new JTextField();
        programNameField.setBounds(200,285,250, 20);
        programPanel.add(programNameField);

        numberLabel = new JLabel("ID number of Program");
        numberLabel.setBounds( 75,310,100, 15);
        programPanel.add(numberLabel);

        programNumberField = new JTextField();
        programNumberField.setBounds( 200,310, 250, 20);
        programPanel.add(programNumberField);

        searchButton = new JButton("Search for Program");
        searchButton.setBounds(200,335, 100, 20);
        programPanel.add(searchButton);

    }


}
