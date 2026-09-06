package com.kaze.bedwars;
import org.bukkit.Location;
import java.util.HashMap;
import java.util.Map;
public class Arena {
private final String name;
private final String mode;
private boolean enabled = false;
private Location lobbySpawn;
private Location spectatorSpawn;
private final Map<String, Location> teamSpawns = new HashMap<>();
private final Map<String, Location> teamBeds = new HashMap<>();
public Arena(String name, String mode) {
this.name = name;
this.mode = mode;
}
public String getName() { return name; }
public String getMode() { return mode; }
public boolean isEnabled() { return enabled; }
public void setEnabled(boolean enabled) { this.enabled = enabled; }
public Location getLobbySpawn() { return lobbySpawn; }
public void setLobbySpawn(Location lobbySpawn) { this.lobbySpawn = lobbySpawn; }
public Location getSpectatorSpawn() { return spectatorSpawn; }
public void setSpectatorSpawn(Location spectatorSpawn) { this.spectatorSpawn = spectatorSpawn; }
public Map<String, Location> getTeamSpawns() { return teamSpawns; }
public Map<String, Location> getTeamBeds() { return teamBeds; }
public boolean isComplete() {
return lobbySpawn != null && spectatorSpawn != null && !teamSpawns.isEmpty() && !teamBeds.isEmpty();
}
}
