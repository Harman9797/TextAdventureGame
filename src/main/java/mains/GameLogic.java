package mains;

import mains.entities.Character;
import mains.entities.GameObject;
import mains.entities.Item;
import mains.entities.Room;

public class GameLogic {

	static UserResponse getRepsonse(UserInput input) {

		NPCController.changeStates();

		if (input.getVerb().equals("move")) {
			if (input.getObject().equals("north")) {
				if (!GameData.getPlayer().getRoom().getToNorth().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToNorth();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						if (GameData.getPlayer().getVisited().contains(r.getName())) {
							return new UserResponse(true,
									"You travelled north to " + r.getName() + ". " + r.getShortDescription());
						} else {
							GameData.getPlayer().getVisited().add(r.getName());
							return new UserResponse(true,
									"You travelled north to " + r.getName() + ". " + r.getLongDescription());
						}
					} else
						return accessRestrictedMessage(r);
				} else {
					return new UserResponse(false, "There is nothing to the north");
				}
			} else if (input.getObject().equals("east")) {
				if (!GameData.getPlayer().getRoom().getToEast().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToEast();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						if (GameData.getPlayer().getVisited().contains(r.getName())) {
							return new UserResponse(true,
									"You travelled east to " + r.getName() + ". " + r.getShortDescription());
						} else {
							GameData.getPlayer().getVisited().add(r.getName());
							return new UserResponse(true,
									"You travelled east to " + r.getName() + ". " + r.getLongDescription());
						}
					} else
						return accessRestrictedMessage(r);
				} else {
					return new UserResponse(false, "There is nothing to the east");
				}
			} else if (input.getObject().equals("south")) {
				if (!GameData.getPlayer().getRoom().getToSouth().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToSouth();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						if (GameData.getPlayer().getVisited().contains(r.getName())) {
							return new UserResponse(true,
									"You travelled south to " + r.getName() + ". " + r.getShortDescription());
						} else {
							GameData.getPlayer().getVisited().add(r.getName());
							return new UserResponse(true,
									"You travelled south to " + r.getName() + ". " + r.getLongDescription());
						}
					} else
						return accessRestrictedMessage(r);
				} else {
					return new UserResponse(false, "There is nothing to the south");
				}
			} else if (input.getObject().equals("west")) {
				if (!GameData.getPlayer().getRoom().getToWest().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToWest();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						if (GameData.getPlayer().getVisited().contains(r.getName())) {
							return new UserResponse(true,
									"You travelled west to " + r.getName() + ". " + r.getShortDescription());
						} else {
							GameData.getPlayer().getVisited().add(r.getName());
							return new UserResponse(true,
									"You travelled west to " + r.getName() + ". " + r.getLongDescription());
						}
					} else
						return accessRestrictedMessage(r);
				} else {
					return new UserResponse(false, "There is nothing to the west");
				}
			} else
				return new UserResponse(false, "Invalid input");
		}

		else if (input.getVerb().equals("print")) {
			if (input.getObject().equals("inventory"))
				return new UserResponse(false, GameData.getPlayer().getPossessions().toString());
			else if (input.getObject().equals("look"))
				return new UserResponse(false, GameData.getPlayer().getRoom().getLongDescription());
		}

		else if (input.getVerb().equals("pick")) {
			for (Item i : GameData.items) {
				if (input.getObject().equalsIgnoreCase(i.getName())) {
					if (i.isPickable() && i.getRoom() == GameData.getPlayer().getRoom()
							&& !GameData.getPlayer().getPossessions().contains(input.getObject())) {
						GameData.getPlayer().addPossessions(input.getObject(), i);
						return new UserResponse(false, "You picked " + input.getObject());
					} else {
						return new UserResponse(false, "" + input.getObject() + " cannot be picked up right now.");
					}
				}
			}
			for (mains.entities.Character i : GameData.characters) {
				if (input.getObject().equalsIgnoreCase(i.getName())) {
					if (i.isPickable() && i.getRoom() == GameData.getPlayer().getRoom()
							&& !GameData.getPlayer().getPossessions().contains(input.getObject())) {
						GameData.getPlayer().addPossessions(input.getObject(), i);
						return new UserResponse(false, "You picked " + input.getObject());
					} else {
						return new UserResponse(false, "" + input.getObject() + " cannot be picked up right now.");
					}
				}
			}
			return new UserResponse(false, "Unknown item/person " + input.getObject());
		}

		else if (input.getVerb().equals("drop")) {
			for (GameObject i : GameData.getPlayer().getObjectsPossessed()) {
				if (input.getObject().equalsIgnoreCase(i.getName())) {
					GameData.getPlayer().removePossession(input.getObject(), i, GameData.getPlayer().getRoom());
					return new UserResponse(false, "You dropped " + input.getObject());
				}
			}
			return new UserResponse(false, "Cannot drop something that is not possessed");
		}

		else if (input.getVerb().equals("look")) {
			if (input.getObject().contains("clock") && GameData.getPlayer().getRoom().getName().equals("MainHall"))
				return new UserResponse(false, "The Clock is stuck on 11:45.");
			else if (input.getObject().contains("telescope")
					&& GameData.getPlayer().getRoom().getName().equals("Observatory"))
				return new UserResponse(false, "The telescope is focused on the constellation \"Orion\"");
			else if (input.getObject().contains("exclusive wine")
					&& GameData.getPlayer().getRoom().getName().equals("WineRoom"))
				return new UserResponse(false,
						"Exclusive wines are racked in the aisles labelled \"A\", \"C\", \"E\" and \"K\".");
			else if (input.getObject().contains("wall") && GameData.getPlayer().getRoom().getName().equals("Cellar"))
				return new UserResponse(false, "The wall has the words: \"Exclusive wines. How sweet!\"");
		}

		else if (input.getVerb().equals("read")) {
			if (input.getObject().equals("book") && GameData.getPlayer().getRoom().getName().equals("Library"))
				return new UserResponse(false, "All the pages in the book are blank except one which reads "
						+ "\"What has 2 hands and a face but no arms and legs\"");
			else if (input.getObject().contains("wall") && GameData.getPlayer().getRoom().getName().equals("Cellar"))
				return new UserResponse(false, "The wall has the words: \"Exclusive wines. How sweet!\"");
		}

		else if (input.getVerb().equals("find") || input.getVerb().equals("search")) {
			if (input.getObject().contains("exclusive wine")
					&& GameData.getPlayer().getRoom().getName().equals("WineRoom"))
				return new UserResponse(false,
						"Exclusive wines are racked in the aisles labelled \"A\", \"C\", \"E\" and \"K\".");
		}

		else if (input.getVerb().equals("listen") || input.getVerb().equals("hear")) {
			if (input.getObject().contains("parrot") && GameData.getPlayer().getRoom().getName().equals("MainHall")) {
				mains.entities.Character parrot = GameData.characters.stream()
						.filter(c -> "Grey parrot".equals(c.getName())).findFirst().orElse(null);
				return new UserResponse(false, "The parrot says " + parrot.getStates().get(parrot.getCurrentState()));
			}
		}

		else if (input.getVerb().equals("use")) {
			if (input.getObject().contains("mongoose")
					&& GameData.getPlayer().getRoom().getName().equals("Observatory")) {
				Character snake = GameData.characters.stream().filter(c -> "Snake".equals(c.getName())).findFirst()
						.orElse(null);
				if (snake.getHealthpower() > 0) {
					GameData.getPlayer().removePossession("mongoose cage");
					return new UserResponse(false, "The mongoose attacked and killed the snake.");
				}
			} else if (input.getObject().contains("torch")
					&& GameData.getPlayer().getRoom().getName().equals("Attic")) {
				return new UserResponse(false,
						"Now you can see in the attic, there is nothing but a shiny silver key in there.");
			}
			else if (input.getObject().contains("silver key")
					&& GameData.getPlayer().getRoom().getName().equals("Bedroom") 
					&& GameData.getPlayer().possessions.contains("silver key")) {
				Character woman = GameData.characters.stream().filter(c -> "Ms Fitzgerald".equals(c.getName())).findFirst()
						.orElse(null);
				woman.setPickable(true);
				GameData.getPlayer().addPossessions("Ms Fitzgerald", woman);
				return new UserResponse(false,
						"Silver key unlocked the silver padlock. Ms Fitzgerlad is now free and she will follow you to help escape the house.");
			}
			
			else if (input.getObject().contains("gold key")
					&& GameData.getPlayer().getRoom().getName().equals("MainHall") 
					&& GameData.getPlayer().possessions.contains("gold key")) {
				System.out.println("Congratulations!! You have successfully escaped");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}
			
		}
		
		else if (input.getVerb().equals("push") || input.getVerb().equals("remove")) {
			if (input.getObject().contains("boulder")
					&& GameData.getPlayer().getRoom().getName().equals("WineRoom")) {
				Character woman = GameData.characters.stream().filter(c -> "Ms Fitzgerald".equals(c.getName())).findFirst()
						.orElse(null);
				if(woman.isPickable()) {
					Room cellar = GameData.rooms.stream().filter(c -> "Cellar".equals(c.getName())).findFirst()
							.orElse(null);
					cellar.setAccess(true);
					return new UserResponse(false, "You and Ms Fitzgerald push the bolder aside. The entrance to cellar is now open.");
				}
				else {
					return new UserResponse(false, "You push the bolder but it is heavy for one person. The entrance to cellar remains blocked");
				}
			}
		}
		
		else if (input.getVerb().equals("enter") || input.getVerb().equals("set") || input.getVerb().equals("open")) {
			if (input.getObject().contains("1145")
					&& GameData.getPlayer().getRoom().getName().equals("Study")) {
				Room bedroom = GameData.rooms.stream().filter(c -> "Bedroom".equals(c.getName())).findFirst()
						.orElse(null);
				bedroom.setAccess(true);
				return new UserResponse(false,
						"The combination worked. Bedroom is now accessible.");
			}
			else if (input.getObject().contains("orion")
					&& GameData.getPlayer().getRoom().getName().equals("Observatory")) {
				Room attic = GameData.rooms.stream().filter(c -> "Attic".equals(c.getName())).findFirst()
						.orElse(null);
				attic.setAccess(true);
				return new UserResponse(false,
						"The combination worked. Attic is now accessible.");
			}
			
		}

		return new UserResponse(false, "An unknown input");
	}

	private static UserResponse accessRestrictedMessage(Room r) {
		if(r.getName().equals("Study")) {
			return new UserResponse(false,
					"Access is restricted. A silver padlock blocks the entrance to bedroom");
		}
		else if(r.getName().equals("Observatory")) {
			return new UserResponse(false,
					"Access is restricted. A lock that accepts 5 letters blocks the entrance to the attic");
		}
		else {
			return new UserResponse(false,
					"Access is restricted. A boulder blocks the entrance to the cellar");
		}	
	}

	public static String getNPCStateDescription() {
		String npcdesc = "";
		for (Character c : GameData.characters) {
			if (c.getName().equals("main"))
				continue;
			if (c.getHealthpower() > 0 && c.getRoom() == GameData.getPlayer().getRoom()) {
				if (c.getName().equalsIgnoreCase("snake")) {
					c.getStates().add("charging at you with an evil intent");
					c.setCurrentState(c.getStates().size() - 1);
				}
				npcdesc += c.getName() + " " + c.getStates().get(c.getCurrentState()) + ". ";
			}
		}
		return npcdesc;
	}

	public static String getFirst() {
		return GameData.getPlayer().getRoom().getLongDescription();
	}
}
