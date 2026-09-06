package com.kaze.bedwars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
public class Config {
private final KazeBedWars plugin;
private FileConfiguration messages;
private File messagesFile;
public Config(KazeBedWars plugin) {
this.plugin = plugin;
loadMessages();
}
private void loadMessages() {
messagesFile = new File(plugin.getDataFolder(), "messages.yml");
if (!messagesFile.exists()) {
plugin.saveResource("messages.yml", false);
}
messages = YamlConfiguration.loadConfiguration(messagesFile);
}
public String getMessage(String path) {
return messages.getString(path, "&cMensagem não encontrada: " + path).replace("&", "§");
}
public void reload() {
plugin.reloadConfig();
loadMessages();
}
public Location getLobbyLocation() {
String worldName = plugin.getConfig().getString("settings.lobby-world", "world");
String coords = plugin.getConfig().getString("settings.lobby-location", "0.5, 100, 0.5, 0, 0");
World world = Bukkit.getWorld(worldName);
if (world == null) return null;
String[] parts = coords.split(",");
if (parts.length < 5) return null;
try {
double x = Double.parseDouble(parts[0].trim());
double y = Double.parseDouble(parts[1].trim());
double z = Double.parseDouble(parts[2].trim());
float yaw = Float.parseFloat(parts[3].trim());
float pitch = Float.parseFloat(parts[4].trim());
return new Location(world, x, y, z, yaw, pitch);
} catch (NumberFormatException e) {
return null;
}
}
}
