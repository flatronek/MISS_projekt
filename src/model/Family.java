package model;

import java.util.ArrayList;

public class Family {
	private ArrayList<Agent> family;

	public Family() {
		family = new ArrayList<>();
	}

	public boolean isFull() {
		if (family.size() > 8)
			return true;
		return false;
	}

	public ArrayList<Agent> getFamily() {
		return family;
	}

	public void setFamily(ArrayList<Agent> family) {
		this.family = family;
	}

	public void addAgent(Agent agent) {
		// TODO Auto-generated method stub
		family.add(agent);
	}

}
