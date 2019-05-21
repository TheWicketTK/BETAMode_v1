package com.thewickettk.betamode5812;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.thewickettk.betamode5812.mysql.Connection;
import com.thewickettk.betamode5812.mysql.MySQLStatments;

public class Main extends JavaPlugin implements Listener{
	public static FileConfiguration config;
	public static Boolean LobbyMode;
	
	public void onEnable(){
		File file = new File(getDataFolder() + "config.yml");
		config = getConfig();
		if (!(file.exists())){
			System.out.println("Config not found!");
			this.saveResource("config.yml", false);
			System.out.println("Config Generated!");
		}
		System.out.println("Config Loaded!");
		LobbyMode = getConfig().getBoolean("config.lobbymode");
		if ((LobbyMode != null) && (LobbyMode == false)) {
			getServer().getPluginManager().registerEvents(this, this);
			System.out.println("(BETAMode) Server mode activated!");
		}
		getCommand("beta").setExecutor(new cmds());
		Connection.mysqlConnect();
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event){
		Connection.mysqlConnect();
	String message = null;	
		Player player = (Player) event.getPlayer();
		if (!MySQLStatments.checkBlackList(player.getName())){
			if (MySQLStatments.checkWhitelist(player.getName())){
				String key = MySQLStatments.getkey(player.getName());
				if (MySQLStatments.checkKey(key)){
					Connection.mysqlClose();
					return;
				}
				message = ChatColor.translateAlternateColorCodes('&', Main.config.getString("msg.keyinvalid"));
				player.kickPlayer(message);
				MySQLStatments.remWhitelist(player.getName());
				MySQLStatments.remKey(key);
				Connection.mysqlClose();
				return;
			}
			message = ChatColor.translateAlternateColorCodes('&', Main.config.getString("msg.kicknotwhitelisted"));
			player.kickPlayer(message);
			Connection.mysqlClose();
			return;
		}
		message = ChatColor.translateAlternateColorCodes('&', Main.config.getString("msg.kickblacklisted"));
		player.kickPlayer(message);
		Connection.mysqlClose();
		return;
	}
}