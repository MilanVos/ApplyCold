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

        String title = event.getView().getTitle();

        if (!title.equals("§c§lConfirm Kill")) return;

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

            case PLAYER_HEAD:
                break;

            default:
                // Glass pane or other items, do nothing
                break;
        }
    }

    private void handleConfirmClick(Player player) {
        player.closeInventory();

        player.setHealth(0);
        player.damage(2);

        player.sendMessage("§c§lYou have been killed!");
    }

    private void handleCancelClick(Player player) {
        player.closeInventory();
        player.sendMessage("§7Kill command cancelled.");
    }
}
