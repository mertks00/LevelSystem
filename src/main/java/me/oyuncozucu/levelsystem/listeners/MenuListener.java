package me.oyuncozucu.levelsystem.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Seviyeler:")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case BARRIER:
                    player.closeInventory();
                    break;
            }
        }

    }

}
