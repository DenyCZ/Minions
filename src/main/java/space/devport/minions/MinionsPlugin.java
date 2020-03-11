package space.devport.minions;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import space.devport.minions.minions.MinionManager;
import space.devport.minions.template.TemplateManager;
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

    @Getter
    private TemplateManager templateManager;

    @Getter
    private MinionManager minionManager;

    @Override
    public void onEnable() {
        instance = this;

        long start = System.currentTimeMillis();

        DevportUtils devportUtils = new DevportUtils();
        consoleOutput = devportUtils.getConsoleOutput();

        cfg = new Configuration(this, "config");

        loadOptions();

        consoleOutput.info("--====-- " + getDescription().getName() + "v " + getDescription().getVersion() + " --====--");

        templateManager = new TemplateManager();
        minionManager = new MinionManager();

        templateManager.load();
        consoleOutput.info("Loaded " + templateManager.getTemplateCache().size() + " template(s)..");

        minionManager.loadAll();
        consoleOutput.info("Loaded " + minionManager.getMinionCache().size() + " minion army(ies)..");

        consoleOutput.info("--====-- Done --====--");
        consoleOutput.info("Reload took " + (System.currentTimeMillis() - start) + "ms");
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