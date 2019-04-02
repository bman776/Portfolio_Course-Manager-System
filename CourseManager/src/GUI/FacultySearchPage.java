package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class FacultySearchPage extends JFrame {
    private JPanel facultySearchPanel;
    private JPanel outerPanel;
    private JLabel facultyLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField facultyNameField;
    private JTextField facultyNumberField;
    private JList<String> facultyList;
    private JScrollPane facultyPane;
    private String[] savedFaculties;
    private JButton searchButton;

    public FacultySearchPage(){

        // Get saved Faculties (Faculty Names and IDs)
        String[] savedFacNames = CSVTools.getNameList("faculty");
        int[] savedFacIDs = CSVTools.getIDList("faculty");
        savedFaculties = new String[savedFacNames.length];
        for (int i = 0; i<savedFacNames.length; i++){
            savedFaculties[i] = savedFacNames[i]+"   "+savedFacIDs[i];
        }

    	setResizable(false);
        setTitle("Faculty Search Page");
        setBounds(800,100, 500, 500);
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

        facultyList = new JList(savedFaculties);
        facultyList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        facultyList.setBounds(100,45,300, 185);
        facultyPane = new JScrollPane(facultyList);
        facultyPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        facultyPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        facultyPane.setBounds(facultyList.getBounds());
        facultySearchPanel.add(facultyPane);
        facultyList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /**
                 * IF statement prevents the infamous double fire event associated with JLists selections
                 * (when made with mouse) and makes the selection in the JList re-selectable to the user
                 */
                if (facultyList.getSelectedValue() != null){
                    int facultyID = Integer.parseInt(facultyList.getSelectedValue().split("   ")[1]);
                    FacultyInfoPage selectedFaculty = new FacultyInfoPage(CSVTools.findItem("faculty", facultyID));
                    facultyList.clearSelection();
                    selectedFaculty.setVisible(true);
                }
                return;
            }
        });

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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nameInput;
                int IDInput;
                List<String> searchResults = new ArrayList<>();

                if (!facultyNameField.getText().equals("") && facultyNumberField.getText().equals("")){

                    /**
                     * Only name given for search
                     */

                    // Retrieve name
                    nameInput = facultyNameField.getText();

                    //Search based on name
                    for (int i=0; i<savedFacNames.length; i++){
                        if (savedFacNames[i].equals(nameInput)){
                            searchResults.add(nameInput+"   "+CSVTools.findItem("faculty",nameInput).getID() );
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        facultyList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        facultyNameField.setText("No such Faculty with that name");
                    }

                } else if (facultyNameField.getText().equals("") && !facultyNumberField.getText().equals("")){

                    /**
                     * only id given for search
                     */

                    // Validate ID input
                    try {
                        IDInput = Integer.parseInt(facultyNumberField.getText());
                    } catch (NumberFormatException e1){
                        facultyNumberField.setText("*Invalid ID Input*");
                        return;
                    }

                    // Search based on ID
                    for (int i=0; i<savedFacIDs.length; i++){
                        if (savedFacIDs[i] == IDInput){
                            searchResults.add(CSVTools.findItem("faculty", IDInput).getName()+ "   "+IDInput);
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        facultyList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        facultyNumberField.setText("No such Faculty with that ID");
                    }

                } else if (  !facultyNameField.getText().equals("") && !facultyNumberField.getText().equals("") ){

                    /**
                     * Name and ID given for search
                     */

                    // Retrieve name
                    nameInput = facultyNameField.getText();

                    // Validate ID input
                    try {
                        IDInput = Integer.parseInt(facultyNumberField.getText());
                    } catch (NumberFormatException e1){
                        facultyNumberField.setText("*Invalid ID Input*");
                        return;
                    }

                    //Search Based on ID and Name
                    for (int i=0; i<savedFacIDs.length; i++){
                        if ( (savedFacIDs[i] == IDInput) && (savedFacNames[i].equals(nameInput)) ){
                            searchResults.add(nameInput+"   "+IDInput);
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        facultyList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        facultyNameField.setText("No such Faculty with that name");
                        facultyNumberField.setText("No such Faculty with that ID");
                    }

                } else {
                    // Neither Name or ID given for Search
                    facultyNameField.setText("Provide Faculty name");
                    facultyNumberField.setText("Provide Faculty number");
                }
            }
        });

    }


}
