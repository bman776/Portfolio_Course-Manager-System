package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentAddPage extends JFrame {
    private JPanel outerPanel;
    private JPanel deptAddPanel;
    private JLabel addLabel;
    private JLabel deptNameLabel;
    private JTextField deptNameField;
    private JLabel deptIDLabel;
    private JTextField deptIDField;
    private JLabel parentIDLabel;
    private JTextField parentIDField;
    private JLabel deptDescriptionLabel;
    private JTextArea deptDescriptionField;
    private JScrollPane deptDescriptionPane;
    private JButton btnCreateDepartment;
    private JLabel errorMessage;
    private JLabel confirmationMessage;

    public DepartmentAddPage(){

        setResizable(false);
        setTitle("Department Add Page");
        setBounds(100,100, 500, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        outerPanel = new JPanel();
        outerPanel.setBackground(new Color(176, 196, 222));
        outerPanel.setBounds(0,0, 500, 580);
        getContentPane().add(outerPanel);
        outerPanel.setLayout(null);

        deptAddPanel = new JPanel();
        deptAddPanel.setBackground(SystemColor.window);
        deptAddPanel.setBounds(10, 10, 465, 520);
        outerPanel.add(deptAddPanel);
        deptAddPanel.setLayout(null);

        addLabel = new JLabel("Create New Dept.");
        addLabel.setBounds(170, 5 , 110, 30);
        addLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        deptAddPanel.add(addLabel);

        deptNameLabel = new JLabel("Dept. Name:");
        deptNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        deptNameLabel.setBounds(60,50,110,30 );
        deptAddPanel.add(deptNameLabel);

        deptNameField = new JTextField("Enter name of new Dept.");
        deptNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        deptNameField.setBounds(165, 50, 250, 30);
        deptAddPanel.add(deptNameField);

        deptIDLabel = new JLabel("Dept. ID:");
        deptIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        deptIDLabel.setBounds( 60,100,80, 30);
        deptAddPanel.add(deptIDLabel);

        deptIDField = new JTextField("Enter 4-digit ID # of new Dept.");
        deptIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        deptIDField.setBounds( 165,100, 250, 30);
        deptAddPanel.add(deptIDField);

        parentIDLabel = new JLabel("Parent Faculty ID:");
        parentIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        parentIDLabel.setBounds(60, 150, 100, 30);
        deptAddPanel.add(parentIDLabel);

        parentIDField = new JTextField("Enter 4-digit ID # of parent Faculty of dept.");
        parentIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        parentIDField.setBounds(165, 150, 250, 30);
        deptAddPanel.add(parentIDField);

        deptDescriptionLabel = new JLabel("Dept. Description:");
        deptDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        deptDescriptionLabel.setBounds(60,200, 200, 30 );
        deptAddPanel.add(deptDescriptionLabel);

        deptDescriptionField = new JTextArea("Enter Dept. Description");
        deptDescriptionField.setBounds(35,250, 400, 200);
        deptDescriptionPane = new JScrollPane(deptDescriptionField);
        deptDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        deptDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        deptDescriptionPane.setBounds(deptDescriptionField.getBounds());
        deptAddPanel.add(deptDescriptionPane);

        btnCreateDepartment = new JButton("Add Dept.");
        btnCreateDepartment.setBounds(265, 470, 120, 30 );
        btnCreateDepartment.setFont(new Font("Sitka Small", Font.PLAIN, 14));
        btnCreateDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                 * Check for valid user input
                 */
                int deptIDinput;
                int parentIDinput;

                // Validate Department ID input (check for integer input and duplicate Department ID's)
                try {
                    deptIDinput = Integer.parseInt(deptIDField.getText());
                } catch (NumberFormatException e1){
                    deptIDField.setText("*Invalid Dept. ID entered*");
                    return;
                }
                List<Integer> savedDeptIDs = new ArrayList<Integer>();
                for (int i : CSVTools.getIDList("department")){
                    savedDeptIDs.add(i);
                }
                if (savedDeptIDs.contains(deptIDinput)){
                    deptIDField.setText("*Duplicate ID found, Use another ID*");
                    return;
                }

                // Validate Parent Faculty ID input (check for integer input and existence of corresponding faculty)
                try {
                    parentIDinput = Integer.parseInt(parentIDField.getText());
                } catch (NumberFormatException e1){
                    parentIDField.setText("*Invalid Faculty ID entered*");
                    return;
                }
                List<Integer> savedFacultyIDs = new ArrayList<Integer>();
                for (int i : CSVTools.getIDList("faculty")){
                    savedFacultyIDs.add(i);
                }
                if ( !(savedFacultyIDs.contains(parentIDinput)) ){
                    parentIDField.setText("*No Such Faculty*");
                    return;
                }

                /**
                 * Valid Department and Parent Faculty ID entered, create and add new Department
                 */
                 Item createdDept = new Item("department", deptNameField.getText(),
                        deptIDinput,
                        deptDescriptionField.getText(),
                        null,
                        parentIDinput
                );
                CSVTools.addChildIDtoParent(createdDept);
                CSVTools.addItem(createdDept);
                confirmationMessage.setText("Department Created");
                confirmationMessage.setVisible(true);
                return;
            }
        });
        deptAddPanel.add(btnCreateDepartment);

        confirmationMessage = new JLabel();
        confirmationMessage.setBounds(35, 470, 120, 30);
        confirmationMessage.setVisible(false);
        deptAddPanel.add(confirmationMessage);

    }

}
