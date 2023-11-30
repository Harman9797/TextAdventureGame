package mains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Room implements GameObject {

	public String name;
	public boolean access;
	public String shortDescription;
	public String longDescription;
	
	@JsonIgnore
	public Room roomToWest;
	
	@JsonIgnore
	public Room roomToEast;
	
	@JsonIgnore
	public Room roomToNorth;
	
	@JsonIgnore
	public Room roomToSouth;

	public String toWest = "";
	public String toEast = "";
	public String toNorth = "";
//    public ArrayList<GameObject> items;
	public String toSouth = "";

	public Room() {

	}

	public Room(String name, boolean access, String shortDescription, String longDescription, String toWest,
			String toEast, String toNorth, String toSouth) {
		super();
		this.name = name;
		this.access = access;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.toWest = toWest;
		this.toEast = toEast;
		this.toNorth = toNorth;
		this.toSouth = toSouth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAccess() {
		return access;
	}

	public void setAccess(boolean access) {
		this.access = access;
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

	public String getToWest() {
		return toWest;
	}

	public void setToWest(String toWest) {
		this.toWest = toWest;
	}

	public String getToEast() {
		return toEast;
	}

	public void setToEast(String toEast) {
		this.toEast = toEast;
	}

	public String getToNorth() {
		return toNorth;
	}

	public void setToNorth(String toNorth) {
		this.toNorth = toNorth;
	}

	public String getToSouth() {
		return toSouth;
	}

	public void setToSouth(String toSouth) {
		this.toSouth = toSouth;
	}

	@JsonIgnore
	public Room getRoomToWest() {
		return roomToWest;
	}

	@JsonIgnore
	public void setRoomToWest(Room roomToWest) {
		this.roomToWest = roomToWest;
	}

	@JsonIgnore
	public Room getRoomToEast() {
		return roomToEast;
	}

	@JsonIgnore
	public void setRoomToEast(Room roomToEast) {
		this.roomToEast = roomToEast;
	}

	@JsonIgnore
	public Room getRoomToNorth() {
		return roomToNorth;
	}

	@JsonIgnore
	public void setRoomToNorth(Room roomToNorth) {
		this.roomToNorth = roomToNorth;
	}

	@JsonIgnore
	public Room getRoomToSouth() {
		return roomToSouth;
	}

	@JsonIgnore
	public void setRoomToSouth(Room roomToSouth) {
		this.roomToSouth = roomToSouth;
	}

	@Override
	public String toString() {
		return "Room [name=" + name + ", shortDescription=" + shortDescription + ", toWest=" + toWest + ", toEast="
				+ toEast + ", toNorth=" + toNorth +  ", toSouth=" + toSouth + "]";
	}

	@JsonIgnore
	public Room getRoom() {
		return this;
	}

	public boolean isPickable() {
		return false;
	}

	@JsonIgnore
	public void setRoom(Room r) {
		
	}
	
	

}
