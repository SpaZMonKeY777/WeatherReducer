package me.aphelps.plugins.WeatherReducer;

import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherListener;

public class WRWeatherListener extends WeatherListener 
{
	private WeatherReducer plugin;
	private double chance;
	
	public WRWeatherListener(WeatherReducer plugin, double num)
	{
		this.plugin = plugin;
		this.chance = num;
	}
	
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
