package mains;

public class TextParser {

	static UserInput parse(String input) {
		input = input.toLowerCase().trim();
		if (input.length() == 1 || input.contains("move ") || input.contains("go ")) {
			if (input.equals("n") || input.contains("north")) {
				return new UserInput("move", "north");
			} else if (input.equals("e") || input.contains("east")) {
				return new UserInput("move", "east");
			} else if (input.equals("s") || input.contains("south")) {
				return new UserInput("move", "south");
			} else if (input.equals("w") || input.contains("west")) {
				return new UserInput("move", "west");
			} else
				return new UserInput("move", "invalid");
		} else if (input.equals("inventory")) {
			return new UserInput("print", "inventory");
		}

		else if (input.contains("look")) {
			String lookwhere = input.replace("the", "");
			if(input.equals("look"))
				return new UserInput("print", GameData.getPlayer().getRoom().getLongDescription());
			else if(lookwhere.contains("at")) {
				return new UserInput("look", lookwhere.split("at")[1].trim());
			}
			else if(lookwhere.contains("through")) {
				return new UserInput("look", lookwhere.split("through")[1].trim());
			}
			else {
				return new UserInput("look", lookwhere.split("look")[1].trim());
			}	
			
		}

		else if (input.contains("get") || input.contains("take") || input.contains("pick")) {
			String pickedObject = input.substring(input.indexOf(" ")).trim();
			if (pickedObject.toLowerCase().contains("mongoose"))
				pickedObject = "mongoose cage";
			return new UserInput("pick", pickedObject);
		}

		else if (input.contains("drop")) {
			String pickedObject = input.substring(input.indexOf(" ")).trim();
			if (pickedObject.toLowerCase().contains("mongoose"))
				pickedObject = "mongoose cage";
			return new UserInput("drop", pickedObject);
		}
		

		return new UserInput("Unrecognized", "Some other input not yet calcaulted");
	}

}
