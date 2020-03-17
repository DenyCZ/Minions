package space.devport.minions.minions;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MinionManager {

    @Getter private final HashMap<UUID, MinionArmy> minionCache = new HashMap<>();
    @Getter private final List<MinionGroup> minionGroups = new ArrayList<>();

    @Getter private MinionTask minionTask = new MinionTask();

    // Update minions based on templates & their levels, bcs they could change on plugin reload
    public void reloadAll() {
        minionTask.stopTasks();
        loadAll();
        minionTask.startTasks();
    }

    public void stopAll() {
        minionTask.stopTasks();
    }

    public void saveAll() {

    }

    public void loadAll() {

    }

    public MinionBasic getMinion(String id) {
        for (MinionGroup group : minionGroups) {
            // TODO
        }

        return null;
    }

    public void addMinion(Player player, Class<? extends MinionBasic> minion) {
        addMinion(player.getUniqueId(), minion);
    }

    // Add a minion to owners army
    public void addMinion(UUID uniqueID, Class<? extends MinionBasic> minion) {
        MinionArmy army = getArmy(uniqueID);
        army.addMinion(minion);

        MinionGroup mGroup = getLast();
        if(mGroup == null || mGroup.isFull()) {
            createGroup();
        }

        getLast().addMinion(minion);
    }

    public MinionArmy getArmy(Player player) {
        return getArmy(player.getUniqueId());
    }

    public MinionArmy getArmy(UUID uniqueID) {
        return minionCache.getOrDefault(uniqueID, new MinionArmy(uniqueID));
    }

    public boolean hasArmy(Player player) {
        return hasArmy(player.getUniqueId());
    }

    public boolean hasArmy(UUID uniqueID) {
        return getArmy(uniqueID).isEmpty();
    }

    // Group Handling
    private void createGroup() {
        minionGroups.add(new MinionGroup());
    }

    private MinionGroup getLast() {
        return minionGroups.size() > 0 ? minionGroups.get(minionGroups.size()-1) : null;
    }
}
