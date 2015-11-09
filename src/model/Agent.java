package model;

import java.util.List;

import model.interests.Interest;

public class Agent {
	//aa
	private int age;
	private Location location;
	private List<Interest> interests;
	private List<Agent> friends;
	
	public List<Agent> getFriends() {
		return friends;
	}
	
	public void setFriends(List<Agent> friends) {
		this.friends = friends;
	}
	
	public void addFriend(Agent friend) {
		friends.add(friend);
	}
	
	public void removeFriend(Agent friend) {
		friends.remove(friend);
	}
	
	public List<Interest> getInterests() {
		return interests;
	}
	
	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
}
