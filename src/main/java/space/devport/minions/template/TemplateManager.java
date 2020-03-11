package space.devport.minions.template;

import lombok.Getter;
import space.devport.minions.MinionsPlugin;
import space.devport.utils.configutil.Configuration;

import java.io.File;
import java.util.HashMap;

public class TemplateManager {

    @Getter
    private final HashMap<String, MinionTemplate> templateCache = new HashMap<>();

    @Getter
    private File templateFolder;

    public TemplateManager() {
        this.templateFolder = new File(MinionsPlugin.getInstance().getDataFolder(), MinionsPlugin.getInstance().getConfig().getString("template-folder") + "/");
    }

    public MinionTemplate getTemplate(String name) {
        return templateCache.getOrDefault(name, null);
    }

    // Reload the folder & load templates
    public void reload() {
        this.templateFolder = new File(MinionsPlugin.getInstance().getDataFolder(), MinionsPlugin.getInstance().getConfig().getString("template-folder") + "/");

        load();
    }

    // Load templates
    public void load() {
        templateCache.clear();

        // Loop through files
        for (File file : templateFolder.listFiles()) {
            String name = stripExtension(file.getName());

            MinionTemplate template = new MinionTemplate(name);
            Configuration storage = template.getStorage();

            template.setDisplayName(storage.getColored("display-name"));

            // Load levels
            if (storage.getFileConfiguration().getConfigurationSection("levels") != null)
                for (String level : storage.getFileConfiguration().getConfigurationSection("levels").getKeys(false)) {
                    MinionLevel minionLevel = loadLevel(template, "levels." + level);

                    if (minionLevel != null)
                        template.addLevel(minionLevel);
                }

            if (template.getLevels().isEmpty()) {
                MinionsPlugin.getInstance().getConsoleOutput().warn("Template " + template.getName() + " has not levels configured, cannot use it.");
                continue;
            }

            MinionsPlugin.getInstance().getConsoleOutput().info("Loaded template " + template.getName() + " with " + template.getLevels().size() + " level(s)..");
        }
    }

    // Load a Minion Level on a path
    private MinionLevel loadLevel(MinionTemplate template, String path) {

        Configuration cfg = template.getStorage();

        String levelStr = path.split("\\.")[path.split("\\.").length - 1];

        int lvl;

        try {
            lvl = Integer.parseInt(levelStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        MinionLevel level = new MinionLevel(lvl);

        MinionLevel minLevel = template.getMinimalLevel();

        int minLvl = minLevel != null ? minLevel.getLevel() : -1;

        // Cost
        level.setExp(cfg.getFileConfiguration().getInt(path + ".cost.exp", 0));
        level.setMoney(cfg.getFileConfiguration().getDouble(path + ".cost.money", 0));

        // Properties
        level.setRadius(cfg.getFileConfiguration().getInt(path + ".properties.radius",
                template.hasLevel(minLvl) ? template.getLevel(lvl).getRadius() : MinionsPlugin.getInstance().getConfig().getInt("defaults.radius", 0)));

        level.setActionAmount(cfg.getFileConfiguration().getInt(path + ".properties.action-amount",
                template.hasLevel(minLvl) ? template.getLevel(lvl).getActionAmount() : MinionsPlugin.getInstance().getConfig().getInt("defaults.action-amount", 0)));

        level.setActionInterval(cfg.getFileConfiguration().getInt(path + ".properties.action-interval",
                template.hasLevel(minLvl) ? template.getLevel(lvl).getActionInterval() : MinionsPlugin.getInstance().getConfig().getInt("defaults.action-interval", 0)));

        return level;
    }

    private String stripExtension(String path) {
        path = path.replace(".yaml", "");
        path = path.replace(".yml", "");
        return path;
    }
}