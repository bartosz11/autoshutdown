package me.bartosz1.autoshutdown;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ShutdownTask extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.shutdown();
    }
}
