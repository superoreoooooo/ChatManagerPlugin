package org.oreoprojekt.chatmanager;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.oreoprojekt.chatmanager.command.cmChatCommand;
import org.oreoprojekt.chatmanager.event.cmChatEvent;

public final class ChatManager extends JavaPlugin {
    int anc = 1;
    int timeleft = 60;

    public static String prefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "ChatManager" + ChatColor.GRAY + "] " + ChatColor.WHITE;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "ChatManager On");
        //getCommand("discord").setExecutor(new cmDiscordCommand()); > 폐기
        getCommand("lc").setExecutor(new cmChatCommand());
        getServer().getPluginManager().registerEvents(new cmChatEvent(), this);
        timer(true);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.RED + "ChatManager Off");
        timer(false);
    }

    public void Anc() {
        TextComponent message = new TextComponent(ChatColor.GREEN + "【공지사항】" + ChatColor.WHITE + "(채팅을 누르시면 이동합니다) discord.gg/sjeycKRW9F");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/sjeycKRW9F"));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("누르시면 디스코드 주소로 이동합니다.").color(net.md_5.bungee.api.ChatColor.WHITE).create()));
        Bukkit.getConsoleSender().sendMessage(prefix + "공지사항을 전달하였습니다.");
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(ChatColor.GREEN + "【공지사항】" + ChatColor.WHITE + "↓Projekt 서버 디스코드 주소↓");
            target.spigot().sendMessage(message);
        }
    }

    public void timer(boolean tf) {
        if (tf) {
            anc = Bukkit.getScheduler().scheduleAsyncRepeatingTask(ChatManager.getPlugin(ChatManager.class), new BukkitRunnable() {
                @Override
                public void run() {
                    Anc();
                }
            }, 0, timeleft * 20L);
        }
    }
}
