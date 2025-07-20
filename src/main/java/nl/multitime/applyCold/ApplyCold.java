package nl.multitime.applyCold;

import nl.multitime.applyCold.commands.KillCommand;
import nl.multitime.applyCold.listeners.GuiListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ApplyCold extends JavaPlugin {
    private static ApplyCold instance;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("kill").setExecutor(new KillCommand());
        getServer().getPluginManager().registerEvents(new GuiListener(), this);

        getServer().getConsoleSender().sendMessage("§a[ApplyCold] §7Plugin enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ApplyCold getInstance(){
        return instance;
    }
}
