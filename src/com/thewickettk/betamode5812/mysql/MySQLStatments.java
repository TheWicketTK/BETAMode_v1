package com.thewickettk.betamode5812.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.thewickettk.betamode5812.Main;

public class MySQLStatments {
	private static String sql;
	private static boolean check;

	public static boolean AcheckKey(String key){
		check = false;
		Connection.mysqlConnect();
        try {
    		sql = "SELECT * FROM " + Main.config.getString("table.keys")+ " WHERE ukey='"+ key +"'";
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				if (rs.getInt("auth") == 0){
					statement.close();
					rs.close();
					check = true;
				}
			}
			statement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	public static boolean checkKey(String key){
		check = false;
		Connection.mysqlConnect();
        try {
    		sql = "SELECT * FROM " + Main.config.getString("table.keys")+ " WHERE ukey='"+ key +"'";
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
					statement.close();
					rs.close();
					check = true;
			}
			statement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return check;
	}
	public static String getkey(String user){
		String key = null;	
		Connection.mysqlConnect();
        try {
    		sql = "SELECT * FROM " + Main.config.getString("table.whitelist")+ " WHERE username='"+ user +"'";
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				key = rs.getString("ukey");
				statement.close();
				rs.close();	
			}
			statement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return key;
	}
	public static boolean checkWhitelist(String name){
		sql = "SELECT * FROM " + Main.config.getString("table.whitelist") + " WHERE username='" + name + "'";
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				statement.close();
				rs.close();
				return true;
			}
			statement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean checkBlackList(String string){
		sql = "SELECT * FROM " + Main.config.getString("table.blacklist") + " WHERE username='" + string + "'";
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
			statement.close();
			rs.close();
				return true;
			}
			statement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void addWhitelist(String name,String key){
		sql = "INSERT INTO " + Main.config.getString("table.whitelist") + " (username,ukey) VALUES ('" + name + "','" + key +"')";
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void remWhitelist(String name){
		sql = "DELETE FROM " + Main.config.getString("table.whitelist") + " WHERE username='" + name +"'";
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void addBlacklist(String name){
		sql = "INSERT INTO " + Main.config.getString("table.blacklist") + " (username) VALUES ('"+ name +"')";
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void remBlacklist(String name){
		sql = "DELETE FROM " + Main.config.getString("table.blacklist") + " WHERE username='" + name +"'";
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void addKey(String key){
		sql = "INSERT INTO " + Main.config.getString("table.keys") + " (ukey, auth) VALUES ('"+ key + "','" + 0 +"')";
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void remKey(String key){
		sql = "DELETE FROM " + Main.config.getString("table.keys") + " WHERE ukey='"+ key + "'";
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void LinkPlayer(String name,String key){
		sql = "UPDATE " + Main.config.getString("table.keys") + " SET auth=" + 1 +" WHERE ukey='" + key + "'";
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        addWhitelist(name, key);
	}
	public static void resetkeys(){
		sql = "DELETE FROM " + Main.config.getString("table.keys");
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			statement.executeQuery();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void resetWhitelist(){
        sql = "DELETE FROM " + Main.config.getString("table.whitelist");
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			statement.executeQuery();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getWhitelist(){
		String out = "";
        sql = "SELECT username FROM " + Main.config.getString("table.whitelist");
        try {
			java.sql.PreparedStatement statement = Connection.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				out = out + " " + rs.getString("username");
			}
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}
}
