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
						String[] roomParts = r.getName().split("(?=\\p{Upper})");
						String roomName = roomParts.length == 1 ? roomParts[0] : roomParts[0] + " " + roomParts[1]; 
						if (GameData.getPlayer().getVisited().contains(r.getName())) {
							return new UserResponse(true,
									"You travelled north to " + roomName + ". " + r.getShortDescription());
						} else {
							GameData.getPlayer().getVisited().add(r.getName());
							return new UserResponse(true,
									"You travelled north to " + roomName + ". " + r.getLongDescription());
						}
					} else
						return accessRestrictedMessage(GameData.getPlayer().getRoom());
				} else {
					return new UserResponse(false, "There is nothing to the north");
				}
			} else if (input.getObject().equals("east")) {
				if (!GameData.getPlayer().getRoom().getToEast().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToEast();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						String[] roomParts = r.getName().split("(?=\\p{Upper})");
						String roomName = roomParts.length == 1 ? roomParts[0] : roomParts[0] + " " + roomParts[1]; 
						if (GameData.getPlayer().getVisited().contains(r.getName())) {
							return new UserResponse(true,
									"You travelled east to " + roomName + ". " + r.getShortDescription());
						} else {
							GameData.getPlayer().getVisited().add(r.getName());
							return new UserResponse(true,
									"You travelled east to " + roomName + ". " + r.getLongDescription());
						}
					} else
						return accessRestrictedMessage(GameData.getPlayer().getRoom());
				} else {
					return new UserResponse(false, "There is nothing to the east");
				}
			} else if (input.getObject().equals("south")) {
				if (!GameData.getPlayer().getRoom().getToSouth().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToSouth();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						String[] roomParts = r.getName().split("(?=\\p{Upper})");
						String roomName = roomParts.length == 1 ? roomParts[0] : roomParts[0] + " " + roomParts[1];
						if (GameData.getPlayer().getVisited().contains(r.getName())) {
							return new UserResponse(true,
									"You travelled south to " + roomName + ". " + r.getShortDescription());
						} else {
							GameData.getPlayer().getVisited().add(r.getName());
							return new UserResponse(true,
									"You travelled south to " + roomName + ". " + r.getLongDescription());
						}
					} else
						return accessRestrictedMessage(GameData.getPlayer().getRoom());
				} else {
					return new UserResponse(false, "There is nothing to the south");
				}
			} else if (input.getObject().equals("west")) {
				if (!GameData.getPlayer().getRoom().getToWest().equals("")) {
					Room r = GameData.getPlayer().getRoom().getRoomToWest();
					if (r.access) {
						GameData.getPlayer().setRoom(r);
						String[] roomParts = r.getName().split("(?=\\p{Upper})");
						String roomName = roomParts.length == 1 ? roomParts[0] : roomParts[0] + " " + roomParts[1];
						if (GameData.getPlayer().getVisited().contains(r.getName())) {
							return new UserResponse(true,
									"You travelled west to " + roomName + ". " + r.getShortDescription());
						} else {
							GameData.getPlayer().getVisited().add(r.getName());
							return new UserResponse(true,
									"You travelled west to " + roomName + ". " + r.getLongDescription());
						}
					} else
						return accessRestrictedMessage(GameData.getPlayer().getRoom());
				} else {
					return new UserResponse(false, "There is nothing to the west");
				}
			}
			else if (input.getObject().contains("boulder")
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
			else
				return new UserResponse(false, "Invalid input");
		}

		else if (input.getVerb().equals("print")) {
			if (input.getObject().equals("inventory"))
				return new UserResponse(false, GameData.getPlayer().getPossessions().toString());
			else if (input.getObject().equals("long"))
				return new UserResponse(false, GameData.getPlayer().getRoom().getLongDescription());
			else if (input.getObject().equals("save")) {
				System.out.println("Game saved successfully. You can resume anytime. Goodbye!");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}
			else if (input.getObject().equals("savefail"))
				return new UserResponse(false, "Failed to save Game.");
			else
				return noObjectKnownMessage(input);
		}

		else if (input.getVerb().equals("pick") || input.getVerb().equals("get") || input.getVerb().equals("take")) {
			for (GameObject i : GameData.items) {
				if (input.getObject().equalsIgnoreCase(i.getName())) {
					if (i.isPickable() && i.getRoom() == GameData.getPlayer().getRoom()
							&& !GameData.getPlayer().getPossessions().contains(input.getObject())) {
						GameData.getPlayer().addPossessions(input.getObject(), i);
						if(i.getName().equalsIgnoreCase("knife") 
								&& GameData.getPlayer().getRoom().getName().equals("Kitchen")) {
							GameData.getPlayer().getRoom().setLongDescription("Cold, with a large fridge and "
									+ "an island with rotten food.");
						}
						else if(i.getName().contains("mongoose") 
						&& GameData.getPlayer().getRoom().getName().equals("MainHall")) {
							GameData.getPlayer().getRoom().setLongDescription("You are in a huge room with a big door locked with a gold coloured padlock, paintings and a clock on the wall, 3 cages,"
									+ " one with a grey parrot and the other two open. The parrot says Book Macbeth, Book Macbeth");
						}
						else if(i.getName().equalsIgnoreCase("torch") 
								&& GameData.getPlayer().getRoom().getName().equals("wineRoom")) {
									GameData.getPlayer().getRoom().setLongDescription("Old wine bottles arranged in lettered rows, a small table with an "
											+ "empty bottle.");
								}
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
						if(i.getName().toLowerCase().contains("mongoose") 
								&& GameData.getPlayer().getRoom().getName().equals("MainHall")) {
									GameData.getPlayer().getRoom().setLongDescription("You are in a huge room with a big door locked with a gold coloured padlock, paintings and a clock on the wall, 3 cages,"
											+ " one with a grey parrot and the other two open. The parrot says Book Macbeth, Book Macbeth");
								}
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

		else if (input.getVerb().equals("look") || input.getVerb().equals("see")) {
			if (input.getObject().contains("clock") && GameData.getPlayer().getRoom().getName().equals("MainHall"))
				return new UserResponse(false, "The Clock is stuck on 11:45.");
			else if (input.getObject().contains("telescope")
					&& GameData.getPlayer().getRoom().getName().equals("Observatory")) {
				Character snake = GameData.characters.stream().filter(c -> "Snake".equals(c.getName())).findFirst()
						.orElse(null);
				if(snake.getHealthpower() > 0) {
					return new UserResponse(false, 
							"The snake blocks the access to telescope. It is coming towards you with evil intent.");
				}
				return new UserResponse(false, "The telescope is focused on the constellation \"Orion\"");
			}
			else if (input.getObject().contains("exclusive wine")
					&& GameData.getPlayer().getRoom().getName().equals("WineRoom"))
				return new UserResponse(false,
						"Exclusive wines are racked in the aisles labelled \"A\", \"C\", \"E\" and \"K\".");
			else if (input.getObject().contains("wall") && GameData.getPlayer().getRoom().getName().equals("Cellar"))
				return new UserResponse(false, "The wall has the words: \"Exclusive wines. How sweet!\"");
			else if (input.getObject().contains("macbeth") && GameData.getPlayer().getRoom().getName().equals("Library"))
				return new UserResponse(false, "All the pages in the book are blank except one which reads "
						+ "\"What has 2 hands and a face but no arms and legs\"");
			else
				return noObjectKnownMessage(input);
		}

		else if (input.getVerb().equals("read")) {
			if (input.getObject().contains("macbeth") && GameData.getPlayer().getRoom().getName().equals("Library"))
				return new UserResponse(false, "All the pages in the book are blank except one which reads "
						+ "\"What has 2 hands and a face but no arms and legs\"");
			else if (input.getObject().contains("wall") && GameData.getPlayer().getRoom().getName().equals("Cellar"))
				return new UserResponse(false, "The wall has the words: \"Exclusive wines. How sweet!\"");
			else if (input.getObject().contains("clock") && GameData.getPlayer().getRoom().getName().equals("MainHall"))
				return new UserResponse(false, "The Clock is stuck on 11:45.");
			else
				return noObjectKnownMessage(input);
		}

		else if (input.getVerb().equals("find") || input.getVerb().equals("search")) {
			if (input.getObject().contains("exclusive wine")
					&& GameData.getPlayer().getRoom().getName().equals("WineRoom"))
				return new UserResponse(false,
						"Exclusive wines are racked in the aisles labelled \"A\", \"C\", \"E\" and \"K\".");
			else if(input.getObject().contains("cake") && GameData.getPlayer().getRoom().getName().equals("Kitchen")) {
				Item goldKey = GameData.items.stream().filter(c -> "gold key".equals(c.getName())).findFirst()
						.orElse(null);
				goldKey.setPickable(true);
				GameData.getPlayer().addPossessions("gold Key", goldKey);
				return new UserResponse(false, "Inside the cake you found a gold key. It has been added to your inventory.");
			}
			else if (input.getObject().contains("macbeth") && GameData.getPlayer().getRoom().getName().equals("Library"))
				return new UserResponse(false, "All the pages in the book are blank except one which reads "
						+ "\"What has 2 hands and a face but no arms and legs\"");
			else
				return noObjectKnownMessage(input);
		}

		else if (input.getVerb().equals("listen") || input.getVerb().equals("hear")) {
			if (input.getObject().contains("parrot") && GameData.getPlayer().getRoom().getName().equals("MainHall")) {
				mains.entities.Character parrot = GameData.characters.stream()
						.filter(c -> "Grey parrot".equals(c.getName())).findFirst().orElse(null);
				return new UserResponse(false, "The parrot says " + parrot.getStates().get(parrot.getCurrentState()));
			}
			else
				return noObjectKnownMessage(input);
		}

		else if (input.getVerb().equals("use")) {
			if (input.getObject().contains("mongoose")
					&& GameData.getPlayer().getRoom().getName().equals("Observatory") 
					&& (GameData.getPlayer().getPossessions().contains("mongoose") || GameData.getPlayer().getPossessions().contains("Mongoose"))) {
				Character snake = GameData.characters.stream().filter(c -> "Snake".equals(c.getName())).findFirst()
						.orElse(null);
				if (snake.getHealthpower() > 0) {
					Character mongoose = GameData.characters.stream().filter(c -> "Mongoose".equals(c.getName())).findFirst()
							.orElse(null);
					GameData.getPlayer().removePossession("mongoose cage");
					GameData.getPlayer().removePossession("mongoose");
					mongoose.setPickable(false);
					mongoose.setRoom(GameData.getPlayer().getRoom());
					snake.setHealthpower(0);
					return new UserResponse(false, "The mongoose attacked and killed the snake.");
				}
				else
					return new UserResponse(false, "You do not have mongoose anymore.");
			} else if (input.getObject().contains("torch")
					&& GameData.getPlayer().getRoom().getName().equals("Attic")
					&& GameData.getPlayer().getPossessions().contains("torch")) {
				
				if(!GameData.getPlayer().getPossessions().contains("silver key")) {
				Item silverKey = GameData.items.stream().filter(c -> "silver key".equals(c.getName())).findFirst()
						.orElse(null);
				silverKey.setPickable(true);
				return new UserResponse(false,
						"Now you can see in the attic, there is nothing but a shiny silver key in there.");
				}
				else
					return new UserResponse(false,
							"Now you can see in the attic, but there's nothing there.");
			}
			else if (input.getObject().contains("silver key")
					&& GameData.getPlayer().getRoom().getName().equals("Bedroom") 
					&& GameData.getPlayer().possessions.contains("silver key")) {
				
				Character woman = GameData.characters.stream().filter(c -> "Ms Fitzgerald".equals(c.getName())).findFirst()
						.orElse(null);
				
				if(woman.isPickable())
					return new UserResponse(false,
							"Silver key has already been used.");
				woman.setPickable(true);
				GameData.getPlayer().addPossessions("Ms Fitzgerald", woman);
				
				Room room = GameData.rooms.stream().filter(c -> "Bedroom".equals(c.getName())).findFirst()
						.orElse(null);
				room.setLongDescription("A windowless bedroom with chains unlocked");
				room.setShortDescription("A windowless bedroom with chains unlocked");
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
			
			else if (input.getObject().contains("telescope")
					&& GameData.getPlayer().getRoom().getName().equals("Observatory")) {
				Character snake = GameData.characters.stream().filter(c -> "Snake".equals(c.getName())).findFirst()
						.orElse(null);
				if(snake.getHealthpower() > 0) {
					return new UserResponse(false, 
							"The snake blocks the access to telescope. It is coming towards you with evil intent.");
				}
				return new UserResponse(false, "The telescope is focused on the constellation \"Orion\"");
			}
			else if (input.getObject().equalsIgnoreCase("knife")
					&& GameData.getPlayer().getRoom().getName().equals("Observatory")
					&& GameData.getPlayer().getPossessions().contains("knife")) {
				Character snake = GameData.characters.stream().filter(c -> "Snake".equals(c.getName())).findFirst()
						.orElse(null);
				if(snake.getHealthpower() > 0) {
					return new UserResponse(false, 
							"You tried using knife to kill the snake but failed.");
				}
				return new UserResponse(false, "Knife cannot be used right now.");
			}
			else {
				return noObjectKnownMessage(input);
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
			else
				return noObjectKnownMessage(input);
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
			else if (input.getObject().contains("fridge")
					&& GameData.getPlayer().getRoom().getName().equals("Kitchen")) {
				return new UserResponse(false,
						"Inside the fridge, you find soft drinks and a cake.");
			}
			else
				return noObjectKnownMessage(input);
			
		}
		
		else if (input.getVerb().equals("eat")) {
			if(input.getObject().contains("cake") && GameData.getPlayer().getRoom().getName().equals("Kitchen")) {
				Item goldKey = GameData.items.stream().filter(c -> "gold key".equals(c.getName())).findFirst()
						.orElse(null);
				goldKey.setPickable(true);
				GameData.getPlayer().addPossessions("gold key", goldKey);
				return new UserResponse(false, "Inside the cake you found a gold key. It has been added to your inventory.");
			}
		}
	

		return new UserResponse(false, "An unknown input");
	}

	private static UserResponse accessRestrictedMessage(Room r) {
		if(r.getName().equals("Study")) {
			return new UserResponse(false,
					"Access is restricted. A lock that takes 4 numbers blocks the entrance to the bedroom.");
		}
		else if(r.getName().equals("Observatory")) {
			return new UserResponse(false,
					"Access is restricted. A lock that accepts 5 letters blocks the entrance to the attic.");
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
	
	private static UserResponse noObjectKnownMessage(UserInput input) {
		return new UserResponse(false, "Cannot determine how to " + input.getVerb() + " " + input.getObject());
	}
}
