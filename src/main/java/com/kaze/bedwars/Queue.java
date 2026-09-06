package com.kaze.bedwars;
import org.bukkit.entity.Player;
import java.util.*;
public class Queue {
private final Map<String, List<UUID>> queues = new HashMap<>();
public void joinQueue(Player player, String mode) {
queues.computeIfAbsent(mode, k -> new ArrayList<>()).add(player.getUniqueId());
player.sendMessage("§aVocê entrou na fila para o modo §e" + mode + "§a!");
checkQueue(mode);
}
public void leaveQueue(Player player) {
for (List<UUID> q : queues.values()) {
if (q.remove(player.getUniqueId())) {
player.sendMessage("§cVocê saiu da fila.");
break;
}
}
}
private void checkQueue(String mode) {
List<UUID> q = queues.get(mode);
if (q != null && q.size() >= 2) {
// Matchmaking simples: inicia uma partida na primeira arena disponível para o modo
for (Arena arena : KazeBedWars.getInstance().getArenas().values()) {
if (arena.getMode().equalsIgnoreCase(mode) && arena.isEnabled()) {
Game game = new Game(arena);
for (UUID uuid : new ArrayList<>(q)) {
Player p = org.bukkit.Bukkit.getPlayer(uuid);
if (p != null) {
game.addPlayer(p);
q.remove(uuid);
}
}
break;
}
}
}
}
}
