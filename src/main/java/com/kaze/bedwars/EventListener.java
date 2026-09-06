package com.kaze.bedwars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
public class EventListener implements Listener {
private final KazeBedWars plugin;
public EventListener(KazeBedWars plugin) {
this.plugin = plugin;
}
@EventHandler
public void onJoin(PlayerJoinEvent event) {
Player player = event.getPlayer();
plugin.getDatabase().loadPlayer(player.getUniqueId());
plugin.getPlayerDataMap().put(player.getUniqueId(), plugin.getDatabase().loadPlayer(player.getUniqueId()));
}
@EventHandler
public void onQuit(PlayerQuitEvent event) {
Player player = event.getPlayer();
plugin.getPlayerDataMap().remove(player.getUniqueId());
plugin.getQueue().leaveQueue(player);
}
}
