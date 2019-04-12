package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * This class is to read and write CSV files. 
 * The CSV files can be read and write using text editor directly, but we suggest to use 
 * this tool. Because editing directly may destroy the form of csv files. 
 * @author Brett Gattinger, Shiwei Sun
 * 
 */

public class CSVTools {

    /**
     * Important note: The Document of a JTextArea (or JTextPane) always stores the newline string as "\n" (regardless of system)
     * So we only need to worry about system dependent line separators when writing to or reading from the csv files
     */

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private static final String system_ls = System.lineSeparator();

    // CSVTool constants to be used in conjunction with CSVTool methods
    public static final String typeF = "faculty";
    public static final String typeD = "department";
    public static final String typeP = "program";
    public static final String typeC = "course";

    // CSVTool constants for file path names
    public static final String CSV_C = "./data/created/";
    public static final String CSV_D = "./data/deleted/";

    /* CSVTool constants for specifying whether CSVTool methods are reading from/writing to files in created or deleted
       CSV file folders */
    public static final boolean toCreated = true;
    public static final boolean toDeleted = false;
    public static final boolean fromCreated = true;
    public static final boolean fromDeleted = false;


    /**
    * This function is used to find one item by its name. It will read the csv file, and return
    * the corresponding item instance.
    * @param type This indicate the type of item you want to find. for example, "department" 
    * @param name This is the name of the item you want to find.
    * @return The instance of the item we want to find.
    */

