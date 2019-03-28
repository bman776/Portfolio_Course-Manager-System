package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class DepartmentSearchPage extends JFrame {
    private JLabel departmentLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JTextField departmentNameField;
    private JTextField departmentNumberField;
    private JList<String> departmentList;
    private JScrollPane departmentPane;
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
            savedDepartments[i] = savedDeptNames[i]+"   "+savedDeptIDs[i];
        }

        setResizable(false);
        setTitle("Department Search Page");
        setBounds(800,200, 500, 500);
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

        departmentLabel = new JLabel("Departments");
        departmentLabel.setBounds(170, 5, 100, 30);
        departmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        departmentSearchPanel.add(departmentLabel);

        departmentList = new JList(savedDepartments);
        departmentList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        departmentList.setBounds(100,45,300, 185);
        departmentPane = new JScrollPane(departmentList);
        departmentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        departmentPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        departmentPane.setBounds(departmentList.getBounds());
        departmentSearchPanel.add(departmentPane);
        departmentList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /**
                 * IF statement prevents the infamous double fire event associated with JLists selections
                 * (when made with mouse) and makes the selection in the JList re-selectable to the user
                 */
                if (departmentList.getSelectedValue() != null){
                    int deptID = Integer.parseInt(departmentList.getSelectedValue().split("   ")[1]);
                    DepartmentInfoPage selectedDept = new DepartmentInfoPage(CSVTools.findItem("department",deptID));
                    departmentList.clearSelection();
                    selectedDept.setVisible(true);
                }
                return;
            }
        });


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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameInput;
                int IDInput;
                List<String> searchResults = new ArrayList<>();

                if (!departmentNameField.getText().equals("") && departmentNumberField.getText().equals("")){

                    /**
                     * Only name given for search
                     */

                    // Retrieve name
                    nameInput = departmentNameField.getText();

                    //Search based on name
                    for (int i=0; i<savedDeptNames.length; i++){
                        if (savedDeptNames[i].equals(nameInput)){
                            searchResults.add(nameInput+"   "+CSVTools.findItem("department",nameInput).getID() );
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        departmentList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        departmentNameField.setText("No such Department with that name");
                    }

                } else if (departmentNameField.getText().equals("") && !departmentNumberField.getText().equals("")){

                    /**
                     * only id given for search
                     */

                    // Validate ID input
                    try {
                        IDInput = Integer.parseInt(departmentNumberField.getText());
                    } catch (NumberFormatException e1){
                        departmentNumberField.setText("*Invalid ID Input*");
                        return;
                    }

                    // Search based on ID
                    for (int i=0; i<savedDeptIDs.length; i++){
                        if (savedDeptIDs[i] == IDInput){
                            searchResults.add(CSVTools.findItem("department", IDInput).getName()+ "   "+IDInput);
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        departmentList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        departmentNumberField.setText("No such Faculty with that ID");
                    }

                } else if (  !departmentNameField.getText().equals("") && !departmentNumberField.getText().equals("") ){

                    /**
                     * Name and ID given for search
                     */

                    // Retrieve name
                    nameInput = departmentNameField.getText();

                    // Validate ID input
                    try {
                        IDInput = Integer.parseInt(departmentNumberField.getText());
                    } catch (NumberFormatException e1){
                        departmentNumberField.setText("*Invalid ID Input*");
                        return;
                    }

                    //Search Based on ID and Name
                    for (int i=0; i<savedDeptIDs.length; i++){
                        if ( (savedDeptIDs[i] == IDInput) && (savedDeptNames[i] == nameInput) ){
                            searchResults.add(nameInput+"   "+IDInput);
                        }
                    }

                    //display results of the search
                    if (searchResults.size() != 0)
                        departmentList.setListData(searchResults.stream().toArray(String[]::new));
                    else {
                        departmentNameField.setText("No such Department with that name");
                        departmentNumberField.setText("No such Department with that ID");
                    }

                } else {
                    // Neither Name or ID given for Search
                    departmentNameField.setText("Provide Department name");
                    departmentNumberField.setText("Provide Department number");
                }
            }
        });

    }

}
