package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramAddPage extends JFrame {
    private JPanel outerPanel;
    private JPanel prgmAddPanel;
    private JLabel addLabel;
    private JLabel prgmNameLabel;
    private JTextField prgmNameField;
    private JLabel prgmIDLabel;
    private JTextField prgmIDField;
    private JLabel parentIDLabel;
    private JTextField parentIDField;
    private JLabel prgmDescriptionLabel;
    private JTextArea prgmDescriptionField;
    private JScrollPane prgmDescriptionPane;
    private JButton btnCreateProgram;
    private JLabel errorMessage;
    private JLabel confirmationMessage;

    public ProgramAddPage(){

        setResizable(false);
        setTitle("Program Add Page");
        setBounds(100,100, 500, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        outerPanel = new JPanel();
        outerPanel.setBackground(new Color(176, 196, 222));
        outerPanel.setBounds(0,0, 500, 580);
        getContentPane().add(outerPanel);
        outerPanel.setLayout(null);

        prgmAddPanel = new JPanel();
        prgmAddPanel.setBackground(SystemColor.window);
        prgmAddPanel.setBounds(10, 10, 465, 520);
        outerPanel.add(prgmAddPanel);
        prgmAddPanel.setLayout(null);

        addLabel = new JLabel("Create New Program");
        addLabel.setBounds(170, 5 , 110, 30);
        addLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        prgmAddPanel.add(addLabel);

        prgmNameLabel = new JLabel("Program Name:");
        prgmNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        prgmNameLabel.setBounds(60,50,110,30 );
        prgmAddPanel.add(prgmNameLabel);

        prgmNameField = new JTextField("Enter name of new program");
        prgmNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        prgmNameField.setBounds(165, 50, 250, 30);
        prgmAddPanel.add(prgmNameField);

        prgmIDLabel = new JLabel("Program ID:");
        prgmIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        prgmIDLabel.setBounds( 60,100,80, 30);
        prgmAddPanel.add(prgmIDLabel);

        prgmIDField = new JTextField("Enter 4-digit ID # of new Program");
        prgmIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        prgmIDField.setBounds( 165,100, 250, 30);
        prgmAddPanel.add(prgmIDField);

        parentIDLabel = new JLabel("Parent Dept. ID:");
        parentIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        parentIDLabel.setBounds(60, 150, 100, 30);
        prgmAddPanel.add(parentIDLabel);

        parentIDField = new JTextField("Enter 4-digit ID # of parent Department of program");
        parentIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        parentIDField.setBounds(165, 150, 250, 30);
        prgmAddPanel.add(parentIDField);

        prgmDescriptionLabel = new JLabel("Program Description:");
        prgmDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        prgmDescriptionLabel.setBounds(60,200, 200, 30 );
        prgmAddPanel.add(prgmDescriptionLabel);

        prgmDescriptionField = new JTextArea("Enter Program Description");
        prgmDescriptionField.setBounds(35,250, 400, 200);
        prgmDescriptionPane = new JScrollPane(prgmDescriptionField);
        prgmDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        prgmDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        prgmDescriptionPane.setBounds(prgmDescriptionField.getBounds());
        prgmAddPanel.add(prgmDescriptionPane);

        btnCreateProgram = new JButton("Add Program");
        btnCreateProgram.setBounds(265, 470, 140, 30 );
        btnCreateProgram.setFont(new Font("Sitka Small", Font.PLAIN, 14));
        btnCreateProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                 * Check for valid user input
                 */
                int prgmIDinput;
                int parentIDinput;

                // Validate Department ID input (check for integer input and duplicate Department ID's)
                try {
                    prgmIDinput = Integer.parseInt(prgmIDField.getText());
                } catch (NumberFormatException e1){
                    prgmIDField.setText("*Invalid Program ID entered*");
                    return;
                }
                List<Integer> savedPrgmIDs = new ArrayList<Integer>();
                for (int i : CSVTools.getIDList("program")){
                    savedPrgmIDs.add(i);
                }
                if (savedPrgmIDs.contains(prgmIDinput)){
                    prgmIDField.setText("*Duplicate ID found, Use another ID*");
                    return;
                }

                // Validate Parent Department ID input (check for integer input and existence of corresponding faculty)
                try {
                    parentIDinput = Integer.parseInt(parentIDField.getText());
                } catch (NumberFormatException e1){
                    parentIDField.setText("*Invalid Dept. ID entered*");
                    return;
                }
                List<Integer> savedDeptIDs = new ArrayList<Integer>();
                for (int i : CSVTools.getIDList("department")){
                    savedDeptIDs.add(i);
                }
                if ( !(savedDeptIDs.contains(parentIDinput)) ){
                    parentIDField.setText("*No Such Department*");
                    return;
                }

                /**
                 * Valid Program and Parent Department ID entered, create and add new Program
                 */
                Item createdPrgrm = new Item("program", prgmNameField.getText(),
                        prgmIDinput,
                        prgmDescriptionField.getText(),
                        null,
                        parentIDinput
                );
                CSVTools.addChildIDtoParent(createdPrgrm);
                CSVTools.addItem(createdPrgrm);
                confirmationMessage.setText("Program Created");
                confirmationMessage.setVisible(true);
                return;
            }
        });
        prgmAddPanel.add(btnCreateProgram);

        confirmationMessage = new JLabel();
        confirmationMessage.setBounds(35, 470, 120, 30);
        confirmationMessage.setVisible(false);
        prgmAddPanel.add(confirmationMessage);
    }

}
