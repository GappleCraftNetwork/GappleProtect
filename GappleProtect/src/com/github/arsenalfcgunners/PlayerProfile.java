package com.github.arsenalfcgunners;

import org.bukkit.entity.Player;

public class PlayerProfile {
Player player;
boolean build;
GappleProtect gp;

	public PlayerProfile(Player p, GappleProtect plugin){
		player = p;
		gp = plugin;
	}
	
	public boolean getBuildStatus(){
		return build;
	}
	
	public void toggleBuild(){
		build = !build;
	}
	
	public Player getPlayer(){
		return player;
	}
}

