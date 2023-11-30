package mains;

import java.util.Scanner;

public class Run {

	public static void main(String[] args) {
		
		GameData.loadData();
		System.out.println(GameData.getFirst());
		Scanner in;
		in = new Scanner(System.in);	

		// Read line
		String str = "";
		do {
			str = in.nextLine();
			UserResponse response = GameLogic.getRepsonse(TextParser.parse(str));
			System.out.println(response.getResponse());
			if(response.isShowNPCDetails()) {
				System.out.println(GameLogic.getNPCStateDescription());
			}
		} while (!str.equalsIgnoreCase("q"));
	}

}
