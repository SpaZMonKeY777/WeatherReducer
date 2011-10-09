package me.aphelps.plugins.WhoNeedsWeather;

//import org.bukkit.command.Command;
//import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.*;

public class WhoNeedsWeather extends JavaPlugin
{
	protected Logger log = Logger.getLogger("Minecraft");
	

	
    public void onEnable()
    { 	
    	PluginManager pm = this.getServer().getPluginManager();
    	//WWPlayerListener playerListener = new WWPlayerListener(this);
    	//WWWeatherListener weatherListener = new WWWeatherListener(this);
    	
    	//pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
    	
    	pm.registerEvent(Event.Type.WEATHER_CHANGE, new WWWeatherListener(this), Event.Priority.Normal, this);
    	
        log.info(this + " is now enabled!");
    }

    public void onDisable()
    {
        log.info(this + " is now disabled!");
    }
    
    /*public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
    	if(cmd.getName().equalsIgnoreCase("wr")){ // If the player typed /basic then do the following...
    		this.getServer().broadcastMessage("Command");
    		return true;
    	} 
    	return false; 
    }*/
}