    public static Item findItem(String type, String name, boolean source) {

        String csvFile;
        if (source==fromCreated)
            csvFile = CSV_C+type+".csv";
        else
            csvFile = CSV_D+type+".csv";

        Item result = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(csvFile));
            scanner.nextLine();scanner.nextLine();  //delete first two lines
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                if(line.get(1).equals(name)){
                    int tempChildren[] = new int[line.size()-4];
                    for(int i=4, j=0;i < line.size();i++, j++) {
                        tempChildren[j] = Integer.parseInt(line.get(i));
                    }
                    String restoredDescription = line.get(2).replace("@@@", "\n");
                    result = new Item(type, line.get(1), Integer.parseInt(line.get(0)), restoredDescription, tempChildren, Integer.parseInt(line.get(3)));
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner) {
                    scanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
     /**
     * This function is used to find one item by its ID. It will read the csv file, and return
    * the corresponding item instance.
    * @param type This indicate the type of item you want to find. for example, "department" 
    * @param ID This is the name of the item you want to find.
     * @return The instance of the item we want to find.
    */

    public static Item findItem(String type, int ID, boolean source) {

        String csvFile;
        if (source==fromCreated)
            csvFile = CSV_C+type+".csv";
        else
            csvFile = CSV_D+type+".csv";

        Item result = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(csvFile));
            scanner.nextLine();scanner.nextLine();  //delete first two lines
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                if(Integer.parseInt(line.get(0))==ID){
                    int tempChildren[] = new int[line.size()-4];
                    for(int i=4, j=0;i < line.size();i++, j++) {
                        tempChildren[j] = Integer.parseInt(line.get(i));
                    }
                    String restoredDescription = line.get(2).replace("@@@", "\n");
                    result = new Item(type, line.get(1), Integer.parseInt(line.get(0)), restoredDescription, tempChildren, Integer.parseInt(line.get(3)));
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner) {
                    scanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * This function is to find the course by its name. It will return the instance of
     * corresponding course.
     * @param name The name of the course you want to find.
     * @return The instance of that course.
     */

    public static Course findCourse(String name, boolean source) {

        String csvFile;
        if (source==fromCreated)
            csvFile = CSV_C+typeC+".csv";
        else
            csvFile = CSV_D+typeC+".csv";

        Course result = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(csvFile));
            scanner.nextLine();scanner.nextLine();  //delete first two lines
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                if(line.get(1).equals(name)){
                    int prerequisite[] = new int[Integer.parseInt(line.get(3))];
                    int antirequisite[] = new int[Integer.parseInt(line.get(4))];
                    for(int i=9, j=0;i < line.size()-antirequisite.length;i++, j++) {
                        prerequisite[j] = Integer.parseInt(line.get(i));
                    }
                    for(int i=9+prerequisite.length, j=0;i < line.size();i++, j++) {
                        antirequisite[j] = Integer.parseInt(line.get(i));
                    }
                    String restoredDescription = line.get(2).replace("@@@", "\n");
                    String restoredLabInfo = line.get(5).replace("@@@", "\n");
                    result = new Course(line.get(1), Integer.parseInt(line.get(0)), restoredDescription, Integer.parseInt(line.get(8)),
                            Double.parseDouble(line.get(6)), Double.parseDouble(line.get(7)), restoredLabInfo, prerequisite, antirequisite);
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner) {
                    scanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * This function is to find the course by its ID. It will read the csv file first and 
     * return the instance of corresponding course.
     * @param ID The name of the course you want to find.
     * @return The instance of that course.
     */
    
    public static Course findCourse(int ID, boolean source) {

        String csvFile;
        if (source==fromCreated)
            csvFile = CSV_C+typeC+".csv";
        else
            csvFile = CSV_D+typeC+".csv";

        Course result = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(csvFile));
            scanner.nextLine();scanner.nextLine();  //delete first two lines
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                if(Integer.parseInt(line.get(0))==ID){
                    int prerequisite[] = new int[Integer.parseInt(line.get(3))];
                    int antirequisite[] = new int[Integer.parseInt(line.get(4))];
                    for(int i=9, j=0;i < line.size()-antirequisite.length;i++, j++) {
                        prerequisite[j] = Integer.parseInt(line.get(i));
                    }
                    for(int i=9+prerequisite.length,j=0;i<line.size();i++, j++) {
                        antirequisite[j] = Integer.parseInt(line.get(i));
                    }
                    String restoredDescription = line.get(2).replace("@@@", "\n");
                    String restoredLabInfo = line.get(5).replace("@@@", "\n");
                    result = new Course(line.get(1), Integer.parseInt(line.get(0)), restoredDescription, Integer.parseInt(line.get(8)),
                            Double.parseDouble(line.get(6)), Double.parseDouble(line.get(7)), restoredLabInfo, prerequisite, antirequisite);
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner) {
                    scanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * This item will take an item parameter, and it will write this item to the corresponding 
     * csv file. For example, if the type of the para is department, this function will write an
     * department details in department.csv. 
     * @param newitem The instance of item you want to add to the corresponding csv file.
     */

    public static void addItem(Item newitem, boolean destination) {

        String csvFile;
        if (destination==toCreated)
            csvFile = CSV_C+newitem.getType()+".csv";
        else
            csvFile = CSV_D+newitem.getType()+".csv";

        Scanner scanner = null;
        File fileio = null;
        File filepast = new File(csvFile);
        FileOutputStream writer = null;
        try {
            scanner = new Scanner(filepast);
            fileio = new File("./data/"+newitem.type+".temp");
            fileio.createNewFile();
            writer = new FileOutputStream(fileio);
            writer.write((Integer.parseInt(scanner.nextLine())+1+system_ls).getBytes());
            writer.write((scanner.nextLine()+system_ls).getBytes());
            while(true) {
                if(scanner.hasNext()) {
                    writer.write((scanner.nextLine()+system_ls).getBytes());
                }
                else break;
            }

            String csvConvertedDescription = newitem.getDescription().replace("\n", "@@@");

            writer.write(("\""+(newitem.getID())+"\",\""+newitem.getName()+"\",\""+csvConvertedDescription+"\",\""+newitem.getParent()+"\"").getBytes());
            if (newitem.children != null) {
                for (int i = 0; i < newitem.getChildren().length; i++) {
                    writer.write((",\"" + newitem.children[i] + "\"").getBytes());
                }
            }
            writer.write((system_ls).getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner && null != writer) {
                    scanner.close();
                    writer.close();
                    filepast.delete();
                    fileio.renameTo(filepast);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
     /**
     * This item will take an course parameter, and it will write this item to the corresponding 
     * csv file. For example, 
     * @param newcourse The instance of item you want to add to course.
     */

    public static void addCourse(Course newcourse, boolean destination) {

        String csvFile;
        if (destination==toCreated)
            csvFile = CSV_C+typeC+".csv";
        else
            csvFile = CSV_D+typeC+".csv";

        Scanner scanner = null;
        File fileio = null;
        File filepast = new File(csvFile);
        FileOutputStream writer = null;
        try {
            scanner = new Scanner(filepast);
            fileio = new File("./data/course.temp");
            fileio.createNewFile();
            writer = new FileOutputStream(fileio);
            writer.write((Integer.parseInt(scanner.nextLine())+1+system_ls).getBytes());
            writer.write((scanner.nextLine()+system_ls).getBytes());
            while(true) {
                if(scanner.hasNext()) {
                    writer.write((scanner.nextLine()+system_ls).getBytes());
                }
                else break;
            }

            String csvConvertedDescription = newcourse.getDescription().replace("\n", "@@@");
            String csvConvertedLabInfo = newcourse.getLabinfo().replace("\n", "@@@");


            if ( (newcourse.getPrerequisites() == null) && (newcourse.getAntirequisites() == null) ){
                writer.write(("\""+(newcourse.getID())+"\",\""+newcourse.getName()+"\",\""+csvConvertedDescription
                        +"\",\""+"0"+"\",\""+"0"+"\",\""
                        +csvConvertedLabInfo+"\",\""+newcourse.getHours()+"\",\""+newcourse.getCredits()+"\",\""
                        +newcourse.getParent()+"\"").getBytes());
            } else if ( (newcourse.getPrerequisites() != null) && (newcourse.getAntirequisites() == null) ) {
                writer.write(("\""+(newcourse.getID())+"\",\""+newcourse.getName()+"\",\""+csvConvertedDescription+"\",\""
                        +newcourse.getPrerequisites().length+"\",\""+"0"+"\",\""
                        +csvConvertedLabInfo+"\",\""+newcourse.getHours()+"\",\""+newcourse.getCredits()+"\",\""
                        +newcourse.getParent()+"\"").getBytes());
                for(int i=0;i<newcourse.getPrerequisites().length;i++) {
                    writer.write((",\""+newcourse.getPrerequisites()[i]+"\"").getBytes());
                }
            } else if ( (newcourse.getPrerequisites() == null) && (newcourse.getAntirequisites() != null) ) {
                writer.write(("\""+(newcourse.getID())+"\",\""+newcourse.getName()+"\",\""+csvConvertedDescription+"\",\""
                        +"0"+"\",\""+newcourse.getAntirequisites().length+"\",\""
                        +csvConvertedLabInfo+"\",\""+newcourse.getHours()+"\",\""+newcourse.getCredits()+"\",\""
                        +newcourse.getParent()+"\"").getBytes());
                for(int i=0;i<newcourse.getAntirequisites().length;i++) {
                    writer.write((",\""+newcourse.getAntirequisites()[i]+"\"").getBytes());
                }
            } else {
                writer.write(("\""+(newcourse.getID())+"\",\""+newcourse.getName()+"\",\""+csvConvertedDescription+"\",\""
                        +newcourse.getPrerequisites().length+"\",\""+newcourse.getAntirequisites().length+"\",\""
                        +csvConvertedLabInfo+"\",\""+newcourse.getHours()+"\",\""+newcourse.getCredits()+"\",\""
                        +newcourse.getParent()+"\"").getBytes());
                for(int i=0;i<newcourse.getPrerequisites().length;i++) {
                    writer.write((",\""+newcourse.getPrerequisites()[i]+"\"").getBytes());
                }
                for(int i=0;i<newcourse.getAntirequisites().length;i++) {
                    writer.write((",\""+newcourse.getAntirequisites()[i]+"\"").getBytes());
                }
            }
            writer.write((system_ls).getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner && null != writer) {
                    scanner.close();
                    writer.close();
                    filepast.delete();
                    fileio.renameTo(filepast);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void changeItemIDinSystem(int oldID, int newID, String itemType){

        //Changing the ID of a Faculty means we need to change the parent ID of all child Departments
        if (itemType.equals(typeF)){
            int[] savedDepts = getIDList(typeD, fromCreated);
            for(int i=0; i<savedDepts.length; i++){
                Item childDept = findItem(typeD, savedDepts[i], fromCreated);
                if (childDept.getParent() == oldID){
                    removeData(childDept);
                    childDept.setParent(newID);
                    addItem(childDept, toCreated);
                }
            }
        }

        // Changing the ID of a Department means we need to change the parent ID of all child programs
        // and the childID of the parent Faculty
        else if (itemType.equals(typeD)){
            int[] savedPrgms = getIDList(typeP, fromCreated);
            for(int i=0; i<savedPrgms.length; i++){
                Item childPrgm = findItem(typeP, savedPrgms[i], fromCreated);
                if (childPrgm.getParent() == oldID){
                    removeData(childPrgm);
                    childPrgm.setParent(newID);
                    addItem(childPrgm, toCreated);
                }
            }
            Item parentFaculty = findItem(typeF, findItem(typeD, oldID, fromCreated).getParent(), fromCreated);
            removeData(parentFaculty);
            parentFaculty.removeChild(oldID);
            parentFaculty.addChild(newID);
            addItem(parentFaculty, toCreated);
        }

        // Changing the ID of a Program means we need to change the parent ID of all child courses
        // and the child ID of the parent Department
        else if (itemType.equals(typeP)){
            int[] savedCourses = getIDList(typeC, fromCreated);
            for (int i=0; i<savedCourses.length; i++){
                Course childCourse = findCourse(savedCourses[i], fromCreated);
                if (childCourse.getParent() == oldID){
                    removeData(childCourse);
                    childCourse.setParent(newID);
                    addCourse(childCourse, toCreated);
                }
            }
            Item parentDept = findItem(typeD, findItem(typeP, oldID, fromCreated).getParent(), fromCreated);
            removeData(parentDept);
            parentDept.removeChild(oldID);
            parentDept.addChild(newID);
            addItem(parentDept, toCreated);
        }

        // Changing the ID of a course means we need to change the prereq or anitreq ID's of all dependent courses
        // and the child ID of the parent Program
        else if (itemType.equals(typeC)){

            int[] savedCourses = getIDList(typeC, fromCreated);

            for (int i=0; i<savedCourses.length; i++){

                Course courseI = findCourse(savedCourses[i], fromCreated);

                for (int j=0; j<courseI.getPrerequisites().length; j++){
                    if (courseI.getPrerequisites()[j] == oldID){
                        removeData(courseI);
                        courseI.removePrerequisites(oldID);
                        courseI.addPrerequisites(newID);
                        addCourse(courseI, toCreated);
                    }
                }
                for (int j=0; j<courseI.getAntirequisites().length; j++){
                    if (courseI.getAntirequisites()[j] == oldID){
                        removeData(courseI);
                        courseI.removeAntirequisites(oldID);
                        courseI.addAntirequisites(newID);
                        addCourse(courseI, toCreated);
                    }
                }
            }
            Item parentPrgm = findItem(typeP, findCourse(oldID, fromCreated).getParent(), fromCreated);
            removeData(parentPrgm);
            parentPrgm.removeChild(oldID);
            parentPrgm.addChild(newID);
            addItem(parentPrgm, toCreated);
        }
    }

    
    public static void addChildIDtoParent(Item child){
        Item parentItem = null;
        if (child.type.equals(typeC)) {
            parentItem = findItem(typeP, child.parent, toCreated);
            removeData(parentItem);
            parentItem.addChild(child.ID);
            addItem(parentItem, toCreated);
        } else if (child.type.equals(typeP)){
            parentItem = findItem(typeD, child.parent, toCreated);
            removeData(parentItem);
            parentItem.addChild(child.ID);
            addItem(parentItem, toCreated);
        } else if (child.type.equals(typeD)){
            parentItem = findItem(typeF, child.parent, toCreated);
            removeData(parentItem);
            parentItem.addChild(child.ID);
            addItem(parentItem, toCreated);
        }
    }

    public static void removeChildIDfromParent(Item child){
        Item parentItem = null;
        if (child.type.equals(typeC)) {
            parentItem = findItem(typeP, child.parent, toCreated);
            removeData(parentItem);
            parentItem.removeChild(child.ID);
            addItem(parentItem, toCreated);
        } else if (child.type.equals(typeP)){
            parentItem = findItem(typeD, child.parent, toCreated);
            removeData(parentItem);
            parentItem.removeChild(child.ID);
            addItem(parentItem, toCreated);
        } else if (child.type.equals(typeD)){
            parentItem = findItem(typeF, child.parent, toCreated);
            removeData(parentItem);
            parentItem.removeChild(child.ID);
            addItem(parentItem, toCreated);
        }
    }

    public static void addPrereqToCourse(Course c, int p){
        removeData(c);
        c.addPrerequisites(p);
        addCourse(c, toCreated);
    }

    public static void removePrereqFromCourse(Course c, int p){
        removeData(c);
        c.removePrerequisites(p);
        addCourse(c, toCreated);
    }

    public static void addAntireqToCourse(Course c, int a){
        removeData(c);
        c.addAntirequisites(a);
        addCourse(c, toCreated);
    }

    public static void removeAntireqFromCourse(Course c, int a){
        removeData(c);
        c.removeAntirequisites(a);
        addCourse(c, toCreated);
    }

    public static void removeData(Item deleteditem) {
    	String csvFile = CSV_C+deleteditem.type+".csv";
    	Scanner scanner = null;
    	File fileio = null;
    	File filepast = new File(csvFile);
    	FileOutputStream writer = null;
    	try {
    		scanner = new Scanner(filepast);
    		fileio = new File(CSV_C+deleteditem.type+".temp");
    		fileio.createNewFile();
    		String tempstr = null;
    		writer = new FileOutputStream(fileio);
    		writer.write(((Integer.parseInt(scanner.nextLine())-1)+system_ls).getBytes());
    		writer.write((scanner.nextLine()+system_ls).getBytes());
    		while(true) {
    			if(scanner.hasNext()) {
    				tempstr = scanner.nextLine();
        			List<String> line = parseLine(tempstr);
        			if(Integer.parseInt(line.get(0))==deleteditem.getID()) {
        				continue;
        			}
        			writer.write((tempstr+system_ls).getBytes());
    			} else break;
    		}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner && null != writer) {
                    scanner.close();
                    writer.close();
                    filepast.delete();
                    fileio.renameTo(filepast);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int[] getIDList(String type, boolean source) {

        String csvFile;
        if (source==fromCreated)
            csvFile = CSV_C+type+".csv";
        else
            csvFile = CSV_D+type+".csv";

        int[] list = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(csvFile));
            int num = Integer.parseInt(scanner.nextLine()); //get Number of items saved in csv file from first line of csv file
            scanner.nextLine();                             //Skip the 2nd csv format line
            list = new int[num];
            for(int count = 0;count < num; count++) {
                List<String> line = parseLine(scanner.nextLine());
                list[count] = Integer.parseInt(line.get(0));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner) {
                    scanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String[] getNameList(String type, boolean source) {

        String csvFile;
        if (source==fromCreated)
            csvFile = CSV_C+type+".csv";
        else
            csvFile = CSV_D+type+".csv";

        String[] list = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(csvFile));
            int num = Integer.parseInt(scanner.nextLine());
            scanner.nextLine();
            list = new String[num];
            for(int count = 0;count < num; count++) {
                List<String> line = parseLine(scanner.nextLine());
                list[count] = (line.get(1));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner) {
                    scanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean fileIsEmpty(String type, boolean source){

        String csvFile;
        if (source==fromCreated)
            csvFile = CSV_C+type+".csv";
        else
            csvFile = CSV_D+type+".csv";

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(csvFile));
            if (Integer.parseInt(scanner.nextLine()) == 0 ){
                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (null != scanner) {
                    scanner.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false; //returns false by default
    }

    public static void deleteFaculty(Item deletedFaculty){

        //delete all departments under faculty
        try {
            int[] deletedFacultyDepartments = deletedFaculty.getChildren();
            for (int i=0; i<deletedFacultyDepartments.length; i++){
                deleteDepartment(findItem(typeD, deletedFacultyDepartments[i], fromCreated));
            }
        } catch (NullPointerException e){
            // deleted Faculty has no child Departments
        }

        //write deleted faculty to deleted
        addItem(deletedFaculty, toDeleted);

        //delete faculty from created
        removeData(deletedFaculty);
    }

    public static void deleteDepartment(Item deletedDepartment){

        //delete all programs under department
        try {
            int[] deletedDepartmentPrograms = deletedDepartment.getChildren();
            for (int i=0; i<deletedDepartmentPrograms.length; i++){
                deleteProgram(findItem(typeP, deletedDepartmentPrograms[i], fromCreated));
            }
        } catch (NullPointerException e) {
            // deleted Department has no child Programs
            return;
        }

        //remove deleted department ID from parent Faculty child list
        removeChildIDfromParent(deletedDepartment);

        //write deleted department to deleted
        addItem(deletedDepartment, toDeleted);

        //delete department from created
        removeData(deletedDepartment);
    }

    public static void deleteProgram(Item deletedProgram){

        //delete all courses under program
        try{
            int[] deletedProgramCourses = deletedProgram.getChildren();
            for (int i=0; i<deletedProgramCourses.length; i++){
                deleteCourse(findCourse(deletedProgramCourses[i], fromCreated));
            }
        } catch (NullPointerException e){
            // deleted Program has no child Coureses
            return;
        }

        //remove deleted program ID from parent Department child list
        removeChildIDfromParent(deletedProgram);

        //write deleted program to deleted
        addItem(deletedProgram, toDeleted);

        //delete program from created
        removeData(deletedProgram);
    }

    public static void deleteCourse(Course deletedCourse){

        //get all courses
        int[] allCourseIDs = getIDList(typeC,fromCreated);

        // Go through all saved courses and remove deletedCourse from their prereqs or antireqs
        for (int i=0; i<allCourseIDs.length; i++){

            // course0 is course currently being examined
            Course course0 = findCourse(allCourseIDs[i], fromCreated);

            // Go through course0's prerequisites to see if it has deleted course as a prereq
            try {
                for (int j=0; j<course0.getPrerequisites().length; j++){
                    if (deletedCourse.getID() == course0.getPrerequisites()[j]){
                        // course0 has deletedCourse as prereq so we remove that
                        removePrereqFromCourse(course0, deletedCourse.getID());
                    }
                }
            } catch (NullPointerException e){
                // course0 has no prerequisites
            }

            // Go through course0's antirequisites to see if it has deleted course as an antireq
            try {
                for (int j=0; j<course0.getAntirequisites().length; j++){
                    if (deletedCourse.getID() ==course0.getAntirequisites()[j]){
                        removeAntireqFromCourse(course0, deletedCourse.getID());
                    }
                }
            } catch (NullPointerException e){
                // course0 has no antirequisites
            }

        }

        //remove course ID from parent Program child list
        removeChildIDfromParent(deletedCourse);

        //write deleted course to deleted
        addCourse(deletedCourse, toDeleted);

        //now delete deletedCourse from created
        removeData(deletedCourse);
    }


    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null ) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }



}
