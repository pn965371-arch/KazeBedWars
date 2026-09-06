package com.kaze.bedwars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import java.util.*;
public class Game {
public enum State { WAITING, STARTING, PLAYING, ENDING, RESETTING }
private final Arena arena;
private State state = State.WAITING;
private final List<UUID> players = new ArrayList<>();
private final List<UUID> spectators = new ArrayList<>();
private BukkitTask task;
private int countdown = 10;
public Game(Arena arena) {
this.arena = arena;
}
public Arena getArena() { return arena; }
public State getState() { return state; }
public List<UUID> getPlayers() { return players; }
public void addPlayer(Player player) {
players.add(player.getUniqueId());
player.teleport(arena.getLobbySpawn() != null ? arena.getLobbySpawn() : Bukkit.getWorlds().get(0).getSpawnLocation());
broadcast("§a" + player.getName() + " entrou na partida! (" + players.size() + "/" + arena.getMode() + ")");
if (state == State.WAITING && players.size() >= KazeBedWars.getInstance().getConfig().getInt("settings.min-players-to-start", 2)) {
startCountdown();
}
}
public void removePlayer(Player player) {
players.remove(player.getUniqueId());
spectators.remove(player.getUniqueId());
player.teleport(KazeBedWars.getInstance().getConfigManager().getLobbyLocation() != null ?
KazeBedWars.getInstance().getConfigManager().getLobbyLocation() : Bukkit.getWorlds().get(0).getSpawnLocation());
if (state == State.PLAYING && players.size() <= 1) {
endGame(players.isEmpty() ? null : Bukkit.getPlayer(players.get(0)));
}
}
private void startCountdown() {
state = State.STARTING;
countdown = KazeBedWars.getInstance().getConfig().getInt("settings.countdown-seconds", 10);
task = Bukkit.getScheduler().runTaskTimer(KazeBedWars.getInstance(), () -> {
if (countdown <= 0) {
startGame();
if (task != null) task.cancel();
return;
}
if (countdown <= 5 || countdown == 10) {
broadcast("§eA partida começa em §c" + countdown + " §esegundos!");
}
countdown--;
}, 0L, 20L);
}
private void startGame() {
state = State.PLAYING;
broadcast("§a§lA partida começou! Proteja sua cama!");
}
public void endGame(Player winner) {
state = State.ENDING;
if (winner != null) {
broadcast("§6§lO jogador " + winner.getName() + " venceu a partida na arena " + arena.getName() + "!");
}
Bukkit.getScheduler().runTaskLater(KazeBedWars.getInstance(), this::resetGame, 100L);
}
private void resetGame() {
state = State.RESETTING;
for (UUID uuid : new ArrayList<>(players)) {
Player p = Bukkit.getPlayer(uuid);
if (p != null) removePlayer(p);
}
players.clear();
spectators.clear();
state = State.WAITING;
}
private void broadcast(String message) {
for (UUID uuid : players) {
Player p = Bukkit.getPlayer(uuid);
if (p != null) p.sendMessage(message);
}
}
}
