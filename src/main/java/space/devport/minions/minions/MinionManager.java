package space.devport.minions.minions;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MinionManager {

    @Getter
    private final HashMap<UUID, MinionArmy> minionCache = new HashMap<>();

    // Update minions based on templates & their levels, bcs they could change on plugin reload
    public void reloadAll() {

    }

    public void saveAll() {

    }

    public void loadAll() {

    }

    public void addMinion(Player player, Class<? extends MinionBasic> minion) {
        addMinion(player.getUniqueId(), minion);
    }

    // Add a minion to owners army
    public void addMinion(UUID uniqueID, Class<? extends MinionBasic> minion) {
        MinionArmy army = getArmy(uniqueID);
        army.addMinion(minion);
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
}
