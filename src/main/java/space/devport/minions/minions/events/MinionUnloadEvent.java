package space.devport.minions.minions.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import space.devport.minions.minions.MinionBasic;

public class MinionUnloadEvent extends MinionEvent {

    @Getter
    private final Player player;

    @Getter
    @Setter
    private boolean cancelled = false;

    public MinionUnloadEvent(final Player player, final MinionBasic minion) {
        super(minion);
        this.player = player;
    }
}