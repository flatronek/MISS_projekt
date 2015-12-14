package model;

import java.util.ArrayList;

public class Job {
	private ArrayList<Agent> workers;

	public Job() {
		workers = new ArrayList<>();
	}


	public void addAgent(Agent agent) {
		// TODO Auto-generated method stub
		workers.add(agent);
	}


	public ArrayList<Agent> getWorkers() {
		return workers;
	}


	public void setWorkers(ArrayList<Agent> workers) {
		this.workers = workers;
	}



}