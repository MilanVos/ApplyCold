package nl.multitime.applyCold.listeners;

import nl.multitime.applyCold.ApplyCold;
import nl.multitime.applyCold.gui.KillConfirmationGui;
import nl.multitime.applyCold.utils.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        if (!event.getView().getTitle().equals("§c§lConfirm Kill")) return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        Material clickedMaterial = event.getCurrentItem().getType();

        SoundManager.playButtonClickSound(player);

        switch (clickedMaterial) {
            case LIME_CONCRETE:
                handleConfirmClick(player);
                break;

            case RED_CONCRETE:
                handleCancelClick(player);
                break;

            case PLAYER_HEAD: // Skull click
                break;

            default:
                break;
        }
    }

    private void handleConfirmClick(Player player) {
        player.closeInventory();

        Bukkit.getScheduler().runTask(ApplyCold.getInstance(), () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill " + player.getName());
        });

        player.sendMessage("§c§lYou have been killed!");
    }

    private void handleCancelClick(Player player) {
        player.closeInventory();
        player.sendMessage("§7Kill command cancelled.");
    }
}
