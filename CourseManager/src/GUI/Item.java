package GUI;

public class Item {
	protected String type;
	protected String name;
	protected int ID;
	protected String letterID;
	protected String description;
	protected int[] children;
	
	
	public Item(String type, String name, int ID, String letterID, String description, int[] children) {
		this.type = type;
		this.name = name;
		this.ID = ID;
		this.letterID = letterID;
		this.description = description;
		this.children = new int[children.length];
		for(int i = 0;i<children.length;i++) {
			this.children[i] = children[i];
		}
	}

	//Getters and Setters
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLetterID() { return letterID; }

	public void setLetterID(String letterID) { this.letterID = letterID; }
	
	public String getDescription() { return description; }
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int[] getChildren(){ return children; }

	public void setChildren( int[] children) { this.children = children; }
	
}
