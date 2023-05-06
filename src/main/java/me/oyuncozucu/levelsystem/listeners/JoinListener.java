package me.oyuncozucu.levelsystem.listeners;

import me.oyuncozucu.levelsystem.LevelSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final LevelSystem plugin;

    public JoinListener(LevelSystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        plugin.getLevelManager().setupPlayer(e.getPlayer());
        plugin.getScoreboardSystem().setScoreboard(e.getPlayer());
    }

}
