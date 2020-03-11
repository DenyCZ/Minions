package space.devport.minions.minions.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import space.devport.minions.minions.MinionBasic;

public class MinionDeathEvent extends MinionEvent implements Cancellable {

    @Getter
    private final Player player;

    @Getter
    @Setter
    private boolean cancelled = false;

    public MinionDeathEvent(final Player player, final MinionBasic minion) {
        super(minion);
        this.player = player;
    }
}