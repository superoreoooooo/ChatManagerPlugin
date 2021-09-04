package org.oreoprojekt.chatmanager.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.oreoprojekt.chatmanager.ChatManager;

import java.util.ArrayList;


public class cmChatCommand implements CommandExecutor {

    public static ArrayList<Player> Insc = new ArrayList<Player>();

    private ChatManager main;
    String Prefix = ChatManager.prefix;
    String Enabled = ChatColor.translateAlternateColorCodes('&', Prefix + ChatColor.AQUA + "&6거리 채팅이 활성화 되었습니다.");
    String Disabled = ChatColor.translateAlternateColorCodes('&', Prefix + ChatColor.AQUA + "&6거리 채팅이 비활성화 되었습니다.");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("lc")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("콘솔에서는 사용하실 수 없습니다.");
                return false;
            }
            Player p = (Player) sender;

            if (args.length == 0) {
                if (Insc.contains(p)) {
                    Insc.remove(p);
                    p.sendMessage(Disabled);
                    return true;
                } else {
                    Insc.add(p);
                    p.sendMessage(Enabled);
                    return true;
                }
            }
        }
        return false;
    }
}
