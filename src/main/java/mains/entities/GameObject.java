package mains.entities;

public interface GameObject {
	
	String getName();	
	String getShortDescription();
	String getLongDescription();
	Room getRoom();
	void setRoom(Room r);
	boolean isPickable();

}
