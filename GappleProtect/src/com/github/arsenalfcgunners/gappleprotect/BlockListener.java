package com.github.arsenalfcgunners.gappleprotect;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener{
	private GappleProtect gp;
	
	public BlockListener(GappleProtect plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		gp = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOWEST) 
	public void onBlockPlace(BlockPlaceEvent e){
		if(e.getBlock().getType() != Material.FIRE){
			if(!gp.getPlayerProfile(e.getPlayer()).getBuildStatus()){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST) 
	public void onBlockBreak(BlockBreakEvent e){
		if(e.getBlock().getType() != Material.FIRE){
	        if(!gp.getPlayerProfile(e.getPlayer()).getBuildStatus()){
				e.setCancelled(true);
	        }
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST) 
    public void onBlockIgnite(BlockIgniteEvent e){
        if (e.getCause() != IgniteCause.FLINT_AND_STEEL){
            e.setCancelled(true);
        }
    }
	
	@EventHandler(priority = EventPriority.LOWEST) 
    public void onBlockBurn(BlockBurnEvent e){
		for (BlockFace face : BlockFace.values())
        {
            Block f = e.getBlock().getRelative(face);
            if (f.getType() == Material.FIRE)
            {
                f.setType(Material.AIR);
            }
        }
		e.setCancelled(true);
    }
}
