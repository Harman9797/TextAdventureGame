package mains;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import mains.entities.Item;
import mains.entities.Room;
import mains.entities.Character;
import mains.entities.GameObject;

public class GameData {

	public static List<Room> rooms;
	public static List<Item> items;
	public static List<Character> characters;
	static Character player;
	static ObjectMapper mapper = new ObjectMapper();
	static String env;
	static String roomPath = "Rooms.json";
	static String itemPath = "Items.json";
	static String characterPath = "Character.json";

	public static Character getPlayer() {
		return player;
	}

	public static void loadData() {
		// mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
		env = System.getProperty("env") != null ? System.getProperty("env") : "dev";

		if (env.equalsIgnoreCase("dev")) {
			roomPath = "src/main/resources/mains/Rooms.json";
			itemPath = "src/main/resources/mains/Items.json";
			characterPath = "src/main/resources/mains/Character.json";
		}

		try {
			rooms = mapper.readValue(new File(roomPath),
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

			items = mapper.readValue(new File(itemPath),
					mapper.getTypeFactory().constructCollectionType(List.class, Item.class));

			characters = mapper.readValue(new File(characterPath),
					mapper.getTypeFactory().constructCollectionType(List.class, Character.class));

			for (Character character : characters) {
				if (character.getName().equalsIgnoreCase("main")) {
					player = character;
				}
				for (String item : character.getPossessions()) {
					boolean inItem = false;
					for (GameObject i : GameData.items) {
						if (item.equalsIgnoreCase(i.getName())) {
							character.getObjectsPossessed().add(i);
							inItem = true;
							break;
						}
					}
					if (!inItem) {
						for (GameObject i : GameData.characters) {
							if (item.equalsIgnoreCase(i.getName())) {
								character.getObjectsPossessed().add(i);
								break;
							}
						}
					}
					// System.out.println("Unknown item: " + item + ".");
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

	public static String getFirst() {
		if (GameData.getPlayer().getPossessions().size() == 0
				&& GameData.getPlayer().getRoom().getName().equalsIgnoreCase("MainHall")) {
			return "You are a detective who is solving the disappearance of Ms Fitzgerald. You trace her to"
					+ " an old secluded big house. When you secretly enter through the window of the Main Hall"
					+ ", someone hits you on the back of the head. The last thing you remember hearing is \"Let's"
					+ " see how you get out of this detective\".\n" + "You wake up and find all the windows sealed. "
					+ GameData.getPlayer().getRoom().getLongDescription();
		} else
			return "Welcome back. You are in the " + GameData.getPlayer().getRoom().getName() + ". "
					+ GameData.getPlayer().getRoom().getLongDescription() + " You have "
					+ GameData.getPlayer().getPossessions();
	}

	public static void saveGame() throws JsonGenerationException, JsonMappingException, IOException {
		mapper.writeValue(new File(characterPath), characters);
		mapper.writeValue(new File(itemPath), items);
		mapper.writeValue(new File(roomPath), rooms);
	}
}
