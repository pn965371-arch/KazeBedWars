package com.kaze.bedwars;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
public class Shop {
private final KazeBedWars plugin;
public Shop(KazeBedWars plugin) {
this.plugin = plugin;
}
public void openShop(Player player) {
player.sendMessage("§a[Loja] Abrindo loja de BedWars...");
// Menu interativo será aberto através de MenuHandler
MenuHandler.openShopMenu(player);
}
}
