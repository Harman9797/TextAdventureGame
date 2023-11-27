package mains;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mains.entities.Item;
import mains.entities.Room;
import mains.entities.Character;

public class GameData {

	public static List<Room> rooms;
	public static List<Item> items;
	public static List<Character> characters;
	static Character player;

	public static Character getPlayer() {
		return player;
	}

	public static void loadData() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			rooms = mapper.readValue(new File("Rooms.json"),
					mapper.getTypeFactory().constructCollectionType(List.class, Room.class));

			for (Room r : rooms) {
				if (!r.getToNorth().equals("")) {
					for (Room rdir : rooms) {
						if (r.getToNorth().equalsIgnoreCase(rdir.getName())) {
							r.setRoomToNorth(rdir);
							break;
						}
					}
				}
				if (!r.getToEast().equals("")) {
					for (Room rdir : rooms) {
						if (r.getToEast().equalsIgnoreCase(rdir.getName())) {
							r.setRoomToEast(rdir);
							break;
						}
					}
				}
				if (!r.getToSouth().equals("")) {
					for (Room rdir : rooms) {
						if (r.getToSouth().equalsIgnoreCase(rdir.getName())) {
							r.setRoomToSouth(rdir);
							break;
						}
					}
				}
				if (!r.getToWest().equals("")) {
					for (Room rdir : rooms) {
						if (r.getToWest().equalsIgnoreCase(rdir.getName())) {
							r.setRoomToWest(rdir);
							break;
						}
					}
				}
			}

			items = mapper.readValue(new File("Items.json"),
					mapper.getTypeFactory().constructCollectionType(List.class, Item.class));

			characters = mapper.readValue(new File("Character.json"),
					mapper.getTypeFactory().constructCollectionType(List.class, Character.class));

			for (Character character : characters) {
				if (character.getName().equalsIgnoreCase("main")) {
					player = character;
					break;
				}
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
