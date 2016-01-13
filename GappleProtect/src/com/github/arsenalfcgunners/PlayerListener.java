package com.github.arsenalfcgunners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class PlayerListener implements Listener{
	String tag = ChatColor.GRAY+"["+ChatColor.GOLD+"GappleProtect"+ChatColor.GRAY+"]+ "+ChatColor.YELLOW;
	GappleProtect gp;
	
	public PlayerListener(GappleProtect plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		gp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onJoin(PlayerJoinEvent e){
		gp.pp.add(new PlayerProfile(e.getPlayer(), gp));
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onQuit(PlayerQuitEvent e){
		gp.removePlayer(e.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onBedEnter(PlayerBedEnterEvent e){
		e.getPlayer().sendMessage(tag+"Beds are disabled.");
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onAchievement(PlayerAchievementAwardedEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onShear(PlayerShearEntityEvent e){
		e.getPlayer().sendMessage(tag+"Shears are disabled.");
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void bucketFillEvent(PlayerBucketFillEvent e){
		Player player = e.getPlayer();
		if(!gp.getPlayerProfile(player).getBuildStatus()){
			player.sendMessage(tag+"Buckets are disabled.");
			e.setCancelled(true);
		}
	}
	
	public void bucketEmptyEvent(PlayerBucketEmptyEvent e){
		Player player = e.getPlayer();
		if(!gp.getPlayerProfile(player).getBuildStatus()){
			player.sendMessage(tag+"Buckets are disabled.");
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onDrop(PlayerDropItemEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPickUp(PlayerPickupItemEvent e){
		Player player = e.getPlayer();
		if(!gp.getPlayerProfile(player).getBuildStatus()){
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onXp(PlayerExpChangeEvent e){
		e.getPlayer().setExp(0);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onFish(PlayerFishEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOW)
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
		}
	}
	
}
