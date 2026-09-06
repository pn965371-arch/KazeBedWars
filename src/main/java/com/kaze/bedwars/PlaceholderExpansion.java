package com.kaze.bedwars;
import me.clip.placeholderapi.expansion.PlaceholderExpansionBase;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
public class PlaceholderExpansion extends me.clip.placeholderapi.expansion.PlaceholderExpansion {
private final KazeBedWars plugin;
public PlaceholderExpansion(KazeBedWars plugin) {
this.plugin = plugin;
}
@Override
public @NotNull String getIdentifier() { return "bedwars"; }
@Override
public @NotNull String getAuthor() { return "Kaze Network"; }
@Override
public @NotNull String getVersion() { return "1.0.0"; }
@Override
public boolean persist() { return true; }
@Override
public String onPlaceholderRequest(Player player, @NotNull String params) {
if (player == null) return "";
PlayerData data = plugin.getPlayerDataMap().get(player.getUniqueId());
if (data == null) return "0";
if (params.equalsIgnoreCase("level")) return String.valueOf(data.getLevel());
if (params.equalsIgnoreCase("xp")) return String.valueOf(data.getXp());
if (params.equalsIgnoreCase("wins")) return String.valueOf(data.getWins());
if (params.equalsIgnoreCase("kills")) return String.valueOf(data.getKills());
return null;
}
}
