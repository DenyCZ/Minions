package space.devport.minions;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import space.devport.minions.commands.MinionCommands;
import space.devport.minions.minions.MinionManager;
import space.devport.minions.template.TemplateManager;
import space.devport.minions.utils.Message;
import space.devport.utils.ConsoleOutput;
import space.devport.utils.DevportUtils;
import space.devport.utils.configutil.Configuration;
import space.devport.utils.messageutil.StringUtil;

public class MinionsPlugin extends JavaPlugin {

    @Getter
    private static MinionsPlugin instance;

    @Getter
    private ConsoleOutput consoleOutput;

    @Getter
    private TemplateManager templateManager;
    @Getter
    private MinionManager minionManager;

    @Getter
    private Configuration cfg;

    @Getter
    private String languageFile = "lang/lang_en";

    @Override
    public void onEnable() {
        instance = this;

        long start = System.currentTimeMillis();

        DevportUtils devportUtils = new DevportUtils(this);
        consoleOutput = devportUtils.getConsoleOutput();

        cfg = new Configuration(this, "config");

        loadOptions();
        Message.load();

        consoleOutput.info("--====-- " + getDescription().getName() + "v " + getDescription().getVersion() + " --====--");

        templateManager = new TemplateManager();
        minionManager = new MinionManager();

        templateManager.load();
        consoleOutput.info("Loaded " + templateManager.getTemplateCache().size() + " template(s)..");

        minionManager.loadAll();
        consoleOutput.info("Loaded " + minionManager.getMinionCache().size() + " minion army(ies)..");

        getCommand("minions").setExecutor(new MinionCommands());

        consoleOutput.info("--====-- Done --====--");
        consoleOutput.info("Reload took " + (System.currentTimeMillis() - start) + "ms");
    }

    public void reload(CommandSender s) {
        long start = System.currentTimeMillis();

        consoleOutput.addListener(s);

        consoleOutput.info("--====-- " + getDescription().getName() + "v " + getDescription().getVersion() + " --====--");

        cfg.reload();

        loadOptions();
        Message.load();

        templateManager.reload();
        consoleOutput.info("Loaded " + templateManager.getTemplateCache().size() + " template(s)..");

        minionManager.reloadAll();
        consoleOutput.info("Loaded " + minionManager.getMinionCache().size() + " minion army(ies)..");

        consoleOutput.removeListener(s);

        consoleOutput.info("--====-- Done --====--");
        s.sendMessage(consoleOutput.getPrefix() + StringUtil.color("&aDone.. reload took &7" + (System.currentTimeMillis() - start) + "&ams."));
    }

    private void loadOptions() {
        consoleOutput.setDebug(cfg.getFileConfiguration().getBoolean("debug-enabled", false));
        consoleOutput.setPrefix(cfg.getColoredString("plugin-prefix", ""));

        this.languageFile = cfg.getFileConfiguration().getString("language", "lang/lang_en");
    }

    @Override
    public void onDisable() {
        minionManager.stopAll();
        minionManager.saveAll();
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