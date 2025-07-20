package nl.multitime.applyCold.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class KillConfirmationGui {

    private final Player player;
    private final Inventory inventory;

    public KillConfirmationGui(Player player) {
        this.player = player;
        this.inventory = Bukkit.createInventory(null, 27, "§c§lConfirm Kill");
        setupGui();
    }

    private void setupGui() {
        ItemStack glassPane = createItem(Material.GRAY_STAINED_GLASS_PANE, "§7", "");
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, glassPane);
        }

        ItemStack confirmItem = createItem(Material.LIME_CONCRETE, "§a§lCONFIRM",
            "§7Click to confirm killing yourself",
            "§c§lWARNING: This will kill you!");
        inventory.setItem(11, confirmItem);

        ItemStack cancelItem = createItem(Material.RED_CONCRETE, "§c§lCANCEL",
            "§7Click to cancel and close this menu");
        inventory.setItem(15, cancelItem);

        ItemStack skullItem = createPlayerHead();
        inventory.setItem(13, skullItem);
    }

    private ItemStack createItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            if (lore.length > 0) {
                meta.setLore(Arrays.asList(lore));
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createPlayerHead() {
        ItemStack skull;
        try {
            skull = new ItemStack(Material.PLAYER_HEAD);
        } catch (Exception e) {
            try {
                skull = new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short) 3);
            } catch (Exception ex) {
                skull = new ItemStack(Material.valueOf("PLAYER_SKULL"));
            }
        }

        ItemMeta meta = skull.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§6§l" + player.getName());
            meta.setLore(Arrays.asList(
                "§7Are you sure you want to kill yourself?",
                "§7This action cannot be undone!"
            ));

            if (meta instanceof SkullMeta) {
                try {
                    ((SkullMeta) meta).setOwner(player.getName());
                } catch (Exception e) {
                    // Ignore if not supported
                }
            }

            skull.setItemMeta(meta);
        }

        return skull;
    }

    public void openGui() {
        player.openInventory(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Player getPlayer() {
        return player;
    }
}
