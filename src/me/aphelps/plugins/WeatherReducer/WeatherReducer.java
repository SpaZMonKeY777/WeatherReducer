package me.aphelps.plugins.WeatherReducer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import java.util.logging.*;

public class WeatherReducer extends JavaPlugin
{
	protected Logger log = Logger.getLogger("Minecraft");
	protected static Configuration CONFIG;
	private WRWeatherListener wrwl;

	
    public void onEnable()
    { 	
    	CONFIG = getConfiguration();
    	double num = CONFIG.getDouble("chance", 1.0);
    	wrwl = new WRWeatherListener(this, num);
    	PluginManager pm = this.getServer().getPluginManager();
    	
    	pm.registerEvent(Event.Type.WEATHER_CHANGE, wrwl, Event.Priority.Normal, this);
    	
        log.info(this + " is now enabled!");
    }

    public void onDisable()
    {
    	CONFIG.save();
        log.info(this + " is now disabled!");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
    	if(cmd.getName().equalsIgnoreCase("wr"))
    	{
    		if(args.length == 1)
    		{
    			if(sender.hasPermission("WeatherReducer.wr"))
    			{
	    			try
	    			{
	    				double ch = Double.parseDouble(args[0]);
	    				wrwl.setChance(ch);
	    				CONFIG.setProperty("chance", ch);
	    				CONFIG.save();
	    				sender.sendMessage("WeatherReducer: You've changed the chance of weather to " + args[0]);
	    				log.info("WeatherReducer: " + sender.getName() + " changed the chance of weather to " + args[0]);
	    			}
	    			catch(NumberFormatException nfe){sender.sendMessage("WeatherReducer: The number you entered was invalid.");}
	    			catch(Exception e){sender.sendMessage("WeatherReducer: The command you entered was invalid.");}
	        		return true;
    			}
    			else
    			{
    				sender.sendMessage("WeatherReducer: You do not have permission to use this command.");
    			}
    		}
    		else
    		{
    			sender.sendMessage("WeatherReducer: The command you entered was invalid.");
    		}
    	} 
    	return false; 
    }
}
