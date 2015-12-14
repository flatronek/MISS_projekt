package model;

import java.util.ArrayList;

public class School {
	private ArrayList<Agent> students;

	public School() {
		students = new ArrayList<>();
	}


	public void addAgent(Agent agent) {
		// TODO Auto-generated method stub
		getStudents().add(agent);
	}


	public ArrayList<Agent> getStudents() {
		return students;
	}


	public void setStudents(ArrayList<Agent> students) {
		this.students = students;
	}

}