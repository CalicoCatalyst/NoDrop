/**
 * @(#)NoDrop.java        v1.0.0-RELEASE 2016/11/18
 * 
 * Main Class for the NoDrop Plugin. Handles
 * Events and Commands.
 * 
 */
package net.insxnity.nodrop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerDropItemEvent;

public class NoDrop extends JavaPlugin implements Listener {
	
	public Plugin plugin = null;
	public Configuration config = null;
	
	@Override
	public void onEnable() {
		this.plugin = this;
		this.config = new Configuration(this, "config.yml");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(config.enable()) {
			event.setCancelled(!(config.hasPermission(player) || (player.isOp() && config.opOverride() ) ) );
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("np") && args[0].equalsIgnoreCase("reload") 
				&& config.canReload((Player) sender) ) {
			config.reloadDataFile();
			return true;
		} else {
			if (label.equalsIgnoreCase("np") && !args[0].equalsIgnoreCase("reload")) {
				return false;
			} else {
					return true;
				}
		}
	}
	
	@Override
	public void onDisable() {
		this.plugin = null;
		this.config = null;
	}

}
