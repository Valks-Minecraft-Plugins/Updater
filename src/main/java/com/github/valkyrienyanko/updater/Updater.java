package com.github.valkyrienyanko.updater;

import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class Updater extends JavaPlugin {
    @Override
    public void onEnable() {
        // Always assuming new version
        // Updating...
        String name = "RemoteAPI";
        String domain = "Addmypremium.netlify.com";

        try {
            InputStream inputStream = new URL("https://" + domain + "/" + name + ".jar").openStream();
            Files.copy(inputStream, Paths.get("plugins/update/" + name + ".jar"), StandardCopyOption.REPLACE_EXISTING);

            File plugin = new File("plugins/update/" + name + ".jar");
            Bukkit.getPluginManager().loadPlugin(plugin);
            Bukkit.getPluginManager().enablePlugin(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(name)));
        } catch (IOException | InvalidDescriptionException | InvalidPluginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }
}
