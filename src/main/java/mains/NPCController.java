package mains;

public class NPCController {

	public static void changeStates() {
		for(mains.entities.Character c: GameData.characters) {
			if(c.getHealthpower() > 0 && c.getCurrentState() != -1) {
				c.setCurrentState((int)(Math.random() * c.getStates().size()));
			}
		}
	}
}
