package space.devport.minions.template;

import lombok.Getter;
import space.devport.minions.MinionsPlugin;

import java.io.File;
import java.util.HashMap;

public class TemplateManager {

    @Getter
    private final HashMap<String, MinionTemplate> templateCache = new HashMap<>();

    @Getter
    private File templateFolder;

    public TemplateManager() {
        this.templateFolder = new File(MinionsPlugin.getInstance().getDataFolder(), MinionsPlugin.getInstance().getConfig().getString("tempaltes-folder") + "/");
    }

    // Reload the folder & load templates
    public void reload() {
        this.templateFolder = new File(MinionsPlugin.getInstance().getDataFolder(), MinionsPlugin.getInstance().getConfig().getString("tempaltes-folder") + "/");
    }

    // Load templates
    public void load() {
    }
}