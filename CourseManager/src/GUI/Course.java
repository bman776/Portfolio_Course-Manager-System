public class Course extends Item {
	private double hours;
	private double credits;
	private String labinfo;
	private int[] prerequisites;
	private int[] antirequisites;

	//	String type, String name, int ID, String description
	public Course(String name, int ID, String letterID, String description, double hours, double credits, String labinfo,
				  int[] prerequisites, int[] antirequisites) {
		super("Course", name, ID, letterID, description, null);
		this.hours = hours;
		this.credits = credits;
		this.labinfo = labinfo;
		this.prerequisites = prerequisites;
		this.antirequisites = antirequisites;
	}

	public double getHours() {
		return hours;
	}


	public void setHours(double hours) {
		this.hours = hours;
	}


	public double getCredits() {
		return credits;
	}


	public void setCredits(double credits) {
		this.credits = credits;
	}


	public String getLabinfo() {
		return labinfo;
	}


	public void setLabinfo(String labinfo) {
		this.labinfo = labinfo;
	}


	public int[] getPrerequisites() {
		return prerequisites;
	}


	public void setPrerequisites(int[] prerequisites) {
		this.prerequisites = prerequisites;
	}


	public int[] getAntirequisites() {
		return antirequisites;
	}


	public void setAntirequisites(int[] antirequisites) {
		this.antirequisites = antirequisites;
	}


	@Override
	public String toString() {
		return "Course [hours=" + hours + ", credits=" + credits + ", labinfo=" + labinfo + ", prerequisites="
				+ prerequisites + ", antirequisites=" + antirequisites + "]";
	}


}
