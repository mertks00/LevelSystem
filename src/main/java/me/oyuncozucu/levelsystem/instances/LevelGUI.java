package me.oyuncozucu.levelsystem.instances;

import me.oyuncozucu.levelsystem.LevelSystem;
import me.oyuncozucu.levelsystem.managers.LevelManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class LevelGUI {

    public LevelGUI(Player player, LevelSystem plugin) {

        LevelManager levelManager = plugin.getLevelManager();

        Inventory menu = Bukkit.createInventory(player,27, ChatColor.GREEN + "Seviyeler:");

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + player.getDisplayName());
        skullMeta.setLore(Arrays.asList(
                "Seviye: " + ChatColor.GREEN + plugin.getLevelManager().getLevel(player),
                "Puan: " + ChatColor.GREEN + plugin.getLevelManager().getExp(player) + ChatColor.WHITE + "/" + ChatColor.GREEN + plugin.getLevelManager().getRequiredExp(player)
        ));
        skull.setItemMeta(skullMeta);

        ItemStack top = new ItemStack(Material.OAK_SIGN);
        ItemMeta topMeta = top.getItemMeta();
        topMeta.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "En Yüksek Seviyeli 10 Oyuncu: ");

        ArrayList<String> topLore = new ArrayList<>();
        int i = 1;
        for(String uuid : levelManager.loadTop()) {
            OfflinePlayer oPlayer = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
            topLore.add(ChatColor.AQUA.toString() + i + ": " + levelManager.getName(player) + " Seviye: " + levelManager.getLevel(player));
            i++;
        }

        topMeta.setLore(topLore);
        top.setItemMeta(topMeta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "Çıkış!");
        close.setItemMeta(closeMeta);

        menu.setItem(11, skull);
        menu.setItem(15, top);
        menu.setItem(26, close);

        player.openInventory(menu);

    }

}
