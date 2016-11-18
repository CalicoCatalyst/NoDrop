package net.insxnity.nodrop;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.insxnity.api.data.storage.config.YamlConfigFile;

public class Configuration extends YamlConfigFile {
	
	private static String opOverride = "op-override";
	private static String enable = "enable";
	private static String permission = "permission";

	public Configuration(Plugin plugin, String fileName) {
		super(plugin, fileName);
	}
	
	public boolean enable() {
		return (getData().getBoolean(enable));
	}
	
	public boolean hasPermission(Player player) {
		return player.hasPermission(getData().getString(permission));
	}
	
	public boolean opOverride() {
		return (getData().getBoolean(opOverride));
	}
	
	public boolean canReload(Player player) {
		return ( (player.isOp() && getData().getBoolean(opOverride)) || player.hasPermission("Nodrop.*"));
	}

}
