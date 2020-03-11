package space.devport.minions.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import space.devport.minions.minions.MinionBasic;

public class minionLoadEvent extends Event implements Cancellable {
    private static final HandlerList handlers;
    @Getter private Player player;
    @Getter private MinionBasic minion;
    @Getter @Setter private boolean isCancelled;

    public minionLoadEvent(final Player player, final MinionBasic minion) {
        this.isCancelled = false;
        this.player = player;
        this.minion = minion;
    }

    public HandlerList getHandlers() {
        return minionLoadEvent.handlers;
    }

    static {
        handlers = new HandlerList();
    }
}
