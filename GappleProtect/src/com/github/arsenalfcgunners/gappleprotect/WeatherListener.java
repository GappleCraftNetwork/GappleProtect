package com.github.arsenalfcgunners.gappleprotect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener{
	GappleProtect gp;
	
	public WeatherListener(GappleProtect plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		gp = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onWeatherChange(WeatherChangeEvent e){
		e.setCancelled(true);
	}
}
