package space.devport.minions.minions.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import space.devport.minions.minions.MinionBasic;

public class MinionEvent extends Event {

    private static final HandlerList handlers;

    @Getter
    private final MinionBasic minion;

    public MinionEvent(final MinionBasic minion) {
        this.minion = minion;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return MinionEvent.handlers;
    }

    static {
        handlers = new HandlerList();
    }
}