package space.devport.minions;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import space.devport.utils.ConsoleOutput;
import space.devport.utils.DevportUtils;
import space.devport.utils.configutil.Configuration;

public class MinionsPlugin extends JavaPlugin {

    @Getter
    private static MinionsPlugin instance;

    @Getter
    private Configuration cfg;

    @Getter
    private ConsoleOutput consoleOutput;

    @Override
    public void onEnable() {
        instance = this;

        DevportUtils devportUtils = new DevportUtils();
        consoleOutput = devportUtils.getConsoleOutput();

        cfg = new Configuration(this, "config");

        loadOptions();
    }

    private void loadOptions() {
        consoleOutput.setDebug(cfg.getFileConfiguration().getBoolean("debug-enabled", false));
        consoleOutput.setPrefix(cfg.getColored("plugin-prefix"));
    }

    @Override
    public void onDisable() {

    }

    @Override
    @NotNull
    public FileConfiguration getConfig() {
        return cfg.getFileConfiguration();
    }

    @Override
    public void saveConfig() {
        cfg.save();
    }
}
