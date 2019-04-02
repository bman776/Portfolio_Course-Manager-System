package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramInfoPage extends JFrame {

    private List<Item> childCourses;
    private String[] childCourseNameIDs;

    private JPanel outerPanel;
    private JPanel innerContentPanel;
    private JLabel programName;
    private JLabel programID;
    private JLabel parentDept;
    private JLabel descriptionLabel;
    private JTextArea descriptionField;
    private JScrollPane descriptionPane;
    private JLabel coursesLabel;
    private JList<String> coursesList;
    private JScrollPane coursePane;
    private JButton btnEdit;

    public ProgramInfoPage(Item selectedProgram){

        // Get Child Departments
        childCourses = new ArrayList<Item>();
        for (int i=0; i<CSVTools.getIDList("course").length; i++){
            Course courseItem = CSVTools.findCourse( CSVTools.getIDList("course")[i] );
            if ( courseItem.getParent() == selectedProgram.getID() ){
                childCourses.add(courseItem);
            }
        }
        childCourseNameIDs = new String[childCourses.size()];
        for (int i = 0; i< childCourses.size(); i++){
            childCourseNameIDs[i] = childCourses.get(i).getName()+"   "+ childCourses.get(i).getID();
        }

        setResizable(false);
        setTitle("Program Info Page");
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

        programName = new JLabel("Program: "+selectedProgram.getName());
        programName.setBounds(10, 10, 545, 20);
        programName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(programName);

        programID = new JLabel("ID: "+selectedProgram.getID());
        programID.setBounds(10, 40, 545, 20);
        programID.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(programID);

        parentDept = new JLabel("Parent Dept: "+CSVTools.findItem("department", selectedProgram.getParent()).getName());
        parentDept.setBounds(10, 70, 545, 20);
        parentDept.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(parentDept);

        descriptionLabel = new JLabel("Program Description:");
        descriptionLabel.setBounds(10,100, 200, 20 );
        descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(descriptionLabel);

        descriptionField = new JTextArea(selectedProgram.getDescription());
        descriptionField.setBounds(10, 130, 285, 230);
        descriptionField.setEditable(false);
        descriptionPane = new JScrollPane(descriptionField);
        descriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setBounds(descriptionField.getBounds());
        innerContentPanel.add(descriptionPane);

        coursesLabel = new JLabel("Program Courses:");
        coursesLabel.setBounds(350,80, 150, 20 );
        coursesLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        innerContentPanel.add(coursesLabel);

        coursesList = new JList<String>(childCourseNameIDs);
        coursesList.setBounds(315, 110, 240, 230);
        coursePane = new JScrollPane(coursesList);
        coursePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        coursePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        coursePane.setBounds(coursesList.getBounds());
        innerContentPanel.add(coursePane);
        coursesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /**
                 * IF statement prevents the infamous double fire event associated with JLists selections
                 * (when made with mouse) and makes the selection in the JList re-selectable to the user
                 */
                if (coursesList.getSelectedValue() != null){
                    int courseID = Integer.parseInt(coursesList.getSelectedValue().split("   ")[1]);
                    CourseInfoPage selectedCourse = new CourseInfoPage(CSVTools.findCourse(courseID));
                    coursesList.clearSelection();
                    selectedCourse.setVisible(true);
                }
                return;
            }
        });

        btnEdit = new JButton("Edit Faculty");
        btnEdit.setBounds(10 , 380, 130, 25);
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        innerContentPanel.add(btnEdit);

    }


}

