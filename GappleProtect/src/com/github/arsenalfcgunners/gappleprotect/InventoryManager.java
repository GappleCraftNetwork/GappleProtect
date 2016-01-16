package com.github.arsenalfcgunners.gappleprotect;

import java.util.ArrayList;

import org.bukkit.event.inventory.InventoryType;

public class InventoryManager {
	GappleProtect gp;
	ArrayList<InventoryType> inventories;
	
	public InventoryManager(GappleProtect plugin){
		gp = plugin;
		inventories = new ArrayList<InventoryType>();
		addDefaultInventories();
	}
	
	public ArrayList<InventoryType> getinventoriesentories(){
		return inventories;
	}
	
	public void addDefaultInventories(){
		inventories.add(InventoryType.FURNACE);
		inventories.add(InventoryType.BREWING);
		inventories.add(InventoryType.DISPENSER);
		inventories.add(InventoryType.BEACON);
		inventories.add(InventoryType.ENCHANTING);
		inventories.add(InventoryType.ANVIL);
		inventories.add(InventoryType.WORKBENCH);
		inventories.add(InventoryType.DISPENSER);
		inventories.add(InventoryType.ENDER_CHEST);
		inventories.add(InventoryType.MERCHANT);
		inventories.add(InventoryType.DROPPER);
		inventories.add(InventoryType.CHEST);
	}
	
	public void addInventory(InventoryType inv){
		inventories.add(inv);
	}
	
	public void removeInventory(InventoryType inv){
		inventories.remove(inv);
	}
}
