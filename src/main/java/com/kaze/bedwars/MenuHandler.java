package com.kaze.bedwars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class MenuHandler {
public static void openShopMenu(Player player) {
Inventory inv = Bukkit.createInventory(null, 27, "§8Loja BedWars");
ItemStack wool = new ItemStack(Material.WHITE_WOOL, 16);
ItemMeta meta = wool.getItemMeta();
meta.setDisplayName("§fLã Branca");
wool.setItemMeta(meta);
inv.setItem(10, wool);
player.openInventory(inv);
}
}
