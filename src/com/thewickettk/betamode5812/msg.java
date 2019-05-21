package com.thewickettk.betamode5812;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class msg {
	public static String out;
	
	public static void send(Player player,String msg,Boolean prefix){	
		if (!prefix){
			out = (ChatColor.translateAlternateColorCodes('&', msg));
			player.sendMessage(out);
			return;
		}
		out = (ChatColor.translateAlternateColorCodes('&', Main.config.getString("msg.prefix") + msg));
		player.sendMessage(out);
		return;
	}
	public static void CSsend(CommandSender player,String msg,Boolean prefix){
		if (!prefix){
			out = (ChatColor.translateAlternateColorCodes('&', msg));
			player.sendMessage(out);
			return;
		}
		out = (ChatColor.translateAlternateColorCodes('&', Main.config.getString("msg.prefix") + msg));
		player.sendMessage(out);
		return;
	}
	public static String stringMsg(String msg,Boolean prefix){
		if (!prefix){
			out = (ChatColor.translateAlternateColorCodes('&', msg));
		}
		out = (ChatColor.translateAlternateColorCodes('&', Main.config.getString("msg.prefix") + msg));
		return out;
	}
	public static void adminHelp(CommandSender player){
		if (player.hasPermission("beta.admin")){
		msg.CSsend(player, "&8&m&l+=========+&f &c&lCentrixPvP &b&lBETA &c&6Admin &8&m&l+=========+",false);
		msg.CSsend(player, "&7",false);
		msg.CSsend(player, "&b&lAdmin commands:",false);
		msg.CSsend(player, "&b/beta admin &8- displace commands.",false);
		msg.CSsend(player, "&b/beta admin whitelist (name) &8- adds a persone to the whitelist.",false);		
		msg.CSsend(player, "&b/beta admin remove (name) &8- remove player from whitelist.",false);
		msg.CSsend(player, "&b/beta admin blacklist add/remove (name) &8- blacklist a player.",false);
		msg.CSsend(player, "&b/beta admin key generate&8- generate a key.",false);
		msg.CSsend(player, "&b/beta admin key remove (key) &8- remove key.",false);
		msg.CSsend(player, "&7",false);
		msg.CSsend(player, "&8&l&m+===================================+",false);
		return;
		} 
			msg.CSsend(player, Main.config.getString("msg.nopermission"), true);
			return;
	}
}
