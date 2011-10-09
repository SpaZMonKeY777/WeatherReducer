package me.aphelps.plugins.WhoNeedsWeather;

import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherListener;

public class WWWeatherListener extends WeatherListener 
{
	private WhoNeedsWeather plugin;
	
	public WWWeatherListener(WhoNeedsWeather plugin)
	{
		this.plugin = plugin;
	}
	
	public void onWeatherChange(WeatherChangeEvent event)
	{
		if(!event.isCancelled() && event.toWeatherState())
		{
			double d = Math.random();
			if(d < .5)
			{
				event.setCancelled(true);
				plugin.log.info("A Weather event was been stopped.");
			}
		}
	}
}
