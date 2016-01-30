package com.github.arsenalfcgunners.gappleprotect;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener{
	GappleProtect gp;
	
	public InventoryListener(GappleProtect plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		gp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryOpen(InventoryOpenEvent e){
		if(e.getPlayer() instanceof Player){
			Player player = (Player) e.getPlayer();
			Inventory inv = e.getInventory();
			
			if (gp.getInvManager().inventories.contains(inv.getType()) && !gp.getPlayerProfile(player).getBuildStatus()){
				if(inv.getType() != InventoryType.CHEST || inv.getName().equals("container.chest")){
					player.sendMessage(gp.getTag()+"You cannot open the inventory "+inv.getType().toString()+".");
					e.setCancelled(true);
				}
			}
		}
    }
}
