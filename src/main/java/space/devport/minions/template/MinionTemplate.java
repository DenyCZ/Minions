package space.devport.minions.template;

import lombok.Getter;
import lombok.Setter;
import space.devport.minions.MinionsPlugin;
import space.devport.utils.configutil.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinionTemplate {

    // System name of the template
    @Getter
    private final String name;

    // File where the template is saved
    @Getter
    @Setter
    private Configuration storage;

    // Display name of the minion
    @Getter
    @Setter
    private String displayName;

    // Levels the minion can achieve in it's miserable life.
    @Getter
    @Setter
    private List<MinionLevel> levels = new ArrayList<>();

    public MinionTemplate(String name) {
        this.name = name;

        // Load the storage file
        storage = new Configuration(MinionsPlugin.getInstance(), "templates/" + name);
    }

    public void addLevel(MinionLevel level) {
        levels.add(level);
    }

    public boolean hasLevel(int lvl) {
        return levels.stream().anyMatch(l -> l.getLevel() == lvl);
    }

    public MinionLevel getLevel(int lvl) {
        return levels.stream().filter(l -> l.getLevel() == lvl).findFirst().orElse(null);
    }

    public MinionLevel getMinimalLevel() {
        return levels.stream().min(Comparator.comparingInt(MinionLevel::getLevel)).orElse(null);
    }
}