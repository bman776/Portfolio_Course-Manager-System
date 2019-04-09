package GUI;

import java.util.ArrayList;

public class Course extends Item {
	private double hours;
	private double credits;
	private String labinfo;
	private int[] prerequisites;
	private int[] antirequisites;

	//Constructor
	public Course(String name, int ID, String description, int parent, double hours, double credits, String labinfo,
				  int[] prerequisites, int[] antirequisites) {
		super("course", name, ID, description, new int[0], parent); //we have a length 0 int array here.
		this.hours = hours;
		this.credits = credits;
		this.labinfo = labinfo;
		this.prerequisites = prerequisites;
		this.antirequisites = antirequisites;
	}

	//Getters and Setters
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

	public void addPrerequisites(int prereqID){
		ArrayList<Integer> tempPrereqs = new ArrayList<>();
		for(int i=0; i<this.prerequisites.length; i++)
			tempPrereqs.add(this.prerequisites[i]);
		tempPrereqs.add(prereqID);
		this.prerequisites = new int [this.prerequisites.length+1];
		for (int i=0; i<this.prerequisites.length; i++)
			this.prerequisites[i] = tempPrereqs.get(i);
	}

	public void removePrerequisites(int prereqID){
		ArrayList<Integer> newPrereqs = new ArrayList<>();
		for(int i=0; i<this.prerequisites.length; i++){
			if (this.prerequisites[i] == prereqID){
				continue;
			} else {
				newPrereqs.add(this.prerequisites[i]);
			}
		}
		this.prerequisites = new int[newPrereqs.size()];
		for (int i=0; i<this.prerequisites.length; i++){
			this.prerequisites[i] = newPrereqs.get(i);
		}
	}

	public void setPrerequisites(int[] prerequisites) {
		this.prerequisites = prerequisites;
	}

	public int[] getAntirequisites() {
		return antirequisites;
	}

	public void addAntirequisites(int antireqID) {
		ArrayList<Integer> tempAntireqs = new ArrayList<>();
		for(int i=0; i<this.antirequisites.length; i++)
			tempAntireqs.add(this.antirequisites[i]);
		tempAntireqs.add(antireqID);
		this.antirequisites = new int [this.antirequisites.length+1];
		for (int i=0; i<this.antirequisites.length; i++)
			this.antirequisites[i] = tempAntireqs.get(i);
	}

	public void removeAntirequisites(int antireqID) {
		ArrayList<Integer> newAntireqs = new ArrayList<>();
		for(int i=0; i<this.antirequisites.length; i++){
			if (this.antirequisites[i] == antireqID){
				continue;
			} else {
				newAntireqs.add(this.antirequisites[i]);
			}
		}
		this.antirequisites = new int[newAntireqs.size()];
		for (int i=0; i<this.antirequisites.length; i++){
			this.antirequisites[i] = newAntireqs.get(i);
		}
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
