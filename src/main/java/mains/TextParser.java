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

		else if(input.equals("look"))
				return new UserInput("print", "long");			
		
		else if(input.equals("save")) {
			try {
				GameData.saveGame();
				return new UserInput("print", "save");
			}
			catch(Exception e) {
			if(GameData.env.equalsIgnoreCase("dev"))
				e.printStackTrace();
				return new UserInput("print", "savefail");
			}
		}

/*		else if (input.contains("get") || input.contains("take") || input.contains("pick")) {
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
		
		else if (input.contains("read")) {
			String readObject = input.split("read")[1].trim();
			return new UserInput("read", readObject);
		}
		
		else if (input.contains("find")) {
			String readObject = input.split("find")[1].trim();
			return new UserInput("find", readObject);
		}*/
		
		else {
			String parts[] = input.split(" ", 2);
			if(parts.length > 1) {
				return new UserInput(parts[0].trim(), parts[1].trim());
			}
				return new UserInput("invalid", parts[0]);
		}
		
	}

}
