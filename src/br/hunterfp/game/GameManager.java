package br.hunterfp.game;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

	private static Player player;
	
	public static void registerPlayer(Player p){
		player = p;
	}
	
	public static Player getPlayer(){
		return player;
	}
	
}
