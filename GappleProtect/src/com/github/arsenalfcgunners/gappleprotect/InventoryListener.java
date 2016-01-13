package com.github.arsenalfcgunners.gappleprotect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener{
	GappleProtect gp;
	
	public InventoryListener(GappleProtect plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		gp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOW)
    public void onInventoryOpen(InventoryOpenEvent e){
		if (e.getInventory().getType() == InventoryType.CHEST) {
    		e.setCancelled(true);
		}
   
    	if (e.getInventory().getType() == InventoryType.ANVIL) {
    		e.setCancelled(true);
    	}
   
    	if (e.getInventory().getType() == InventoryType.WORKBENCH) {
    		e.setCancelled(true);
    	}
    	
    	if (e.getInventory().getType() == InventoryType.ENCHANTING) {
    		e.setCancelled(true);
    	}
    	
    	if (e.getInventory().getType() == InventoryType.FURNACE) {
    		e.setCancelled(true);
    	}
    }
}
