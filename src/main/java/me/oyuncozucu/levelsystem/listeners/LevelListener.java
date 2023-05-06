package me.oyuncozucu.levelsystem.listeners;

import me.oyuncozucu.levelsystem.LevelSystem;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class LevelListener implements Listener {

    private final LevelSystem plugin;

    public LevelListener(LevelSystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent e) {
        if (e.getEntity() instanceof Creeper) {
            Creeper creeper = (Creeper) e.getEntity();
            if (creeper.getKiller() != null) {
                Player player = creeper.getKiller();
                plugin.getLevelManager().addExp(player, 5);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player player = e.getPlayer();

        switch (e.getBlock().getType()) {
            case DIAMOND_ORE:
                plugin.getLevelManager().addExp(player,5);
                break;
            case IRON_ORE:
                plugin.getLevelManager().addExp(player,2);
                break;
            default:
                plugin.getLevelManager().addExp(player,1);
        }

    }
}
