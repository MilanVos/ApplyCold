package nl.multitime.applyCold.commands;

import nl.multitime.applyCold.gui.KillConfirmationGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        Player player = (Player) commandSender;

        KillConfirmationGui killConfirmationGui = new KillConfirmationGui(player);
        killConfirmationGui.open();

        return true;

    }
}
