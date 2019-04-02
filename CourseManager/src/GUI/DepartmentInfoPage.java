package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentInfoPage extends JFrame{
    
    private List<Item> childPrgms;
    private String[] childPrgrmNameIDs;

    private JPanel outerPanel;

    private JPanel innerContentPanel;
    private JLabel facultyName;
    private JLabel facultyID;
    private JLabel descriptionLabel;
    private JTextArea descriptionField;
    private JScrollPane descriptionPane;
    private JLabel programsLabel;
    private JList<String> programsList;
    private JScrollPane programPane;
    private JLabel parentFaculty;

    private JButton btnEdit;

    public DepartmentInfoPage(Item selectedDepartment){

        // Get Child Programs
        childPrgms = new ArrayList<Item>();
        for (int i=0; i<CSVTools.getIDList("program").length; i++){
            Item deptItem = CSVTools.findItem( "program", CSVTools.getIDList("program")[i] );
            if ( deptItem.getParent() == selectedDepartment.getID() ){
                childPrgms.add(deptItem);
            }
        }
        childPrgrmNameIDs = new String[childPrgms.size()];
        for (int i = 0; i< childPrgms.size(); i++){
            childPrgrmNameIDs[i] = childPrgms.get(i).getName()+"   "+ childPrgms.get(i).getID();
        }

        setResizable(false);
        setTitle("Department Info Page");
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

        facultyName = new JLabel("Department: "+selectedDepartment.getName());
        facultyName.setBounds(10, 10, 545, 20);
        facultyName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(facultyName);

        facultyID = new JLabel("ID: "+selectedDepartment.getID());
        facultyID.setBounds(10, 40, 545, 20);
        facultyID.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(facultyID);

        parentFaculty = new JLabel("Parent Faculty: "+CSVTools.findItem("faculty", selectedDepartment.getParent()).getName());
        parentFaculty.setBounds(10, 70, 545, 20);
        parentFaculty.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(parentFaculty);

        descriptionLabel = new JLabel("Department Description:");
        descriptionLabel.setBounds(10,110, 200, 20 );
        descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(descriptionLabel);

        descriptionField = new JTextArea(selectedDepartment.getDescription());
        descriptionField.setBounds(10, 140, 285, 230);
        descriptionField.setEditable(false);
        descriptionPane = new JScrollPane(descriptionField);
        descriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setBounds(descriptionField.getBounds());
        innerContentPanel.add(descriptionPane);

        programsLabel = new JLabel("Department Programs:");
        programsLabel.setBounds(340,110, 200, 20 );
        programsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(programsLabel);

        programsList = new JList<String>(childPrgrmNameIDs);
        programsList.setBounds(315, 140, 240, 230);
        programPane = new JScrollPane(programsList);
        programPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        programPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        programPane.setBounds(programsList.getBounds());
        innerContentPanel.add(programPane);
        programsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /**
                 * IF statement prevents the infamous double fire event associated with JLists selections
                 * (when made with mouse) and makes the selection in the JList re-selectable to the user
                 */
                if (programsList.getSelectedValue() != null){
                    int prgmID = Integer.parseInt(programsList.getSelectedValue().split("   ")[1]);
                    ProgramInfoPage selectedPrgm = new ProgramInfoPage(CSVTools.findItem("program",prgmID));
                    programsList.clearSelection();
                    selectedPrgm.setVisible(true);
                }
                return;
            }
        });

        btnEdit = new JButton("Edit Faculty");
        btnEdit.setBounds(10 , 390, 130, 25);
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        innerContentPanel.add(btnEdit);

    }
}
