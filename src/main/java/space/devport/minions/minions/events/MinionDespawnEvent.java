package space.devport.minions.minions.events;

import space.devport.minions.minions.MinionBasic;

public class MinionDespawnEvent extends MinionEvent {
    public MinionDespawnEvent(MinionBasic minion) {
        super(minion);
    }
}