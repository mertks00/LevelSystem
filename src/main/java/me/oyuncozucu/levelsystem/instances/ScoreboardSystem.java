package me.oyuncozucu.levelsystem.instances;

import me.oyuncozucu.levelsystem.LevelSystem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardSystem {

    private final LevelSystem plugin;

    public ScoreboardSystem(LevelSystem plugin) {
        this.plugin = plugin;
    }

    public void setScoreboard(Player player) {

        ScoreboardManager manager = plugin.getServer().getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective levelBoard = scoreboard.registerNewObjective("title","dummy");
        levelBoard.setDisplaySlot(DisplaySlot.SIDEBAR);
        levelBoard.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Seviye");

        Score line = levelBoard.getScore(ChatColor.YELLOW + "--------------------");
        line.setScore(4);

        Score name = levelBoard.getScore("Ad: " + ChatColor.GREEN + player.getDisplayName());
        name.setScore(3);

        Score level = levelBoard.getScore("Seviye: " + ChatColor.GREEN + plugin.getLevelManager().getLevel(player));
        level.setScore(2);

        Score exp = levelBoard.getScore("Puan: " + ChatColor.GREEN + plugin.getLevelManager().getExp(player) + ChatColor.WHITE + "/" + ChatColor.GREEN + plugin.getLevelManager().getRequiredExp(player));
        exp.setScore(1);

        player.setScoreboard(scoreboard);
    }

}
