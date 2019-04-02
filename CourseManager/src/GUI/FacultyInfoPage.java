package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyInfoPage extends JFrame {

    private List<Item> childDepts;
    private String[] childDeptNameIDs;

    private JPanel outerPanel;
    private JPanel innerContentPanel;
    private JLabel facultyName;
    private JLabel facultyID;
    private JLabel descriptionLabel;
    private JTextArea descriptionField;
    private JScrollPane descriptionPane;
    private JLabel departmentsLabel;
    private JList<String> departmentsList;
    private JScrollPane departmentPane;
    private JButton btnEdit;

    public FacultyInfoPage(Item selectedFaculty){

        // Get Child Departments
        childDepts = new ArrayList<Item>();
        for (int i=0; i<CSVTools.getIDList("department").length; i++){
            Item deptItem = CSVTools.findItem( "department", CSVTools.getIDList("department")[i] );
            if ( deptItem.getParent() == selectedFaculty.getID() ){
                childDepts.add(deptItem);
            }
        }
        childDeptNameIDs = new String[childDepts.size()];
        for (int i = 0; i< childDepts.size(); i++){
            childDeptNameIDs[i] = childDepts.get(i).getName()+"   "+ childDepts.get(i).getID();
        }

        setResizable(false);
        setTitle("Faculty Info Page");
        setBounds(100, 400, 600, 500);
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

        facultyName = new JLabel("Faculty: "+selectedFaculty.getName());
        facultyName.setBounds(10, 10, 545, 20);
        facultyName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(facultyName);

        facultyID = new JLabel("ID: "+selectedFaculty.getID());
        facultyID.setBounds(10, 40, 545, 20);
        facultyID.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(facultyID);

        descriptionLabel = new JLabel("Faculty Description:");
        descriptionLabel.setBounds(10,80, 200, 20 );
        descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(descriptionLabel);

        descriptionField = new JTextArea(selectedFaculty.getDescription());
        descriptionField.setBounds(10, 110, 285, 230);
        descriptionField.setEditable(false);
        descriptionPane = new JScrollPane(descriptionField);
        descriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setBounds(descriptionField.getBounds());
        innerContentPanel.add(descriptionPane);

        departmentsLabel = new JLabel("Faculty Departments:");
        departmentsLabel.setBounds(350,80, 190, 20 );
        departmentsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(departmentsLabel);

        departmentsList = new JList<String>(childDeptNameIDs);
        departmentsList.setBounds(315, 110, 240, 230);
        departmentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        departmentPane = new JScrollPane(departmentsList);
        departmentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        departmentPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        departmentPane.setBounds(departmentsList.getBounds());
        innerContentPanel.add(departmentPane);
        departmentsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /**
                 * IF statement prevents the infamous double fire event associated with JLists selections
                 * (when made with mouse) and makes the selection in the JList re-selectable to the user
                 */
                if (departmentsList.getSelectedValue() != null){
                    int deptID = Integer.parseInt(departmentsList.getSelectedValue().split("   ")[1]);
                    DepartmentInfoPage selectedDept = new DepartmentInfoPage(CSVTools.findItem("department",deptID));
                    departmentsList.clearSelection();
                    selectedDept.setVisible(true);
                }
                return;
            }
        });


        btnEdit = new JButton("Edit Faculty");
        btnEdit.setBounds(10 , 360, 130, 25);
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        innerContentPanel.add(btnEdit);

    }


}
