package space.devport.minions;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import space.devport.utils.configutil.Configuration;

public class MinionsPlugin extends JavaPlugin {

    @Getter
    private static MinionsPlugin instance;

    @Getter
    private Configuration cfg;

    @Override
    public void onEnable() {
        instance = this;

        cfg = new Configuration(this, "config");
    }

    @Override
    public void onDisable() {

    }

    @Override
    public FileConfiguration getConfig() {
        return cfg.getFileConfiguration();
    }

    @Override
    public void saveConfig() {
        cfg.save();
    }
}
