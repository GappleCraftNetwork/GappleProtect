package com.github.arsenalfcgunners.gappleprotect;

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
	GappleProtect gp;
	
	public EntityListener(GappleProtect plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		gp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOWEST) 
	public void onArrowHit(ProjectileHitEvent e){
		 if(e.getEntity() instanceof Arrow){
			 Arrow arrow = (Arrow) e.getEntity();
			 arrow.remove();
		 }
	}
	
	@EventHandler(priority = EventPriority.LOWEST) 
	public void onDoorBreak(EntityBreakDoorEvent e){
		if(e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
			if(!gp.getPlayerProfile(player).getBuildStatus()){
				player.sendMessage(gp.tag+"You cannot break that.");
				e.setCancelled(true);
			}
		}
		else{
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST) 
	public void onExpolode(EntityExplodeEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onFoodLevelChange (FoodLevelChangeEvent e) {
	    if(e.getEntity() instanceof Player) {
	        Player player = (Player)e.getEntity();
	        player.setFoodLevel(20);
	        e.setCancelled(true);
	    }
	}
}
