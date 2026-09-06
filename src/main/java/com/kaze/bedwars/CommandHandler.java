package com.kaze.bedwars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
public class CommandHandler implements CommandExecutor, TabCompleter {
private final KazeBedWars plugin;
public CommandHandler(KazeBedWars plugin) {
this.plugin = plugin;
}
@Override
public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
if (command.getName().equalsIgnoreCase("party")) {
if (!(sender instanceof Player player)) return true;
if (args.length == 0) {
player.sendMessage("§eUso: /party create, /party invite <player>, /party accept");
return true;
}
if (args[0].equalsIgnoreCase("create")) {
if (plugin.getParties().containsKey(player.getUniqueId())) {
player.sendMessage("§cVocê já está em uma party!");
return true;
}
Party party = new Party(player);
plugin.getParties().put(player.getUniqueId(), party);
player.sendMessage("§aParty criada com sucesso!");
}
return true;
}
if (command.getName().equalsIgnoreCase("bw")) {
if (!(sender instanceof Player player)) return true;
if (args.length == 0) {
player.sendMessage("§cUtilize /bw join <modo> ou /bw stats");
return true;
}
if (args[0].equalsIgnoreCase("join") && args.length > 1) {
plugin.getQueue().joinQueue(player, args[1]);
} else if (args[0].equalsIgnoreCase("leave")) {
plugin.getQueue().leaveQueue(player);
} else if (args[0].equalsIgnoreCase("stats")) {
PlayerData data = plugin.getPlayerDataMap().get(player.getUniqueId());
if (data != null) {
player.sendMessage("§6--- Suas Estatísticas ---");
player.sendMessage("§eNível: §f" + data.getLevel() + " (Prestígio " + data.getPrestige() + ")");
player.sendMessage("§eVitórias: §f" + data.getWins());
player.sendMessage("§eKills: §f" + data.getKills());
}
} else if (args[0].equalsIgnoreCase("arena") && args.length > 2 && player.hasPermission("kazebedwars.admin")) {
String sub = args[1];
String name = args[2];
if (sub.equalsIgnoreCase("create")) {
String mode = args.length > 3 ? args[3] : "Solo";
Arena arena = new Arena(name, mode);
plugin.getArenas().put(name, arena);
player.sendMessage("§aArena §e" + name + " §acriada com sucesso!");
}
}
}
return true;
}
@Override
public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
List<String> completions = new ArrayList<>();
if (command.getName().equalsIgnoreCase("bw")) {
if (args.length == 1) {
completions.add("join");
completions.add("leave");
completions.add("stats");
if (sender.hasPermission("kazebedwars.admin")) {
completions.add("arena");
}
} else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
completions.add("Solo");
completions.add("Duo");
completions.add("Trio");
completions.add("Squad");
}
}
return completions;
}
}
