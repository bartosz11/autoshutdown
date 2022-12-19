package me.bartosz1.autoshutdown;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class AutoShutdown extends JavaPlugin {

    private BukkitTask shutdownTask;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        long delay = getConfig().getLong("delay");
        getSLF4JLogger().info(String.format("Shutting down in %d seconds if no players join", delay));
        shutdownTask = new ShutdownTask().runTaskLater(this, delay * 20);
    }

    @Override
    public void onDisable() {
        if (shutdownTask != null) {
            shutdownTask.cancel();
            shutdownTask = null;
        }
    }

    public BukkitTask getShutdownTask() {
        return shutdownTask;
    }

    public void setShutdownTask(BukkitTask shutdownTask) {
        this.shutdownTask = shutdownTask;
    }
}
