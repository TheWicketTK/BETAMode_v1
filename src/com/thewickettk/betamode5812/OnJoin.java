package com.thewickettk.betamode5812;

import org.bukkit.entity.Player;
import com.thewickettk.betamode5812.mysql.MySQLStatments;

public class OnJoin {
	public static void onJoin(Player player){
		if (MySQLStatments.checkBlackList(player.getName())) {
			if (MySQLStatments.checkWhitelist(player.getName())){
				return;
			} else {
				player.kickPlayer(msg.stringMsg(Main.config.getString("msg.notwhitelisted"), false));
			}
		} else {
			player.kickPlayer(msg.stringMsg(Main.config.getString("msg.notwhitelisted"), false));
		}
	}
}
