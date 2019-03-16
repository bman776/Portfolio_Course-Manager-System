package GUI;

public class Item {
	protected String type; //type can have one of four values: "faculty", "department", "program", and "course"
	protected String name; //the name can be whatever the user desires
	protected int ID; //ID can be whatever 4 digit number the user desires
	protected String description; //Description can be a string of text detailing whatever the user wants
	protected int[] children; //contains the ID numbers of the children items which belong to the item
	protected int parent; //contains the ID number of the parent item which the item belongs to
	
	//Constructor
	public Item(String type, String name, int ID, String description, int[] children, int parent) {
		this.type = type;
		this.name = name;
		this.ID = ID;
		this.description = description;

		if (children != null){
			this.children = new int[children.length];
			for(int i = 0;i<children.length;i++) {
				this.children[i] = children[i];
			}
		} else
			this.children = null;

		this.parent = parent;
		if(type.equals("faculty"))
			this.parent = 65535;	//faculty's parentID is 65535.
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

	public String getDescription() { return description; }
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int[] getChildren(){ return children; }

	public void addChildren( int child ) {
		int[] tempChildren = this.children;
		this.children = new int [this.children.length+1];
		for (int i=0; i<this.children.length-1; i++)
			this.children[i] = tempChildren[i];
		this.children[this.children.length-1] = child;
	}

	public int getParent() { return parent; }

	public void setParent(int parent) { this.parent = parent; }
}
