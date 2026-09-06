package com.kaze.bedwars;
import org.bukkit.Bukkit;
public class Integration {
public static void init(KazeBedWars plugin) {
if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
plugin.getLogger().info("Vault hook successfully initialized.");
}
if (Bukkit.getPluginManager().getPlugin("DecentHolograms") != null) {
plugin.getLogger().info("DecentHolograms hook successfully initialized.");
}
}
}
