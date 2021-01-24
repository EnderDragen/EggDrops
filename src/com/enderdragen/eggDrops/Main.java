package com.enderdragen.eggDrops;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
  
public static Plugin instance;
  
  public void onEnable() {
    instance = (Plugin)this;
    Bukkit.getServer().getPluginManager().registerEvents(new Events(), (Plugin)this);
    getConfig().options().copyDefaults();
    saveDefaultConfig();
  }
  
  public static void sm(Player player, String msg) {
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (label.equalsIgnoreCase("eggdrop")) {
      if (sender.hasPermission("eggdrop.commands")) {
        if (args.length == 0)
          sender.sendMessage(ChatColor.RED + "Try /eggdrop reload"); 
        if (args.length == 1)
          if (args[0].equalsIgnoreCase("reload")) {
            reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "EggDrops reloaded!");
          } else {
            sender.sendMessage(ChatColor.GREEN + "Unknow command. Try /eggdrop reload");
          }  
      } 
    } else {
      sender.sendMessage(ChatColor.RED + "You don't have the permissions.");
    } 
    return false;
  }
}
