package com.github.arsenalfcgunners.gappleprotect;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EntityListener implements Listener{
	String tag = ChatColor.GRAY+"["+ChatColor.GOLD+"GappleProtect"+ChatColor.GRAY+"] ";
	GappleProtect gp;
	
	public EntityListener(GappleProtect plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		gp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOW) 
	public void onArrowHit(ProjectileHitEvent e){
		 if(e.getEntity() instanceof Arrow){
			 Arrow arrow = (Arrow) e.getEntity();
			 arrow.remove();
		 }
	}
	
	@EventHandler(priority = EventPriority.LOW) 
	public void onDoorBreak(EntityBreakDoorEvent e){
		if(e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
			if(!gp.getPlayerProfile(player).getBuildStatus()){
				player.sendMessage(tag+"You cannot break that.");
				e.setCancelled(true);
			}
		}
		else{
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOW) 
	public void onExpolode(EntityExplodeEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onFoodLevelChange (FoodLevelChangeEvent e) {
	    if(e.getEntity() instanceof Player) {
	        Player player = (Player)e.getEntity();
	        player.setFoodLevel(20);
	        e.setCancelled(true);
	    }
	}
}
