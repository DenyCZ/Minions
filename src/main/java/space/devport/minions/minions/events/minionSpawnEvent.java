package space.devport.minions.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import space.devport.minions.minions.MinionBasic;

public class minionSpawnEvent {
    private static final HandlerList handlers;
    @Getter private MinionBasic minion;
    @Getter @Setter private boolean isCancelled;

    public minionSpawnEvent(final Player player, final MinionBasic minion) {
        this.isCancelled = false;
        this.minion = minion;
    }

    public HandlerList getHandlers() {
        return minionSpawnEvent.handlers;
    }

    static {
        handlers = new HandlerList();
    }
}
