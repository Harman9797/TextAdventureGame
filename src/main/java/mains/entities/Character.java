package mains.entities;

import java.util.ArrayList;

import mains.GameData;

public class Character implements GameObject {

	public String name;
	public String shortDescription;
	public String longDescription;
	public String location;
	public ArrayList<String> possessions;
	public ArrayList<String> states;
	public int healthpower;
	public Room room;

	public ArrayList<GameObject> objectsPossessed = new ArrayList<GameObject>();

	private boolean pickable;
	private int currentState;
	private ArrayList<String> visited = new ArrayList<String>();

	public Character() {

	}

	public Character(String name, String shortDescription, String longDescription, String location,
			ArrayList<String> possessions, int healthpower, boolean pickable, ArrayList<String> states, ArrayList<String> visited) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.location = location;
		this.possessions = possessions;
		this.healthpower = healthpower;
		this.pickable = pickable;
		this.states = states;
		this.visited = visited;

		for (Room r : GameData.rooms) {
			if (location.equalsIgnoreCase(r.getName()))
				room = r;
		}

		for (String item : possessions) {
			for (Item i : GameData.items) {
				if (item.equalsIgnoreCase(i.getName())) {
					objectsPossessed.add(i);
					break;
				}
			}
			System.out.println("Unknown item: " + item + ".");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
		
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		for (Room r : GameData.rooms) {
			if (location.equalsIgnoreCase(r.getName()))
				room = r;
		}
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room r) {
		this.room = r;
		this.location = r.getName();
	}

	public ArrayList<String> getPossessions() {
		return possessions;
	}

	public void setPossessions(ArrayList<String> possessions) {
		this.possessions = possessions;
		for (String item : possessions) {
			for (Item i : GameData.items) {
				if (item.equalsIgnoreCase(i.getName())) {
					objectsPossessed.add(i);
					break;
				}
			}
			System.out.println("Unknown item: " + item + ".");
		}
	}

	public void addPossessions(String possession, GameObject i2) {
		possessions.add(possession);
		objectsPossessed.add(i2);
	}

	public void removePossession(String possession, GameObject i2, Room room2) {
		objectsPossessed.remove(i2);
		possessions.remove(possession);
		i2.setRoom(room2);
	}
	
	public void removePossession(String possession) {
		GameObject ob = GameData.items.stream()
				.filter(c -> possession.equals(c.getName())).findFirst().orElse(null);
		objectsPossessed.remove(ob);
		possessions.remove(possession);
		ob.setRoom(GameData.getPlayer().getRoom());
	}

	public ArrayList<GameObject> getObjectsPossessed() {
		return objectsPossessed;
	}

	public void setObjectsPossessed(ArrayList<GameObject> objectsPossessed) {
		this.objectsPossessed = objectsPossessed;
	}

	public int getHealthpower() {
		return healthpower;
	}

	public void setHealthpower(int healthpower) {
		this.healthpower = healthpower;
	}

	public boolean isPickable() {
		return pickable;
	}

	public void setPickable(boolean pickable) {
		this.pickable = pickable;
	}

	public ArrayList<String> getStates() {
		return states;
	}

	public void setStates(ArrayList<String> states) {
		this.states = states;
		currentState = states.size() > 0 ? 0 : -1;
	}

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}

	public ArrayList<String> getVisited() {
		return visited;
	}

	public void setVisited(ArrayList<String> visited) {
		this.visited = visited;
	}

	
}
