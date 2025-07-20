package nl.multitime.applyCold.utils;

import org.bukkit.entity.Player;

public class SoundManager {

    public static void playButtonClickSound(Player player){
        player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 10.f, 10.f);
    }
}
