package com.kaze.bedwars;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public final class KazeBedWars extends JavaPlugin {
private static KazeBedWars instance;
private Config configManager;
private Database database;
private Shop shop;
private final Map<String, Arena> arenas = new HashMap<>();
private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();
private final Map<UUID, Party> parties = new HashMap<>();
private final Queue queue = new Queue();
@Override
public void onEnable() {
instance = this;
saveDefaultConfig();
this.configManager = new Config(this);
this.database = new Database(this);
this.database.init();
this.shop = new Shop(this);
// Register commands & events
CommandHandler cmdHandler = new CommandHandler(this);
if (getCommand("bw") != null) getCommand("bw").setExecutor(cmdHandler);
if (getCommand("party") != null) getCommand("party").setExecutor(cmdHandler);
getServer().getPluginManager().registerEvents(new EventListener(this), this);
if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
new PlaceholderExpansion(this).register();
getLogger().info("PlaceholderAPI integration enabled.");
}
Integration.init(this);
getLogger().info("KazeBedWars enabled successfully!");
}
@Override
public void onDisable() {
if (database != null) {
database.close();
}
getLogger().info("KazeBedWars disabled.");
}
public static KazeBedWars getInstance() { return instance; }
public Config getConfigManager() { return configManager; }
public Database getDatabase() { return database; }
public Shop getShop() { return shop; }
public Map<String, Arena> getArenas() { return arenas; }
public Map<UUID, PlayerData> getPlayerDataMap() { return playerDataMap; }
public Map<UUID, Party> getParties() { return parties; }
public Queue getQueue() { return queue; }
}
