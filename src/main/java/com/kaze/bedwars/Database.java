package com.kaze.bedwars;
import org.bukkit.Bukkit;
import java.io.File;
import java.sql.*;
import java.util.UUID;
public class Database {
private final KazeBedWars plugin;
private Connection connection;
public Database(KazeBedWars plugin) {
this.plugin = plugin;
}
public void init() {
try {
File dbFile = new File(plugin.getDataFolder(), "database.db");
if (!dbFile.exists()) {
dbFile.getParentFile().mkdirs();
dbFile.createNewFile();
}
Class.forName("org.sqlite.JDBC");
connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
try (Statement stmt = connection.createStatement()) {
stmt.execute("CREATE TABLE IF NOT EXISTS bw_stats (" +
"uuid VARCHAR(36) PRIMARY KEY, " +
"wins INT DEFAULT 0, " +
"losses INT DEFAULT 0, " +
"kills INT DEFAULT 0, " +
"final_kills INT DEFAULT 0, " +
"deaths INT DEFAULT 0, " +
"beds_broken INT DEFAULT 0, " +
"games_played INT DEFAULT 0, " +
"winstreak INT DEFAULT 0, " +
"xp INT DEFAULT 0, " +
"level INT DEFAULT 1, " +
"prestige INT DEFAULT 0)");
}
} catch (Exception e) {
plugin.getLogger().severe("Falha ao inicializar o banco de dados SQLite: " + e.getMessage());
}
}
public PlayerData loadPlayer(UUID uuid) {
PlayerData data = new PlayerData(uuid);
String sql = "SELECT * FROM bw_stats WHERE uuid = ?";
try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
pstmt.setString(1, uuid.toString());
ResultSet rs = pstmt.executeQuery();
if (rs.next()) {
data.setWins(rs.getInt("wins"));
data.setLosses(rs.getInt("losses"));
data.setKills(rs.getInt("kills"));
data.setFinalKills(rs.getInt("final_kills"));
data.setDeaths(rs.getInt("deaths"));
data.setBedsBroken(rs.getInt("beds_broken"));
data.setGamesPlayed(rs.getInt("games_played"));
data.setWinstreak(rs.getInt("winstreak"));
data.setXp(rs.getInt("xp"));
data.setLevel(rs.getInt("level"));
data.setPrestige(rs.getInt("prestige"));
} else {
savePlayerAsync(data);
}
} catch (SQLException e) {
plugin.getLogger().warning("Erro ao carregar dados do jogador " + uuid + ": " + e.getMessage());
}
return data;
}
public void savePlayerAsync(PlayerData data) {
Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
String sql = "INSERT OR REPLACE INTO bw_stats (uuid, wins, losses, kills, final_kills, deaths, beds_broken, games_played, winstreak, xp, level, prestige) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
pstmt.setString(1, data.getUuid().toString());
pstmt.setInt(2, data.getWins());
pstmt.setInt(3, data.getLosses());
pstmt.setInt(4, data.getKills());
pstmt.setInt(5, data.getFinalKills());
pstmt.setInt(6, data.getDeaths());
pstmt.setInt(7, data.getBedsBroken());
pstmt.setInt(8, data.getGamesPlayed());
pstmt.setInt(9, data.getWinstreak());
pstmt.setInt(10, data.getXp());
pstmt.setInt(11, data.getLevel());
pstmt.setInt(12, data.getPrestige());
pstmt.executeUpdate();
} catch (SQLException e) {
plugin.getLogger().warning("Erro ao salvar dados do jogador: " + e.getMessage());
}
});
}
public void close() {
try {
if (connection != null && !connection.isClosed()) {
connection.close();
}
} catch (SQLException ignored) {}
}
}
