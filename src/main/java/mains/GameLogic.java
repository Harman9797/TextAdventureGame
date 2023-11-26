package mains;

import mains.entities.Character;
import mains.entities.GameObject;
import mains.entities.Item;
import mains.entities.Room;

public class GameLogic {

	static int dir = 0;

	static UserResponse getRepsonse(UserInput input) {

		NPCController.changeStates();

		if (input.getVerb().equals("move")) {
			if (input.getObject().equals("north")) {
				if (!GameData.getPlayer().getRoom().getToNorth().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToNorth();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						return new UserResponse(true, 
								"You travelled north to " + r.getName() + ". " + r.getLongDescription());
					} else
						return new UserResponse(false,
								"You cannot walk north to " + r.getName() + ". Access is restricted.");
				} else {
					return new UserResponse(false, "There is nothing to the north");
				}
			} else if (input.getObject().equals("east")) {
				if (!GameData.getPlayer().getRoom().getToEast().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToEast();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						return new UserResponse(true,
								"You travelled east to " + r.getName() + ". " + r.getLongDescription());
					} else
						return new UserResponse(false,
								"You cannot walk east to " + r.getName() + ". Access is restricted.");
				} else {
					return new UserResponse(false, "There is nothing to the east");
				}
			} else if (input.getObject().equals("south")) {
				if (!GameData.getPlayer().getRoom().getToSouth().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToSouth();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						return new UserResponse(true,
								"You travelled south to " + r.getName() + ". " + r.getLongDescription());
					} else
						return new UserResponse(false,
								"You cannot walk south to " + r.getName() + ". Access is restricted.");
				} else {
					return new UserResponse(false, "There is nothing to the south");
				}
			} else if (input.getObject().equals("west")) {
				if (!GameData.getPlayer().getRoom().getToWest().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToWest();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						return new UserResponse(true,
								"You travelled west to " + r.getName() + ". " + r.getLongDescription());
					} else
						return new UserResponse(false,
								"You cannot walk west to " + r.getName() + ". Access is restricted.");
				} else {
					return new UserResponse(false, "There is nothing to the west");
				}
			} else
				return new UserResponse(false, "Invalid input");
		}
		
		else if (input.getVerb().equals("print")){
			if(input.getObject().equals("inventory"))
				return new UserResponse(false, GameData.getPlayer().getPossessions().toString());
			else if(input.getObject().equals("look"))
				return new UserResponse(false, GameData.getPlayer().getRoom().getLongDescription());
		}
		
		else if(input.getVerb().equals("pick")) {
			for(Item i : GameData.items) {
				if(input.getObject().equalsIgnoreCase(i.getName())) {
					if(i.isPickable() && i.getRoom() == GameData.getPlayer().getRoom()
							&& !GameData.getPlayer().getPossessions().contains(input.getObject())) {
						GameData.getPlayer().addPossessions(input.getObject(), i);
						return new UserResponse(false, "You picked " + input.getObject());
					}
					else {
						return new UserResponse(false, "" + input.getObject() + " cannot be picked up right now.");
					}
				}
			}
			for(mains.entities.Character i : GameData.characters) {
				if(input.getObject().equalsIgnoreCase(i.getName())) {
					if(i.isPickable() && i.getRoom() == GameData.getPlayer().getRoom()
							&& !GameData.getPlayer().getPossessions().contains(input.getObject())) {
						GameData.getPlayer().addPossessions(input.getObject(), i);
						return new UserResponse(false, "You picked " + input.getObject());
					}
					else {
						return new UserResponse(false, "" + input.getObject() + " cannot be picked up right now.");
					}
				}
			}
			return new UserResponse(false, "Unknown item/person " + input.getObject());
		}
		
		else if(input.getVerb().equals("drop")) {
			for(GameObject i : GameData.getPlayer().getObjectsPossessed()) {
				if(input.getObject().equalsIgnoreCase(i.getName())) {
					GameData.getPlayer().removePossession(input.getObject(), i, GameData.getPlayer().getRoom());
					return new UserResponse(false, "You dropped " + input.getObject());
				}
			}
			return new UserResponse(false, "Cannot drop something that is not possessed");
		}
		
		else if(input.getVerb().equals("look")) {
			if(input.getObject().equals("clock") && GameData.getPlayer().getRoom().getName().equals("MainHall"))
				return new UserResponse(false, "The Clock is stuck on 11:45.");
			else if(input.getObject().equals("telescope") && GameData.getPlayer().getRoom().getName().equals("Observatory"))
				return new UserResponse(false, "The telescope is focused on the constellation \"Orion\"");
		}

		return new UserResponse(false, "Some other input not yet calcaulted");
	}

	public static String getNPCStateDescription() {
		String npcdesc = "";
		for(Character c: GameData.characters) {
			if(c.getName().equals("main"))
				continue;
			if(c.getHealthpower() > 0 && c.getRoom() == GameData.getPlayer().getRoom()) {
				if(c.getName().equalsIgnoreCase("snake")) {
					c.getStates().add("charging at you with an evil intent");
					c.setCurrentState(c.getStates().size() - 1);
				}
				npcdesc += c.getName() + " " + c.getStates().get(c.getCurrentState()) + ". ";
			}
		}
		return npcdesc;
	}
}
