package com.github.arsenalfcgunners.gappleprotect;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class PlayerListener implements Listener{
	GappleProtect gp;
	
	public PlayerListener(GappleProtect plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		gp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent e){
		gp.getProfiles().add(new PlayerProfile(e.getPlayer(), gp));
		e.setJoinMessage(null);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onQuit(PlayerQuitEvent e){
		gp.removePlayer(e.getPlayer());
		e.setQuitMessage(null);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBedEnter(PlayerBedEnterEvent e){
		e.getPlayer().sendMessage(gp.getTag()+"Beds are disabled.");
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onAchievement(PlayerAchievementAwardedEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onShear(PlayerShearEntityEvent e){
		e.getPlayer().sendMessage(gp.getTag()+"Shears are disabled.");
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void bucketFillEvent(PlayerBucketFillEvent e){
		Player player = e.getPlayer();
		if(!gp.getPlayerProfile(player).getBuildStatus()){
			player.sendMessage(gp.getTag()+"Buckets are disabled.");
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onDrop(PlayerDropItemEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPickUp(PlayerPickupItemEvent e){
		Player player = e.getPlayer();
		if(!gp.getPlayerProfile(player).getBuildStatus()){
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onXp(PlayerExpChangeEvent e){
		e.setAmount(0);
		e.getPlayer().setExp(0);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onInteract(PlayerInteractEvent e){
		Player player = e.getPlayer();
		if(e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.SOIL){
            e.setCancelled(true);
    	}
		
		if(!gp.getPlayerProfile(player).getBuildStatus()){      
	        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.TRAP_DOOR){
	        	e.setCancelled(true);
	    	}
	        
	        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.FENCE_GATE){
	        	e.setCancelled(true);
	    	}
	        
	        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && player.getItemInHand().getType() == Material.WATER_BUCKET){
				player.sendMessage(gp.getTag()+"Buckets are disabled.");
	        	e.setCancelled(true);
	    	}
	        
	        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && player.getItemInHand().getType() == Material.LAVA_BUCKET){
				player.sendMessage(gp.getTag()+"Buckets are disabled.");
	        	e.setCancelled(true);
	    	}
		}
	}
	
}
