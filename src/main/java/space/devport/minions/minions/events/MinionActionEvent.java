package space.devport.minions.minions.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import space.devport.minions.minions.MinionBasic;
import space.devport.minions.utils.ActionType;

public class MinionActionEvent extends MinionEvent implements Cancellable {

    @Getter
    private final ActionType action;

    @Getter
    @Setter
    private boolean cancelled = false;

    public MinionActionEvent(MinionBasic minion, ActionType action) {
        super(minion);
        this.action = action;
    }
}