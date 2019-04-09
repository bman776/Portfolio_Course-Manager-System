package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ItemInfoPage extends JFrame {

    /**
     *  Components used if Item type is Faculty, Department, Program or Course
     */
    private JPanel outerPanel;

    private JPanel innerContentPanel;
    private JLabel itemName;
    private JLabel itemID;
    private JLabel itemParent;
    private JButton btnEdit;
    private JButton btnRemove;
    private JLabel descriptionLabel;
    private JTextArea descriptionField;
    private JScrollPane descriptionPane;

    private JFrame deletionConfirmationFrame;
    private JTextArea warningMssg;
    private JButton confirm;
    private JButton cancel;

    /**
     * Components used if Item type is Faculty, Department or Program
     */
    private List<Item> childItems;
    private String[] childItemNameIDs;

    private JLabel childrenLabel;
    private JList<String> childList;
    private JScrollPane childPane;

    /**
     *  Components used if Item type is Course
     */
    private List<Course> prereqCourseList;
    private List<Course> antireqCourseList;
    private String[] prereqNameIDs;
    private String[] antireqsNameIDs;

    private JLabel labInfoLabel;
    private JTextArea labInfoField;
    private JScrollPane labInfoPane;

    private JLabel courseHours;
    private JLabel courseCredits;

    private JLabel prereqsLabel;
    private JList<String> prereqsList;
    private JScrollPane prereqPane;
    private JLabel antireqsLabel;
    private JList<String> antireqsList;
    private JScrollPane anitreqPane;

    public ItemInfoPage(Item selectedItem){


        /**
         * Deletion confirmation frame will be displayed whenever user attempts to delete the Item they are looking at
         */
        deletionConfirmationFrame = new JFrame("Deletion Confirmation");
        deletionConfirmationFrame.setResizable(false);
        deletionConfirmationFrame.setBounds(1000, 400, 350, 200);
        deletionConfirmationFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        deletionConfirmationFrame.getContentPane().setLayout(null);

        warningMssg = new JTextArea("Warning! Deleting an Item will also delete all items under it:\n" +
                " Deleting a Faculty will delete all Departments under it\n" +
                " Deleting a Department will delete all Programs under it\n" +
                " Deleting a Program will delete all Courses under it\n" +
                "you can view deleted items by selecting \"View Deleted Items\"\n" +
                "at the Menu. Are you sure you want to delete this Item?");
        warningMssg.setBounds(10, 10, 320, 100);
        warningMssg.setEditable(false);
        deletionConfirmationFrame.getContentPane().add(warningMssg);

        confirm = new JButton("Confirm");
        confirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
        confirm.setBounds(10, 120, 100, 30);
        deletionConfirmationFrame.getContentPane().add(confirm);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete Item
                if (selectedItem.getType().equals(CSVTools.typeF)){
                    CSVTools.deleteFaculty(selectedItem);
                } else if (selectedItem.getType().equals(CSVTools.typeD)){
                    CSVTools.deleteDepartment(selectedItem);
                } else if (selectedItem.getType().equals(CSVTools.typeP)){
                    CSVTools.deleteProgram(selectedItem);
                } else {
                    // selected Item type must be course
                    CSVTools.deleteCourse((Course)selectedItem);
                }
                deletionConfirmationFrame.dispose();
                dispose();
            }
        });

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cancel.setBounds(220, 120, 100, 30);
        deletionConfirmationFrame.getContentPane().add(cancel);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cancel deletion
                deletionConfirmationFrame.dispose();
            }
        });

        deletionConfirmationFrame.setVisible(false);



        if (selectedItem.getType().equals(CSVTools.typeC)){
            /**
             *  Item info page is arranged to display Course information
             */

            //Down cast item as course (safe because we know item must of type course in this block)
            Course selectedCourse = (Course)selectedItem;

            // Get Prereqs
            getPrereqCourse(selectedCourse);

            // Get Antireqs
            getAntireqCourse(selectedCourse);

            setResizable(false);
            setTitle("Course Info Page");
            setBounds(500, 200, 900, 550);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);

            outerPanel = new JPanel();
            outerPanel.setBackground(new Color(176, 196, 222));
            outerPanel.setBounds(0,0, 900, 550);
            getContentPane().add(outerPanel);
            outerPanel.setLayout(null);

            innerContentPanel = new JPanel();
            innerContentPanel.setBackground(SystemColor.window);
            innerContentPanel.setBounds(10, 10, 865, 490);
            outerPanel.add(innerContentPanel);
            innerContentPanel.setLayout(null);

            itemName = new JLabel("Course: "+selectedItem.getName());
            itemName.setBounds(10, 10, 545, 20);
            itemName.setFont(new Font("Tahoma", Font.PLAIN, 18));
            innerContentPanel.add(itemName);

            itemID = new JLabel("ID: "+selectedItem.getID());
            itemID.setBounds(10, 40, 545, 20);
            itemID.setFont(new Font("Tahoma", Font.PLAIN, 18));
            innerContentPanel.add(itemID);

            descriptionLabel = new JLabel("Description");
            descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            descriptionLabel.setBounds(10, 80, 422, 20);
            innerContentPanel.add(descriptionLabel);

            descriptionField = new JTextArea(selectedCourse.getDescription());
            descriptionField.setBounds(10, 110, 422, 170);
            descriptionField.setEditable(false);
            descriptionPane = new JScrollPane(descriptionField);
            descriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            descriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            descriptionPane.setBounds(descriptionField.getBounds());
            innerContentPanel.add(descriptionPane);

            labInfoLabel = new JLabel("Lab Information");
            labInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            labInfoLabel.setBounds(10, 300, 422, 20);
            innerContentPanel.add(labInfoLabel);

            labInfoField = new JTextArea(selectedCourse.getLabinfo());
            labInfoField.setBounds(10, 330, 422, 100 );
            labInfoField.setEditable(false);
            labInfoPane = new JScrollPane(labInfoField);
            labInfoPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            labInfoPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            labInfoPane.setBounds(labInfoField.getBounds());
            innerContentPanel.add(labInfoPane);

            courseHours = new JLabel("Hours of Instruction per week: "+selectedCourse.getHours());
            courseHours.setBounds(10,450, 300, 18);
            courseHours.setFont(new Font("Tahoma", Font.PLAIN, 15));
            innerContentPanel.add(courseHours);

            courseCredits = new JLabel("Credits: "+selectedCourse.getCredits());
            courseCredits.setBounds(330, 450, 100,18);
            courseCredits.setFont(new Font("Tahoma", Font.PLAIN, 15));
            innerContentPanel.add(courseCredits);

            prereqsLabel = new JLabel("Course Prerequisites:");
            prereqsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            prereqsLabel.setBounds(520, 80, 300, 20);
            innerContentPanel.add(prereqsLabel);

            prereqsList = new JList<>(prereqNameIDs);
            prereqsList.setBounds(452, 110, 400, 150);
            prereqPane = new JScrollPane(prereqsList);
            prereqPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            prereqPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            prereqPane.setBounds(prereqsList.getBounds());
            innerContentPanel.add(prereqPane);
            prereqsList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (prereqsList.getSelectedValue() != null){
                        int courseID = Integer.parseInt(prereqsList.getSelectedValue().split("   ")[1]);
                        ItemInfoPage selCourse = new ItemInfoPage(CSVTools.findCourse(courseID, CSVTools.fromCreated));
                        prereqsList.clearSelection();
                        selCourse.setVisible(true);
                    }
                    return;
                }
            });

            antireqsLabel = new JLabel("Course Anti-requisites:");
            antireqsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            antireqsLabel.setBounds(520, 280, 300, 20);
            innerContentPanel.add(antireqsLabel);

            antireqsList = new JList<>(antireqsNameIDs);
            antireqsList.setBounds(452,310, 400, 120);
            anitreqPane = new JScrollPane(antireqsList);
            anitreqPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            anitreqPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            anitreqPane.setBounds(antireqsList.getBounds());
            innerContentPanel.add(anitreqPane);
            antireqsList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (antireqsList.getSelectedValue() != null){
                        int courseID = Integer.parseInt(antireqsList.getSelectedValue().split("   ")[1]);
                        ItemInfoPage selCourse = new ItemInfoPage(CSVTools.findCourse(courseID, CSVTools.fromCreated));
                        antireqsList.clearSelection();
                        selCourse.setVisible(true);
                    }
                    return;
                }
            });

            btnEdit = new JButton("Edit");
            btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
            btnEdit.setBounds(500, 450, 100, 30);
            innerContentPanel.add(btnEdit);
            btnEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ItemEditPage editPage = new ItemEditPage(selectedItem);
                    editPage.setVisible(true);
                    dispose();

                }
            });

            btnRemove = new JButton("Remove");
            btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
            btnRemove.setBounds(620, 450, 100, 30);
            innerContentPanel.add(btnRemove);
            btnRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deletionConfirmationFrame.setVisible(true);
                }
            });

        } else {

            /**
             * Item info page is arranged to display Faculty, Department or program information (all have same format)
             */
            getChildItems(selectedItem);

            setResizable(false);
            setTitle(selectedItem.getType().substring(0,1).toUpperCase()+selectedItem.getType().substring(1)+"Info Page");
            setBounds(800, 300, 600, 500);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);

            outerPanel = new JPanel();
            outerPanel.setBackground(new Color(176, 196, 222));
            outerPanel.setBounds(0,0, 600, 500);
            getContentPane().add(outerPanel);
            outerPanel.setLayout(null);

            innerContentPanel = new JPanel();
            innerContentPanel.setBackground(SystemColor.window);
            innerContentPanel.setBounds(10, 10, 565, 440);
            outerPanel.add(innerContentPanel);
            innerContentPanel.setLayout(null);

            itemName = new JLabel(selectedItem.getType().substring(0,1).toUpperCase()+selectedItem.getType().substring(1)+
                    ": "+selectedItem.getName());
            itemName.setBounds(10, 10, 545, 20);
            itemName.setFont(new Font("Tahoma", Font.PLAIN, 18));
            innerContentPanel.add(itemName);

            itemID = new JLabel("ID: "+selectedItem.getID());
            itemID.setBounds(10, 40, 545, 20);
            itemID.setFont(new Font("Tahoma", Font.PLAIN, 18));
            innerContentPanel.add(itemID);

            itemParent = new JLabel();
            itemParent.setBounds(10, 70, 545, 20);
            itemParent.setFont(new Font("Tahoma", Font.PLAIN, 18));
            if (selectedItem.getType().equals(CSVTools.typeD)){
                itemParent.setText("In the Faculty of: "+
                        CSVTools.findItem(CSVTools.typeF, selectedItem.getParent(), CSVTools.fromCreated).getName());
                innerContentPanel.add(itemParent);
            } else if (selectedItem.getType().equals(CSVTools.typeP)){
                itemParent.setText("In the Department of: "+
                        CSVTools.findItem(CSVTools.typeD, selectedItem.getParent(), CSVTools.fromCreated).getName());
                innerContentPanel.add(itemParent);
            } else {
                //Faculties have no parent items so itemParent label is not use
            }

            descriptionLabel = new JLabel(selectedItem.getType().substring(0,1).toUpperCase()+selectedItem.getType().substring(1)
                    +" Description:");
            descriptionLabel.setBounds(10,110, 200, 20 );
            descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            innerContentPanel.add(descriptionLabel);

            descriptionField = new JTextArea(selectedItem.getDescription());
            descriptionField.setBounds(10, 140, 285, 230);
            descriptionField.setEditable(false);
            descriptionPane = new JScrollPane(descriptionField);
            descriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            descriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            descriptionPane.setBounds(descriptionField.getBounds());
            innerContentPanel.add(descriptionPane);

            childrenLabel = new JLabel();
            childrenLabel.setBounds(340,110, 200, 20 );
            childrenLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            if (selectedItem.getType().equals(CSVTools.typeF)){
                childrenLabel.setText(selectedItem.getType().substring(0,1).toUpperCase()+selectedItem.getType().substring(1)
                        +" Departments:");
            } else if (selectedItem.getType().equals(CSVTools.typeD)){
                childrenLabel.setText(selectedItem.getType().substring(0,1).toUpperCase()+selectedItem.getType().substring(1)
                        +" Programs:");
            } else{
                //selectedItem must be of type Program
                childrenLabel.setText(selectedItem.getType().substring(0,1).toUpperCase()+selectedItem.getType().substring(1)
                        +" Courses:");
            }
            innerContentPanel.add(childrenLabel);

            childList = new JList<String>(childItemNameIDs);
            childList.setBounds(315, 140, 240, 230);
            childPane = new JScrollPane(childList);
            childPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            childPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            childPane.setBounds(childList.getBounds());
            innerContentPanel.add(childPane);
            childList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    /**
                     * IF statement prevents the infamous double fire event associated with JLists selections
                     * (when made with mouse) and makes the selection in the JList re-selectable to the user
                     */
                    if (childList.getSelectedValue() != null){
                        int childID = Integer.parseInt(childList.getSelectedValue().split("   ")[1]);
                        ItemInfoPage selectedIteminfo;

                        if (selectedItem.getType().equals(CSVTools.typeF)){
                            selectedIteminfo = new ItemInfoPage(CSVTools.findItem(CSVTools.typeD,childID, CSVTools.fromCreated));
                        } else if (selectedItem.getType().equals(CSVTools.typeD)){
                            selectedIteminfo = new ItemInfoPage(CSVTools.findItem(CSVTools.typeP,childID, CSVTools.fromCreated));
                        } else {
                            // selectedItem is of type Program so its children are Courses
                            selectedIteminfo = new ItemInfoPage(CSVTools.findCourse(childID, CSVTools.fromCreated));
                        }

                        childList.clearSelection();
                        selectedIteminfo.setVisible(true);
                    }
                    return;
                }
            });

            btnEdit = new JButton("Edit");
            btnEdit.setBounds(10 , 390, 100, 30);
            btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
            innerContentPanel.add(btnEdit);
            btnEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open item edit page
                    ItemEditPage editPage = new ItemEditPage(selectedItem);
                    editPage.setVisible(true);
                    dispose();

                }
            });

            btnRemove = new JButton("Remove");
            btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
            btnRemove.setBounds(130, 390, 100, 30);
            innerContentPanel.add(btnRemove);
            btnRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Confirm user wants to delete Item
                   deletionConfirmationFrame.setVisible(true);
                }
            });

        }

    }

    private void getChildItems(Item selectedItem){
        childItems = new ArrayList<Item>();
        String childType;
        if (selectedItem.getType().equals(CSVTools.typeF)){
            childType = CSVTools.typeD;
        } else if (selectedItem.getType().equals(CSVTools.typeD)){
            childType = CSVTools.typeP;
        } else {
            childType = CSVTools.typeC;
        }
        if (childType.equals(CSVTools.typeC)) {
            for (int i=0; i<CSVTools.getIDList(childType, CSVTools.fromCreated).length; i++){
                Item childItem = CSVTools.findCourse(CSVTools.getIDList(childType, CSVTools.fromCreated)[i], CSVTools.fromCreated);
                if ( childItem.getParent() == selectedItem.getID() ){
                    childItems.add(childItem);
                }
            }
        } else {
            for (int i=0; i<CSVTools.getIDList(childType, CSVTools.fromCreated).length; i++){
                Item childItem = CSVTools.findItem(childType, CSVTools.getIDList(childType, CSVTools.fromCreated)[i],CSVTools.fromCreated);
                if ( childItem.getParent() == selectedItem.getID() ){
                    childItems.add(childItem);
                }
            }
        }
        childItemNameIDs = new String[childItems.size()];
        for (int i = 0; i< childItems.size(); i++){
            childItemNameIDs[i] = childItems.get(i).getName()+"   "+ childItems.get(i).getID();
        }

    }

    private void getPrereqCourse(Course selectedCourse){
        prereqCourseList = new ArrayList<Course>();
        if (selectedCourse.getPrerequisites() != null) {
            for (int i=0; i<selectedCourse.getPrerequisites().length; i++){
                prereqCourseList.add( CSVTools.findCourse(selectedCourse.getPrerequisites()[i], CSVTools.fromCreated) );
            }
            prereqNameIDs = new String[prereqCourseList.size()];
            for (int i=0; i<prereqCourseList.size(); i++){
                prereqNameIDs[i] = prereqCourseList.get(i).getName()+"   "+prereqCourseList.get(i).getID();
            }
        } else {
            prereqNameIDs = new String[1];
            prereqNameIDs[0] = "There are no Prerequisites for this Course";
        }
    }

    private void getAntireqCourse(Course selectedCourse){
        antireqCourseList = new ArrayList<Course>();
        if (selectedCourse.getAntirequisites() != null){
            for(int i=0; i<selectedCourse.getAntirequisites().length; i++){
                antireqCourseList.add( CSVTools.findCourse(selectedCourse.getAntirequisites()[i], CSVTools.fromCreated) );
            }
            antireqsNameIDs = new String[antireqCourseList.size()];
            for (int i=0; i<antireqCourseList.size(); i++){
                antireqsNameIDs[i] = antireqCourseList.get(i).getName()+"   "+antireqCourseList.get(i).getID();
            }
        } else {
            antireqsNameIDs = new String[1];
            antireqsNameIDs[0] = "There are no anti-requisites for this Course";
        }

    }
}
