package me.bartosz1.autoshutdown;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final AutoShutdown autoShutdown;

    public PlayerQuitListener(AutoShutdown autoShutdown) {
        this.autoShutdown = autoShutdown;
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        //"-1 BECAUSE BUKKIT IS STUPID AND INCLUDES THE LEAVING PLAYER LOL" - comment from my old plugin doing the exact same thing
        //don't ask why I rewrote it
        if (Bukkit.getOnlinePlayers().size()-1 == 0) {
            long delay = autoShutdown.getConfig().getLong("delay");
            autoShutdown.getSLF4JLogger().info(String.format("No players active, shutting down in %d seconds", delay));
            new ShutdownTask().runTaskLater(autoShutdown, delay * 20);
        }
    }
}
