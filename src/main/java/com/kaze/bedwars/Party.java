package com.kaze.bedwars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.*;
public class Party {
private final UUID leader;
private final Set<UUID> members = new HashSet<>();
private final Set<UUID> invites = new HashSet<>();
public Party(Player leader) {
this.leader = leader.getUniqueId();
this.members.add(this.leader);
}
public UUID getLeader() { return leader; }
public Set<UUID> getMembers() { return members; }
public void addMember(Player player) {
members.add(player.getUniqueId());
invites.remove(player.getUniqueId());
}
public void removeMember(Player player) {
members.remove(player.getUniqueId());
}
public boolean isMember(UUID uuid) {
return members.contains(uuid);
}
public void invite(Player player) {
invites.add(player.getUniqueId());
}
public boolean hasInvite(UUID uuid) {
return invites.contains(uuid);
}
}
