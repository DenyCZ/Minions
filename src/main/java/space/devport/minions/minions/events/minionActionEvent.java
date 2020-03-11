package space.devport.minions.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import space.devport.minions.minions.MinionBasic;
import space.devport.minions.utils.ActionType;

public class minionActionEvent extends Event implements Cancellable {
    private static final HandlerList handlers;
    @Getter @Setter private boolean isCancelled;
    @Getter private ActionType action;
    @Getter private MinionBasic minion;

    public minionActionEvent(MinionBasic minion, ActionType action) {
        this.minion = minion;
        this.action = action;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    public HandlerList getHandlers() {
        return minionActionEvent.handlers;
    }

    static {
        handlers = new HandlerList();
    }
}
