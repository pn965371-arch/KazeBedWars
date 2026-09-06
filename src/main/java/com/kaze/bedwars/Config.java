package com.kaze.bedwars;
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
}
