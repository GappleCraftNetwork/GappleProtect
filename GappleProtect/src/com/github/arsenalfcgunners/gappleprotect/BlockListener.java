package com.github.arsenalfcgunners.gappleprotect;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener{
	String tag = ChatColor.GRAY+"["+ChatColor.GOLD+"GappleProtect"+ChatColor.GRAY+"] ";
	GappleProtect gp;
	
	public BlockListener(GappleProtect plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		gp = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onBlockPlace(BlockPlaceEvent e){
		if(e.getBlock().getType() != Material.FIRE){
			if(!gp.getPlayerProfile(e.getPlayer()).getBuildStatus()){
				e.setCancelled(true);
			}
		}
		if(e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOD && e.getBlock().getRelative(BlockFace.DOWN).getData() == (short) 1){
			e.getPlayer().sendMessage(tag+ChatColor.YELLOW+"ERROR: You cannot place fire near spawn.");
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onBlockBreak(BlockBreakEvent e) {
		if(e.getBlock().getType() != Material.FIRE){
	        if(!gp.getPlayerProfile(e.getPlayer()).getBuildStatus()){
				e.setCancelled(true);
	        }
		}
	}
}
