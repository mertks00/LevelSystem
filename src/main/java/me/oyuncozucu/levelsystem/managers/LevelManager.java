package me.oyuncozucu.levelsystem.managers;

import me.oyuncozucu.levelsystem.LevelSystem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LevelManager {

    private final LevelSystem plugin;
    private File levelFile;
    private YamlConfiguration levelConfig;
    private List<String> top;

    public LevelManager(LevelSystem plugin) {
        this.plugin = plugin;
        top = new ArrayList<>();
    }

    public void loadLevelFile() {
        plugin.getDataFolder().mkdir();
        levelFile = new File(plugin.getDataFolder(), "level-data.yml");
        if(!levelFile.exists()) {
            try {
                levelFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            levelConfig = YamlConfiguration.loadConfiguration(levelFile);
        }
    }

    public void setupPlayer(Player player) {
        if(!levelConfig.contains(player.getUniqueId().toString())) {
            levelConfig.set(player.getUniqueId() + ".level",1);
            levelConfig.set(player.getUniqueId() + ".exp",0);
            levelConfig.set(player.getUniqueId() + ".required-exp",5);
        }
        levelConfig.set(player.getUniqueId() + ".name", player.getName());
        try {
            levelConfig.save(levelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /* LEVEL MANAGER */

    public void addExp(Player player, int amount) {
        levelConfig.set(player.getUniqueId() + ".exp", getExp(player) + amount);
        try {
            levelConfig.save(levelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(getExp(player) >= getRequiredExp(player)) {
            levelUp(player);
        }

        plugin.getScoreboardSystem().setScoreboard(player);

    }

    public void levelUp(Player player) {
        levelConfig.set(player.getUniqueId() + ".exp", getExp(player) - getRequiredExp(player));
        levelConfig.set(player.getUniqueId() + ".level", getLevel(player) + 1);
        levelConfig.set(player.getUniqueId() + ".required-exp", getRequiredExp(player) + 5);
        try {
            levelConfig.save(levelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.sendTitle(ChatColor.AQUA.toString() + ChatColor.BOLD + "Seviye Atladın!", ChatColor.GREEN +"Yeni level: " + getLevel(player), 20,60,20);

        switch (getLevel(player)) {
            case 5:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND));
                player.sendMessage(ChatColor.GREEN + "Hediyeni aldın!");
                break;
            case 10:
                player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 16));
                player.sendMessage(ChatColor.GREEN + "Hediyeni aldın!");
                break;
        }

    }

    /* GETTER */

    public List<String> loadTop() {
        Map<String,Integer> data = new HashMap<>();
        for(String key : levelConfig.getKeys(false)) {
            data.put(key, levelConfig.getInt(key + ".level"));
        }

        top.clear();
        top = data.entrySet().stream().sorted(
                Collections.reverseOrder(
                        Map.Entry.comparingByValue()
                ))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return top;
    }

    public int getLevel(Player player) {
        return levelConfig.getInt(player.getUniqueId() + ".level");
    }

    public int getLevel(OfflinePlayer offlinePlayer) {
        return levelConfig.getInt(offlinePlayer.getUniqueId() + ".level");
    }

    public int getExp(Player player) {
        return levelConfig.getInt(player.getUniqueId() + ".exp");
    }

    public int getRequiredExp(Player player) {
        return levelConfig.getInt(player.getUniqueId() + ".required-exp");
    }

    public String getName(OfflinePlayer offlinePlayer) {
        return levelConfig.getString(offlinePlayer.getUniqueId() + ".name");
    }

}
