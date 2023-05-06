package me.oyuncozucu.levelsystem.commands;

import me.oyuncozucu.levelsystem.instances.LevelGUI;
import me.oyuncozucu.levelsystem.LevelSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LevelCommand implements CommandExecutor {

    private final LevelSystem plugin;

    public LevelCommand(LevelSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("top")) {
                    new LevelGUI(player,plugin);
                }
            }

        }
        return true;
    }
}
