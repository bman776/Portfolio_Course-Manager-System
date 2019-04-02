package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVTools {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static Item findItem(String type, String name) {
    	String csvFile = "CourseManager/data/"+type+".csv";
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
                    String restoredDescription = line.get(2).replaceAll("@@@", System.lineSeparator());
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
    
    public static Item findItem(String type, int ID) {
    	String csvFile = "CourseManager/data/"+type+".csv";
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
                    String restoredDescription = line.get(2).replaceAll("@@@", System.lineSeparator());
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

    public static Course findCourse(String name) {
    	String csvFile = "CourseManager/data/course.csv";
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
                    String restoredDescription = line.get(2).replaceAll("@@@", System.lineSeparator());
                    String restoredLabInfo = line.get(5).replaceAll("@@@", System.lineSeparator());
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
    
    public static Course findCourse(int ID) {
    	String csvFile = "CourseManager/data/course.csv";
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
                    String restoredDescription = line.get(2).replaceAll("@@@", System.lineSeparator());
                    String restoredLabInfo = line.get(5).replaceAll("@@@", System.lineSeparator());
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
    
    public static void addItem(Item newitem) {
    	String csvFile = "CourseManager/data/"+newitem.type+".csv";
    	Scanner scanner = null;
    	File fileio = null;
    	File filepast = new File(csvFile);
    	FileOutputStream writer = null;
    	try {
    		scanner = new Scanner(filepast);
    		fileio = new File("CourseManager/data/"+newitem.type+".temp");
    		fileio.createNewFile();
    		writer = new FileOutputStream(fileio);
    		writer.write((Integer.parseInt(scanner.nextLine())+1+"\r\n").getBytes());
    		writer.write((scanner.nextLine()+"\r\n").getBytes());
    		while(true) {
    			if(scanner.hasNext()) {
    				writer.write((scanner.nextLine()+"\r\n").getBytes());
    			}
    			else break;
    		}

    		String csvConvertedDescription = newitem.getDescription().replaceAll("(\\r|\\n|\\r\n)", "@@@");

    		writer.write(("\""+(newitem.getID())+"\",\""+newitem.getName()+"\",\""+csvConvertedDescription+"\",\""+newitem.getParent()+"\"").getBytes());
    		if (newitem.children != null) {
                for (int i = 0; i < newitem.getChildren().length; i++) {
                    writer.write((",\"" + newitem.children[i] + "\"").getBytes());
                }
            }
    		writer.write(("\r\n").getBytes());
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
    
    public static void addCourse(Course newcourse) {
    	String csvFile = "CourseManager/data/course.csv";
    	Scanner scanner = null;
    	File fileio = null;
    	File filepast = new File(csvFile);
    	FileOutputStream writer = null;
    	try {
    		scanner = new Scanner(filepast);
    		fileio = new File("CourseManager/data/course.temp");
    		fileio.createNewFile();
    		writer = new FileOutputStream(fileio);
    		writer.write((Integer.parseInt(scanner.nextLine())+1+"\r\n").getBytes());
    		writer.write((scanner.nextLine()+"\r\n").getBytes());
    		while(true) {
    			if(scanner.hasNext()) {
    				writer.write((scanner.nextLine()+"\r\n").getBytes());
    			}
    			else break;
    		}

    		String csvConvertedDescription = newcourse.getDescription().replaceAll("(\\r|\\n|\\r\n)", "@@@");
    		String csvConvertedLabInfo = newcourse.getLabinfo().replaceAll("(\\r|\\n|\\r\n)", "@@@");


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
    		writer.write(("\r\n").getBytes());
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
    
    public static void addChildIDtoParent(Item child){
        Item parentItem = null;
        if (child.type.equals("course")) {
            parentItem = findItem("program", child.parent);
            removeData(parentItem);
            parentItem.addChildren(child.ID);
            addItem(parentItem);
        } else if (child.type.equals("program")){
            parentItem = findItem("department", child.parent);
            removeData(parentItem);
            parentItem.addChildren(child.ID);
            addItem(parentItem);
        } else if (child.type.equals("department")){
            parentItem = findItem("faculty", child.parent);
            removeData(parentItem);
            parentItem.addChildren(child.ID);
            addItem(parentItem);
        }
    }

    public static void removeData(Item deleteditem) {
    	String csvFile = "CourseManager/data/"+deleteditem.type+".csv";
    	Scanner scanner = null;
    	File fileio = null;
    	File filepast = new File(csvFile);
    	FileOutputStream writer = null;
    	try {
    		scanner = new Scanner(filepast);
    		fileio = new File("CourseManager/data/"+deleteditem.type+".temp");
    		fileio.createNewFile();
    		String tempstr = null;
    		writer = new FileOutputStream(fileio);
    		writer.write(((Integer.parseInt(scanner.nextLine())-1)+"\r\n").getBytes());
    		writer.write((scanner.nextLine()+"\r\n").getBytes());
    		while(true) {
    			if(scanner.hasNext()) {
    				tempstr = scanner.nextLine();
        			List<String> line = parseLine(tempstr);
        			if(Integer.parseInt(line.get(0))==deleteditem.getID()) {
        				continue;
        			}
        			writer.write((tempstr+"\r\n").getBytes());
    			}
    			else break;
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
    
    public static int[] getIDList(String type) {
    	int[] list = null;
    	String csvFile = "CourseManager/data/"+type+".csv";
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

    public static String[] getNameList(String type) {
        String[] list = null;
        String csvFile = "CourseManager/data/"+type+".csv";
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

    public static boolean fileIsEmpty(String type){
        String csvFile = "CourseManager/data/"+type+".csv";
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
