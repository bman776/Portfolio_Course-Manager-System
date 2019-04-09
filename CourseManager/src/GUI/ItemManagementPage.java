package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ItemManagementPage extends JFrame{

    private String[] savedItemNameIDs;

    private JPanel innerPanel;
    private JPanel outerPanel;
    private JLabel itemLabel;
    private JLabel searchLabel;
    private JLabel nameLabel;
    private JLabel itemIDlabel;
    private JTextField itemNameField;
    private JTextField itemNumberField;
    private JList<String> itemList;
    private JScrollPane itemPane;
    private JButton searchButton;
    private JButton addButton;
    private JLabel Mssg;
    private JButton btnRefreshList;

    public ItemManagementPage(String type){

        //Get saved Item names/IDs
        initializeSavedItemNameIDs(type);

        //set Item search JFrame size and properties
        setResizable(false);
        setTitle(type.substring(0,1).toUpperCase()+type.substring(1)+" Search Page");
        setBounds(800,100, 500, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        outerPanel = new JPanel();
        outerPanel.setBackground(new Color(176, 196, 222));
        outerPanel.setBounds(0, 0, 500, 550);
        getContentPane().add(outerPanel);
        outerPanel.setLayout(null);

        innerPanel = new JPanel();
        innerPanel.setBackground(SystemColor.window);
        innerPanel.setBounds(10,10, 465, 490);
        outerPanel.add(innerPanel);
        innerPanel.setLayout(null);

        itemLabel = new JLabel();
        if (type.equals(CSVTools.typeF))
            itemLabel.setText("Faculties");
        else
            itemLabel.setText(type.substring(0,1).toUpperCase()+type.substring(1)+"s:");
        itemLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        itemLabel.setBounds(150, 5, 120, 40);
        innerPanel.add(itemLabel);

        itemList = new JList(savedItemNameIDs);
        itemList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        itemList.setBounds(80,52,300, 185);
        itemPane = new JScrollPane(itemList);
        itemPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        itemPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        itemPane.setBounds(itemList.getBounds());
        innerPanel.add(itemPane);
        itemList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /**
                 * IF statement prevents the infamous double fire event associated with JLists selections
                 * (when made with mouse) and makes the selection in the JList re-selectable to the user
                 */
                if (itemList.getSelectedValue() != null){
                    int selectedItemID = Integer.parseInt(itemList.getSelectedValue().split("   ")[1]);
                    if (type.equals(CSVTools.typeC)){
                        ItemInfoPage selectedItemInfo = new ItemInfoPage(CSVTools.findCourse(selectedItemID, CSVTools.fromCreated));
                        selectedItemInfo.setVisible(true);
                    } else {
                        ItemInfoPage selectedItemInfo = new ItemInfoPage(CSVTools.findItem(type, selectedItemID, CSVTools.fromCreated));
                        selectedItemInfo.setVisible(true);
                    }
                    itemList.clearSelection();
                }
                return;
            }
        });

        searchLabel = new JLabel("Search for a "+type.substring(0,1).toUpperCase()+type.substring(1)+":");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchLabel.setBounds(40,260,150, 15);
        innerPanel.add(searchLabel);

        nameLabel = new JLabel(type.substring(0,1).toUpperCase()+type.substring(1)+" Name:");
        nameLabel.setBounds(40, 298, 120, 15);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        innerPanel.add(nameLabel);

        itemNameField = new JTextField();
        itemNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        itemNameField.setBounds(165,295,250, 25);
        innerPanel.add(itemNameField);

        itemIDlabel = new JLabel();
        if (type.equals(CSVTools.typeD))
            itemIDlabel.setText("Dept. ID Number");
        else
            itemIDlabel.setText(type.substring(0,1).toUpperCase()+type.substring(1)+" ID Number:");
        itemIDlabel.setBounds(40, 338, 120, 15);
        itemIDlabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        innerPanel.add(itemIDlabel);

        itemNumberField = new JTextField();
        itemNumberField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        itemNumberField.setBounds( 165,335, 250, 25);
        innerPanel.add(itemNumberField);

        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(176, 196, 222));
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        searchButton.setBounds(40,370, 80, 30);
        innerPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch(type);
            }
        });

        addButton = new JButton("Add New "+type.substring(0,1).toUpperCase()+type.substring(1));
        addButton.setBackground(new Color(176, 196, 222));
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        addButton.setBounds(40, 430, 170, 30);
        innerPanel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(openItemAddPage(type))){
                    //Add page failed to open
                    Mssg.setVisible(true);
                    return;
                } else {
                    //Add page successfully opened
                    Mssg.setVisible(false);
                    return;
                }
            }
        });

        Mssg = new JLabel();
        Mssg.setBackground(new Color(176, 196, 222));
        Mssg.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Mssg.setBounds(225, 430, 200, 30);
        innerPanel.add(Mssg);

        btnRefreshList = new JButton("Refresh list");
        btnRefreshList.setBackground(new Color(176, 196, 222));
        btnRefreshList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnRefreshList.setBounds(130,370, 100, 30);
        innerPanel.add(btnRefreshList);
        btnRefreshList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeSavedItemNameIDs(type);
                itemList.setListData(savedItemNameIDs);
                Mssg.setVisible(false);
            }
        });


    }

    private void performSearch(String type){
        String nameInput;
        int IDInput;
        List<String> searchResults = new ArrayList<>();

        if (!itemNameField.getText().equals("") && itemNumberField.getText().equals("")){

            /**
             * Only name given for search
             */
            // Search based on name
            searchResults = nameSearch(itemNameField.getText(), type);

            //display results of the search if search returned positive
            if (searchResults.size() != 0)
                itemList.setListData(searchResults.stream().toArray(String[]::new));
            else {
                itemNameField.setText("No such"+type.substring(0,1).toUpperCase()+type.substring(1)+"with that name");
            }

        } else if (itemNameField.getText().equals("") && !itemNumberField.getText().equals("")){

            /**
             * only id given for search
             */

            // Validate ID input
            try {
                IDInput = Integer.parseInt(itemNumberField.getText());
            } catch (NumberFormatException e1){
                itemNumberField.setText("*Invalid ID Input*");
                return;
            }

            // Search based on ID
            searchResults = IDsearch(IDInput, type);

            //display results of the search
            if (searchResults.size() != 0)
                itemList.setListData(searchResults.stream().toArray(String[]::new));
            else {
                itemNumberField.setText("No such"+type.substring(0,1).toUpperCase()+type.substring(1)+"with that ID");
            }

        } else if (  !itemNameField.getText().equals("") && !itemNumberField.getText().equals("") ){

            /**
             * Name and ID given for search
             */

            // Retrieve name
            nameInput = itemNameField.getText();

            // Validate ID input
            try {
                IDInput = Integer.parseInt(itemNumberField.getText());
            } catch (NumberFormatException e1){
                itemNumberField.setText("*Invalid ID Input*");
                return;
            }

            //Search Based on ID and Name
            searchResults = nameIDsearch(IDInput, nameInput, type);

            //display results of the search
            if (searchResults.size() != 0)
                itemList.setListData(searchResults.stream().toArray(String[]::new));
            else {
                itemNameField.setText("No such "+type.substring(0,1).toUpperCase()+type.substring(1)+" with that name");
                itemNumberField.setText("No such "+type.substring(0,1).toUpperCase()+type.substring(1)+" with that name");
            }

        } else {
            // Neither Name or ID given for Search
            itemNameField.setText("Provide "+type.substring(0,1).toUpperCase()+type.substring(1)+" name");
            itemNumberField.setText("Provide "+type.substring(0,1).toUpperCase()+type.substring(1)+" name");
        }
    }


    private boolean openItemAddPage(String type){
        try {
            /**
             * User is not permitted to create a Department, Program or Course if there are no Parent Faculty,
             * Departments or programs for them to belong to. We then check to see if the corresponding CSV files holding
             * these Parent items are empty, if they are then the Add Page for that Child item cannot be open
             */
            //If type is not of Faculty then check parent CSV file for contents
            if (!type.equals(CSVTools.typeF)){
                // type is Department, Program or Course
                if (type.equals(CSVTools.typeD) && (CSVTools.fileIsEmpty(CSVTools.typeF, CSVTools.fromCreated))){
                    Mssg.setText("No Faculties Created for Dept.'s");
                    return false;
                }
                else if (type.equals(CSVTools.typeP) && (CSVTools.fileIsEmpty(CSVTools.typeD, CSVTools.fromCreated))) {
                    Mssg.setText("No Dept.'s created for Programs");
                    return false;
                }
                else if (type.equals(CSVTools.typeC) && (CSVTools.fileIsEmpty(CSVTools.typeP, CSVTools.fromCreated))){
                    Mssg.setText("No Programs created for Courses");
                    return false;
                }
            }
            //Parent CSV file is not empty

            ItemAddPage addItemWindow = new ItemAddPage(type, Mssg);
            addItemWindow.setVisible(true);

            //Add page successfully opened
            return true;
        } catch (Exception a) {
            a.printStackTrace();
            Mssg.setText("Error accessing storage files");
            return false;
        }

    }

    /**
     *  Get saved Item Names and IDs and store corresponding Item Name and ID together as String within array
     */
    private void initializeSavedItemNameIDs(String type){
        savedItemNameIDs = new String[CSVTools.getNameList(type, CSVTools.fromCreated).length];
        for (int i=0; i<CSVTools.getIDList(type, CSVTools.fromCreated).length; i++){
            savedItemNameIDs[i] = CSVTools.getNameList(type, CSVTools.fromCreated)[i]+"   "+CSVTools.getIDList(type, CSVTools.fromCreated)[i];
        }
    }

    private List<String> nameSearch(String nameInput, String type){
        List<String> searchResults = new ArrayList<>();
        for (int i=0; i<savedItemNameIDs.length; i++){
            if (savedItemNameIDs[i].split("   ")[0].equals(nameInput)){
                searchResults.add(nameInput+"   "+CSVTools.findItem(type,nameInput, CSVTools.fromCreated).getID() );
            }
        }
        return searchResults;
    }

    private List<String> IDsearch (int IDInput, String type){
        List<String> searchResults = new ArrayList<>();
        for (int i=0; i<savedItemNameIDs.length; i++){
            if (Integer.parseInt(savedItemNameIDs[i].split("   ")[1]) == IDInput){
                searchResults.add(CSVTools.findItem(type, IDInput, CSVTools.fromCreated).getName()+ "   "+IDInput);
            }
        }
        return searchResults;
    }

    private List<String> nameIDsearch (int IDInput, String nameInput, String type){
        List<String> searchResults = new ArrayList<>();
        for (int i=0; i<savedItemNameIDs.length; i++){
            if (savedItemNameIDs[i].split("   ")[0].equals(nameInput) &&
                    Integer.parseInt(savedItemNameIDs[i].split("   ")[1]) == IDInput){
                searchResults.add(nameInput+"   "+IDInput);
            }
        }
        return searchResults;
    }


}
