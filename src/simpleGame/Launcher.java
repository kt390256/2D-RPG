package simpleGame;

import Core.Game;

public class Launcher {

	
	public static void main(String[] args) {
		
		//Display w = new Display("Apocalypse : Endless Night", 800, 600);
		
		Game game = new Game("Apocalypse :The Borken World", 1500, 900);
		game.start();
	}
	
	
	
}
