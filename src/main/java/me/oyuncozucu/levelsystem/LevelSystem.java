package me.oyuncozucu.levelsystem;

import me.oyuncozucu.levelsystem.commands.LevelCommand;
import me.oyuncozucu.levelsystem.instances.ScoreboardSystem;
import me.oyuncozucu.levelsystem.listeners.JoinListener;
import me.oyuncozucu.levelsystem.listeners.LevelListener;
import me.oyuncozucu.levelsystem.listeners.MenuListener;
import me.oyuncozucu.levelsystem.managers.LevelManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LevelSystem extends JavaPlugin {

    private LevelManager levelManager;
    private ScoreboardSystem scoreboardSystem;

    @Override
    public void onEnable() {

        levelManager = new LevelManager(this);
        levelManager.loadLevelFile();

        getCommand("level").setExecutor(new LevelCommand(this));

        scoreboardSystem = new ScoreboardSystem(this);

        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new LevelListener(this), this);

    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public ScoreboardSystem getScoreboardSystem() {
        return scoreboardSystem;
    }

}
