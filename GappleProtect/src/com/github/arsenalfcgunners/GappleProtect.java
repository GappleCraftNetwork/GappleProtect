package com.github.arsenalfcgunners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author arsenalfcgunners
 * @version 1.1
 * 
 * This plugin manages the world settings and protects the world. In the future it will
 * be made much more custimizable.
 *
 */
public class GappleProtect extends JavaPlugin{
	ArrayList<PlayerProfile> pp = new ArrayList<PlayerProfile>();

	@Override
	public void onEnable(){
		for(Player player: Bukkit.getOnlinePlayers()){
			pp.add(new PlayerProfile(player, this));
		}
		
		new PlayerListener(this);
		new BlockListener(this);
		new WeatherListener(this);
		new EntityListener(this);
		new InventoryListener(this);
	}
	
	@Override
	public void onDisable(){
		
	}
	
	public PlayerProfile getPlayerProfile(Player player){
		for(int i = 0; i < pp.size(); i++){
			if(pp.get(i).getPlayer().equals(player)){
				return pp.get(i);
			}
		}
		return null;
	}
	
	public void removePlayer(Player player){
		for(int i = 0; i < pp.size(); i++){
			if(pp.get(i).getPlayer().equals(player)){
				pp.remove(i);
			}
		}
	}
	
}
