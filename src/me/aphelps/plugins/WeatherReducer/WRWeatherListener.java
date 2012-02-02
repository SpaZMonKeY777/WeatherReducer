package me.aphelps.plugins.WeatherReducer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WRWeatherListener implements Listener 
{
	private WeatherReducer plugin;
	private double chance;
	
	public WRWeatherListener(WeatherReducer plugin, double num)
	{
		this.plugin = plugin;
		this.chance = num;
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event)
	{
		if(!event.isCancelled() && event.toWeatherState())
		{
			double d = Math.random();
			if(d > chance)
			{
				event.setCancelled(true);
				plugin.log.info("WeatherReducer: A Weather event was been stopped.");
			}
			else
			{
				plugin.log.info("WeatherReducer: A Weather event has been allowed to occur.");
			}
		}
	}

	public void setChance(double parseInt) 
	{
		this.chance = parseInt;
	}
}
