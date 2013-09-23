package com.djdch.bukkit.kickall;

import org.apache.commons.lang.StringUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class of the <b>KickAll</b> plugin for Bukkit.
 * 
 * Implement the kickall command.
 * 
 * @author DjDCH
 */
public class KickAll extends JavaPlugin {
    /**
     * Method executed when the plugin is enable.
     */
    public void onEnable() {
        this.saveDefaultConfig();
    }

    /**
     * Method executed when the plugin is disable.
     */
    public void onDisable() {
    }

    /**
     * Method executed when a command is send to the plugin.
     * 
     * @param sender Contains the CommandSender instance.
     * @param command Contains the Command instance.
     * @param label Contains the alias of the command which was used.
     * @param args Contains the command arguments.
     * @return Return true if a valid command, otherwise false.
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kickall")) {
            if (!sender.hasPermission("kickall.kickall")) {
                sender.sendMessage(ChatColor.RED + "You have no permission to run this command.");

                return true;
            }

            String message = this.getConfig().getString("kickall.message.default");

            if (args.length > 0) {
                message = StringUtils.join(args, " ");
            }

            for (Player player : this.getServer().getOnlinePlayers()) {
                player.kickPlayer(message);
            }

            this.getLogger().info("Kicked all connected players");

            return true;
        }

        return false;
    }
}
