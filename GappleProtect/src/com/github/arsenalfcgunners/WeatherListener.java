package com.github.arsenalfcgunners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener{
	String tag = ChatColor.GRAY+"["+ChatColor.GOLD+"GappleProtect"+ChatColor.GRAY+"] ";
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
