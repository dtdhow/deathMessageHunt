package org.deathhunt.deathhunt;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
public final class DeathHunt extends JavaPlugin {
    @Override
    public void onEnable() {

        // Plugin startup logic
        getLogger().info("DeathHunt main loaded...");
        Bukkit.getPluginManager().registerEvents(new Death(), this);

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:scoreboard objectives add deaths dummy \"Death Message Hunt\"");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:scoreboard objectives setdisplay list deaths");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("DeathHunt main did not load...");
    }
}