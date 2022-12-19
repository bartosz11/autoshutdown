package me.bartosz1.autoshutdown;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

public class PlayerJoinListener implements Listener {
    private final AutoShutdown autoShutdown;
    public PlayerJoinListener(AutoShutdown autoShutdown) {
        this.autoShutdown = autoShutdown;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        BukkitTask shutdownTask = autoShutdown.getShutdownTask();
        if (shutdownTask != null) {
            shutdownTask.cancel();
            autoShutdown.setShutdownTask(null);
            autoShutdown.getSLF4JLogger().info("Automatic shutdown cancelled.");
        }
    }
}
