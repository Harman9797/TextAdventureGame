package mains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mains.GameData;

public class Item implements GameObject {
	
    public String name;
    public String shortDescription;
    public String longDescription;
    public boolean possessed;
    public String location;
    
    @JsonIgnore
    private Room room;
    private boolean pickable;
    
    public Item() {
    	
    }

	public Item(String name, String shortDescription, String longDescription, boolean possessed, String location, boolean pickable) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.possessed = possessed;
		this.location = location;
		this.pickable = pickable;
		
		for (Room r: GameData.rooms) {
			if(location.equalsIgnoreCase(r.getName()))
				room = r;
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

	public boolean isPossessed() {
		return possessed;
	}

	public void setPossessed(boolean possessed) {
		this.possessed = possessed;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		for (Room r: GameData.rooms) {
			if(location.equalsIgnoreCase(r.getName()))
				room = r;
		}
	}
    
	@JsonIgnore
	public Room getRoom() {
		return this.room;
	}
	
	@JsonIgnore
	public void setRoom(Room r) {
		this.room = r;
		this.location = r.getName();
	}

	public boolean isPickable() {
		return pickable;
	}

	public void setPickable(boolean pickable) {
		this.pickable = pickable;
	}
    
	

}
