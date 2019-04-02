package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FacultyAddPage extends JFrame {
    private JPanel outerPanel;
    private JPanel facultyAddPanel;
    private JLabel addLabel;
    private JLabel facultyNameLabel;
    private JTextField facultyNameField;
    private JLabel facultyIDLabel;
    private JTextField facultyIDField;
    private JLabel facultyDescriptionLabel;
    private JTextArea facultyDescriptionField;
    private JScrollPane facultyDescriptionPane;
    private JButton btnCreateFaculty;
    private JLabel errorMessage;
    private JLabel confirmationMessage;

    public FacultyAddPage(){

        setResizable(false);
        setTitle("Faculty Add Page");
        setBounds(100,100, 500, 530);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        outerPanel = new JPanel();
        outerPanel.setBackground(new Color(176, 196, 222));
        outerPanel.setBounds(0,0, 500, 530);
        getContentPane().add(outerPanel);
        outerPanel.setLayout(null);

        facultyAddPanel = new JPanel();
        facultyAddPanel.setBackground(SystemColor.window);
        facultyAddPanel.setBounds(10, 10, 465, 470);
        outerPanel.add(facultyAddPanel);
        facultyAddPanel.setLayout(null);

        addLabel = new JLabel("Create New Faculty");
        addLabel.setBounds(170, 5 , 110, 30);
        addLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        facultyAddPanel.add(addLabel);

        facultyNameLabel = new JLabel("Faculty Name:");
        facultyNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        facultyNameLabel.setBounds(60,50,110,30 );
        facultyAddPanel.add(facultyNameLabel);

        facultyNameField = new JTextField("Enter name of new Faculty");
        facultyNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        facultyNameField.setBounds(165, 50, 250, 30);
        facultyAddPanel.add(facultyNameField);

        facultyIDLabel = new JLabel("Faculty ID:");
        facultyIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        facultyIDLabel.setBounds( 60,100,80, 30);
        facultyAddPanel.add(facultyIDLabel);

        facultyIDField = new JTextField("Enter 4-digit ID # of new Faculty");
        facultyIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        facultyIDField.setBounds( 165,100, 250, 30);
        facultyAddPanel.add(facultyIDField);

        facultyDescriptionLabel = new JLabel("Faculty Description:");
        facultyDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        facultyDescriptionLabel.setBounds(60,150, 200, 30 );
        facultyAddPanel.add(facultyDescriptionLabel);

        facultyDescriptionField = new JTextArea("Enter Faculty Description");
        facultyDescriptionField.setBounds(35,200, 400, 200);
        facultyDescriptionPane = new JScrollPane(facultyDescriptionField);
        facultyDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        facultyDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        facultyDescriptionPane.setBounds(facultyDescriptionField.getBounds());
        facultyAddPanel.add(facultyDescriptionPane);

        btnCreateFaculty = new JButton("Add Faculty");
        btnCreateFaculty.setBounds(265, 420, 120, 30 );
        btnCreateFaculty.setFont(new Font("Sitka Small", Font.PLAIN, 14));
        btnCreateFaculty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                 * Check for valid user input
                 */
                int facultyIDinput;

                // Validate Faculty ID input(check for integer input and duplicate Faculty ID's)
                try {
                    facultyIDinput = Integer.parseInt(facultyIDField.getText());
                } catch (NumberFormatException e1){
                    facultyIDField.setText("*Invalid Faculty ID entered*");
                    return;
                }
                List<Integer> savedFacultyIDs = new ArrayList<Integer>();
                for (int i : CSVTools.getIDList("faculty")){
                    savedFacultyIDs.add(i);
                }
                if (savedFacultyIDs.contains(facultyIDinput)){
                    facultyIDField.setText("*Duplicate ID found, Use another ID*");
                    return;
                }

                /**
                 * Valid Faculty ID entered, create and add new item
                 */
                Item createdFaculty = new Item("faculty", facultyNameField.getText(),
                        facultyIDinput,
                        facultyDescriptionField.getText(),
                        null,
                        65535 );
                CSVTools.addItem(createdFaculty);
                confirmationMessage.setText("Faculty Created");
                confirmationMessage.setVisible(true);
                return;
            }
        });
        facultyAddPanel.add(btnCreateFaculty);

        confirmationMessage = new JLabel();
        confirmationMessage.setBounds(35, 420, 120, 30);
        confirmationMessage.setVisible(false);
        facultyAddPanel.add(confirmationMessage);

    }

}
