package me.aphelps.plugins.WeatherReducer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.*;

public class WeatherReducer extends JavaPlugin
{
	protected Logger log = Logger.getLogger("Minecraft");
	private WRWeatherListener wrwl;

	
    public void onEnable()
    { 	
    	double num = getConfig().getDouble("chance", 1.0);
    	wrwl = new WRWeatherListener(this, num);
    	
    	this.getServer().getPluginManager().registerEvents(wrwl, this);
    	
        log.info(this + " is now enabled!");
    }

    public void onDisable()
    {
    	saveConfig();
        log.info(this + " is now disabled!");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
    	if(cmd.getName().equalsIgnoreCase("wr"))
    	{
			if(sender.hasPermission("WeatherReducer.wr"))
			{
	    		if(args.length == 2)
	    		{
	    			if(args[0].equals("change"))
	    			{
		    			try
		    			{
		    				double ch = Double.parseDouble(args[1]);
		    				wrwl.setChance(ch);
		    				getConfig().set("chance", ch);
		    				saveConfig();
		    				sender.sendMessage("WeatherReducer: You've changed the chance of weather to " + args[1]);
		    				log.info("WeatherReducer: " + sender.getName() + " changed the chance of weather to " + args[1]);
		    			}
		    			catch(NumberFormatException nfe){sender.sendMessage("WeatherReducer: The number you entered was invalid.");}
		    			catch(Exception e){sender.sendMessage("WeatherReducer: The command you entered was invalid.");}
		        		return true;
	    			}
	        		else
	        		{
	        			sender.sendMessage("WeatherReducer: The command you entered was invalid.");
	        		}
    			}
	    		else if(args.length == 1)
	    		{
	    			if(args[0].equals("show"))
	    			{
	    				sender.sendMessage("WeatherReducer: The current chance of weather is " + getConfig().getString("chance"));
	    				return true;
	    			}
	    			else
	    			{
	    				sender.sendMessage("WeatherReducer: The command you entered was invalid.");
	    			}
	    		}
    			else
    			{
    				sender.sendMessage("WeatherReducer: The command you entered was invalid.");
    			}
    		}
    		else
    		{
    			sender.sendMessage("WeatherReducer: You do not have permission to use this command.");
    		}
    	} 
    	return false; 
    }
}
