package com.github.arsenalfcgunners.gappleprotect;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author arsenalfcgunners
 * @version 1.1
 * 
 * This plugin manages the world settings and protects the world. In the future it will
 * be made much more customizable.
 *
 */
public class GappleProtect extends JavaPlugin{
	private ArrayList<PlayerProfile> pp;
	private String tag;
	private InventoryManager inv;
	
	@Override
	public void onEnable(){
		tag = ChatColor.GRAY+"["+ChatColor.GOLD+"GappleProtect"+ChatColor.GRAY+"] "+ChatColor.YELLOW;
		pp = new ArrayList<PlayerProfile>();
				
		for(Player player: Bukkit.getOnlinePlayers()){
			pp.add(new PlayerProfile(player, this));
		}
		
		inv = new InventoryManager(this);
		
		new PlayerListener(this);
		new BlockListener(this);
		new WeatherListener(this);
		new EntityListener(this);
		new InventoryListener(this);
	}

	@Override
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("build")){
			Player player;
			if(sender instanceof Player){
				player = (Player) sender;
				if(player.hasPermission("gp.togglebuild")){
					getPlayerProfile(player).toggleBuild();
					if(getPlayerProfile(player).getBuildStatus()){
						player.sendMessage(tag+ChatColor.GREEN+"Building enabled.");
					}
					else{
						player.sendMessage(tag+ChatColor.RED+"Building disabled.");
					}
				}
				else{
					player.sendMessage(tag+ChatColor.YELLOW+"ERROR: You don't have permission.");
				}
			}
			return true;
		}
		return false;
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
	
	public String getTag(){
		return tag;
	}
	
	public InventoryManager getInvManager(){
		return inv;
	}
	
	public ArrayList<PlayerProfile> getProfiles(){
		return pp;
	}
}
