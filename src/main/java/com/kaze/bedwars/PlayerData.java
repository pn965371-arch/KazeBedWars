package com.kaze.bedwars;
import java.util.UUID;
public class PlayerData {
private final UUID uuid;
private int wins = 0;
private int losses = 0;
private int kills = 0;
private int finalKills = 0;
private int deaths = 0;
private int bedsBroken = 0;
private int gamesPlayed = 0;
private int winstreak = 0;
private int xp = 0;
private int level = 1;
private int prestige = 0;
public PlayerData(UUID uuid) {
this.uuid = uuid;
}
public UUID getUuid() { return uuid; }
public int getWins() { return wins; }
public void setWins(int wins) { this.wins = wins; }
public int getLosses() { return losses; }
public void setLosses(int losses) { this.losses = losses; }
public int getKills() { return kills; }
public void setKills(int kills) { this.kills = kills; }
public int getFinalKills() { return finalKills; }
public void setFinalKills(int finalKills) { this.finalKills = finalKills; }
public int getDeaths() { return deaths; }
public void setDeaths(int deaths) { this.deaths = deaths; }
public int getBedsBroken() { return bedsBroken; }
public void setBedsBroken(int bedsBroken) { this.bedsBroken = bedsBroken; }
public int getGamesPlayed() { return gamesPlayed; }
public void setGamesPlayed(int gamesPlayed) { this.gamesPlayed = gamesPlayed; }
public int getWinstreak() { return winstreak; }
public void setWinstreak(int winstreak) { this.winstreak = winstreak; }
public int getXp() { return xp; }
public void setXp(int xp) { this.xp = xp; }
public int getLevel() { return level; }
public void setLevel(int level) { this.level = level; }
public int getPrestige() { return prestige; }
public void setPrestige(int prestige) { this.prestige = prestige; }
public void addXp(int amount) {
this.xp += amount;
int required = level * 1000;
if (this.xp >= required) {
this.xp -= required;
this.level++;
if (this.level > 100) {
this.level = 1;
this.prestige++;
}
}
}
}
