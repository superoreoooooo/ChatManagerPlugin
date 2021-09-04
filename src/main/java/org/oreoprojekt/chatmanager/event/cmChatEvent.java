package org.oreoprojekt.chatmanager.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.oreoprojekt.chatmanager.command.cmChatCommand;

public class cmChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        String Prefix = ChatColor.AQUA + "[거리채팅]";
        String Prefix2 = ChatColor.WHITE + "[전체채팅]";

        int distance = 20;
        if (cmChatCommand.Insc.contains(p)) {
            e.setCancelled(true);
            for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                if (target.getWorld().getEnvironment() == p.getWorld().getEnvironment()) {
                    double Loc = p.getLocation().distance(target.getLocation());
                    if (Loc <= distance) {
                        target.sendMessage(Prefix + " " + p.getDisplayName() + ChatColor.AQUA + " ▶ " + msg);
                    }
                }
            }
            Bukkit.getConsoleSender().sendMessage(Prefix + " " + p.getDisplayName() + ChatColor.AQUA + " ▶ " + msg);
        }
        else {
            e.setCancelled(true);
            for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                target.sendMessage(Prefix2 + " " + ChatColor.WHITE + p.getDisplayName() + " ▶ " + msg);
            }
            Bukkit.getConsoleSender().sendMessage(Prefix2 + " " + ChatColor.WHITE + p.getDisplayName() + " ▶ " + msg);
        }
    }
}
