package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ProgramSearchPage extends JFrame {
    private JLabel programLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField programNameField;
    private JTextField programNumberField;
    private JList<String> programList;
    private JScrollPane programPane;
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
            savedPrograms[i] = savedPrgmNames[i]+"   "+savedPrgmIDs[i];
        }

        setResizable(false);
        setTitle("Program Search Page");
        setBounds(800,300, 500, 500);
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

        programList = new JList(savedPrograms);
        programList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        programList.setBounds(100,45,300, 185);
        programPane = new JScrollPane(programList);
        programPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        programPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        programPane.setBounds(programList.getBounds());
        programSearchPanel.add(programPane);
        programList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /**
                 * IF statement prevents the infamous double fire event associated with JLists selections
                 * (when made with mouse) and makes the selection in the JList re-selectable to the user
                 */
                if (programList.getSelectedValue() != null){
                    int prgmID = Integer.parseInt(programList.getSelectedValue().split("   ")[1]);
                    ProgramInfoPage selectedPrgm = new ProgramInfoPage(CSVTools.findItem("program",prgmID));
                    programList.clearSelection();
                    selectedPrgm.setVisible(true);
                }
                return;
            }
        });

        searchLabel = new JLabel("Search for a Program");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchLabel.setBounds(40,250,150, 15);
        programSearchPanel.add(searchLabel);

        nameLabel = new JLabel("Program Name:");
        nameLabel.setBounds(40, 288, 120, 15);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        programSearchPanel.add(nameLabel);

        programNameField = new JTextField();
        programNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        programNameField.setBounds(165,285,250, 25);
        programSearchPanel.add(programNameField);

        numberLabel = new JLabel("Program ID Number:");
        numberLabel.setBounds(40, 328, 120, 15);
        numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        programSearchPanel.add(numberLabel);

        programNumberField = new JTextField();
        programNumberField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        programNumberField.setBounds( 165,325, 250, 25);
        programSearchPanel.add(programNumberField);

        searchButton = new JButton("Search for Program");
        searchButton.setBackground(new Color(176, 196, 222));
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchButton.setBounds(100,370, 175, 30);
        programSearchPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameInput;
                int IDInput;
                List<String> searchResults = new ArrayList<>();

                if (!programNameField.getText().equals("") && programNumberField.getText().equals("")){

                    /**
                     * Only name given for search
                     */

                    // Retrieve name
                    nameInput = programNameField.getText();

                    //Search based on name
                    for (int i=0; i<savedPrgmNames.length; i++){
                        if (savedPrgmNames[i].equals(nameInput)){
                            searchResults.add(nameInput+"   "+CSVTools.findItem("program",nameInput).getID() );
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        programList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        programNameField.setText("No such Program with that name");
                    }

                } else if (programNameField.getText().equals("") && !programNumberField.getText().equals("")){

                    /**
                     * only id given for search
                     */

                    // Validate ID input
                    try {
                        IDInput = Integer.parseInt(programNumberField.getText());
                    } catch (NumberFormatException e1){
                        programNumberField.setText("*Invalid ID Input*");
                        return;
                    }

                    // Search based on ID
                    for (int i=0; i<savedPrgmIDs.length; i++){
                        if (savedPrgmIDs[i] == IDInput){
                            searchResults.add(CSVTools.findItem("program", IDInput).getName()+ "   "+IDInput);
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        programList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        programNumberField.setText("No such Program with that ID");
                    }

                } else if (  !programNameField.getText().equals("") && !programNumberField.getText().equals("") ){

                    /**
                     * Name and ID given for search
                     */

                    // Retrieve name
                    nameInput = programNameField.getText();

                    // Validate ID input
                    try {
                        IDInput = Integer.parseInt(programNumberField.getText());
                    } catch (NumberFormatException e1){
                        programNumberField.setText("*Invalid ID Input*");
                        return;
                    }

                    //Search Based on ID and Name
                    for (int i=0; i<savedPrgmIDs.length; i++){
                        if ( (savedPrgmIDs[i] == IDInput) && (savedPrgmNames[i].equals(nameInput)) ){
                            searchResults.add(nameInput+"   "+IDInput);
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        programList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        programNameField.setText("No such Program with that name");
                        programNumberField.setText("No such Program with that ID");
                    }

                } else {
                    // Neither Name or ID given for Search
                    programNameField.setText("Provide Program name");
                    programNumberField.setText("Provide Program number");
                }
            }
        });
    }
}
