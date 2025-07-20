package nl.multitime.applyCold.commands;

import nl.multitime.applyCold.gui.KillConfirmationGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;

        KillConfirmationGui killConfirmationGui = new KillConfirmationGui(player);
        killConfirmationGui.openGui();

        return true;

    }
}
