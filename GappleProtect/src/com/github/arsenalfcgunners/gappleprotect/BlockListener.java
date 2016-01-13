package com.github.arsenalfcgunners.gappleprotect;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener{
	GappleProtect gp;
	
	public BlockListener(GappleProtect plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		gp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOW) 
	public void onBlockPlace(BlockPlaceEvent e){
		if(e.getBlock().getType() != Material.FIRE){
			if(!gp.getPlayerProfile(e.getPlayer()).getBuildStatus()){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOW) 
	public void onBlockBreak(BlockBreakEvent e) {
		if(e.getBlock().getType() != Material.FIRE){
	        if(!gp.getPlayerProfile(e.getPlayer()).getBuildStatus()){
				e.setCancelled(true);
	        }
		}
	}
}
