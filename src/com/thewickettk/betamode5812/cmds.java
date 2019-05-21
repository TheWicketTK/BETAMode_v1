package com.thewickettk.betamode5812;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.thewickettk.betamode5812.mysql.Connection;
import com.thewickettk.betamode5812.mysql.MySQLStatments;

public class cmds implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (cmd.getName().equalsIgnoreCase("beta")){
			Connection.mysqlConnect();
			// args[0] args[1] args[2]
			if (args.length == 0){
                msg.CSsend(sender, "&8&m&l+=========+&f &c&lCentrixPvP &b&lBETA &8&m&l+=========+",false);
                msg.CSsend(sender, "&7",false);
                msg.CSsend(sender, "&bWe offer BETA testing to the community to help us find ",false);
                msg.CSsend(sender, "&bbugs/glitches on server before release. This is are new ",false);
                msg.CSsend(sender, "&bof improving the server are servers and user experience.",false);
                msg.CSsend(sender, "&bFor more info go visit: ",false);
                msg.CSsend(sender, "&7",false);
                msg.CSsend(sender, "&8&l&m+===================================+",false);
                return false;
			}
			if (args.length == 1){
				if (args[0].equalsIgnoreCase("key")){
					if (sender.hasPermission("beta.key")){
						msg.CSsend(sender,Main.config.getString("msg.needkey"),true);
						return false;	
					}
					msg.CSsend(sender, Main.config.getString("msg.nopermission"), true);
					return false;
				}
				if (args[0].equalsIgnoreCase("admin")){
					if (sender.hasPermission("beta.key")){
						msg.adminHelp(sender);
						return false;
					}
					msg.CSsend(sender, Main.config.getString("msg.nopermission"), true);
					return false;
				}
				msg.CSsend(sender, Main.config.getString("msg.commandnotfound"), true);
				return false;
			}
			if (args.length == 2){
				if (args[0].equalsIgnoreCase("key")){
					if (sender.hasPermission("beta.key")){
						String key = args[1];
						if ((key.length() == Main.config.getInt("config.keylenght")) && (MySQLStatments.AcheckKey(key)) && (args[1].matches("[a-zA-Z0-9]*"))){
							if (!MySQLStatments.checkBlackList(sender.getName())){
								if (!MySQLStatments.checkWhitelist(sender.getName())){
									MySQLStatments.LinkPlayer(sender.getName(), key);
                                    msg.CSsend(sender, Main.config.getString("msg.playerlinksucess"), true);
                                    return false;
								}
								msg.CSsend(sender, Main.config.getString("msg.alleardywhitelisted"), true);
								return false;
							}
							msg.CSsend(sender, Main.config.getString("msg.kickblacklisted"), true);
							return false;
						}
                        msg.CSsend(sender, Main.config.getString("msg.keynotvalid"), true);
                        return false;
					}
					msg.CSsend(sender, Main.config.getString("msg.nopermission"), true);
					return false;
				}
				if (args[0].equalsIgnoreCase("admin")){
					if (sender.hasPermission("beta.admin")){
						if (args[1].equalsIgnoreCase("whitelist")) {
                            msg.adminHelp(sender);
                            return false;
                        }
						if (args[1].equalsIgnoreCase("blacklist")) {
                            msg.adminHelp(sender);
                            return false;
                        }
						if (args[1].equalsIgnoreCase("remove")) {
                            msg.adminHelp(sender);
                            return false;
                        }
						if (args[1].equalsIgnoreCase("key")) {
                            msg.adminHelp(sender);
                            return false;
                        }
						if (args[1].equalsIgnoreCase("list")){
							msg.CSsend(sender, "&cWhitelisted user are&7:&9" + MySQLStatments.getWhitelist() , true);
							return false;
						}
						msg.CSsend(sender, Main.config.getString("msg.commandnotfound"), true);
						return false;
					}
					msg.CSsend(sender, Main.config.getString("msg.nopermission"), true);
					return false;
				}
				msg.CSsend(sender, Main.config.getString("msg.commandnotfound"), true);
				return false;
			}
			if (args.length == 3){
				if (sender.hasPermission("beta.admin")){
					if (args[0].equalsIgnoreCase("admin")){
						if (args[1].equalsIgnoreCase("blacklist")) {
							msg.adminHelp(sender);
							return false;
						}
						if (args[1].equalsIgnoreCase("remove")) {
							if (MySQLStatments.checkWhitelist(args[2])){
								MySQLStatments.remWhitelist(args[2]);
								msg.CSsend(sender, Main.config.getString("msg.whitelistremove"), true);
							}
							msg.CSsend(sender, Main.config.getString("msg.isnotwhitelisted"), true);
							return false;
						}			
						if (args[1].equalsIgnoreCase("whitelist")) {
							if (!MySQLStatments.checkWhitelist(args[2])){
								String key = GenKey.genRandomKey();
								while (MySQLStatments.checkKey(key)){
									key = GenKey.genRandomKey();
								}
								MySQLStatments.addKey(key);
								MySQLStatments.LinkPlayer(args[2], key);
								msg.CSsend(sender, Main.config.getString("msg.whitelistadded") + key, true);
								return false;
							}
							msg.CSsend(sender, Main.config.getString("msg.iswhitelisted"), true);
							return false;
						}
						if (args[1].equalsIgnoreCase("key")){
							if (args[2].equalsIgnoreCase("generate")){
								String key = GenKey.genRandomKey();
								while (MySQLStatments.checkKey(key)){
									key = GenKey.genRandomKey();
								}
								MySQLStatments.addKey(key);
								msg.CSsend(sender, Main.config.getString("msg.generatekey") + " " + key , true);
								return false;
							}
							if (args[2].equalsIgnoreCase("remove")){
								msg.adminHelp(sender);
							}
						}
						msg.CSsend(sender, Main.config.getString("msg.commandnotfound"), true);
						return false;
					}	
				}
				msg.CSsend(sender, Main.config.getString("msg.nopermission"), true);
				return false;	
			}
			if (args.length == 4){
				if (sender.hasPermission("beta.admin")){
					if (args[0].equals("admin")){
						if (args[1].equalsIgnoreCase("key")){
							if (args[2].equalsIgnoreCase("remove")){
								String key = args[3];
								if ((key.length() == Main.config.getInt("config.keylenght")) && (MySQLStatments.checkKey(key)) && (args[3].matches("[a-zA-Z0-9]*"))){
									MySQLStatments.remKey(key);
									msg.CSsend(sender, Main.config.getString("msg.removedkey"), true);
									return false;
								}
								msg.CSsend(sender, Main.config.getString("msg.keynotvalid"), true);
								return false;
							}
							msg.adminHelp(sender);
							return false;						
						}
						if (args[1].equalsIgnoreCase("blacklist")){
							if (args[2].equalsIgnoreCase("remove")){
								if (MySQLStatments.checkBlackList(args[3])) {
									MySQLStatments.remBlacklist(args[3]);
									msg.CSsend(sender, Main.config.getString("msg.removedblacklisted"), true);
									return false;
								}
								msg.CSsend(sender, Main.config.getString("msg.isnotblacklisted"), true);
							}
							if (args[2].equalsIgnoreCase("add")){
								if (!MySQLStatments.checkBlackList(args[3])) {
									MySQLStatments.addBlacklist(args[3]);
									msg.CSsend(sender, Main.config.getString("msg.blacklisted"), true);
									Player p = Bukkit.getPlayer(args[3]);
									if (p != null){	
										if (!Main.LobbyMode){
											String message = ChatColor.translateAlternateColorCodes('&', Main.config.getString("msg.kickblacklisted"));
											p.kickPlayer(message);
										}
									}
								}
								 msg.CSsend(sender, Main.config.getString("msg.isnotblacklisted"), true);
								 return false;
							}
						}
						msg.adminHelp(sender);
						return false;
					}
					msg.adminHelp(sender);
					return false;
				}
				msg.CSsend(sender, Main.config.getString("msg.nopermission"), true);
				return false;
			}
			if (args.length == 2 && !(sender instanceof Player)){
				if (args[0].equalsIgnoreCase("cosnole")){
					if (args[1].equalsIgnoreCase("ResetWhitelist")){
						MySQLStatments.resetWhitelist();
						return false;
					}
					if (args[1].equalsIgnoreCase("ResetKey")){
						MySQLStatments.resetkeys();
						return false;
					}
				}
			}
			Connection.mysqlClose();
		}
		return false;
	}

}
