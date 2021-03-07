package net.pistonmaster.pistonutils.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@SuppressWarnings({"unused", "ignore"})
public class SimpleBukkitConfig {
    public static FileConfiguration setupSimpleConfig(JavaPlugin plugin, String fileName) {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

        File file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            try (InputStream in = plugin.getResource(fileName)) {
                assert in != null;
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return YamlConfiguration.loadConfiguration(file);
    }
}
