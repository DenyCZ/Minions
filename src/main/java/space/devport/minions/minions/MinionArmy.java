package space.devport.minions.minions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MinionArmy {

    @Getter private final UUID leader;

    @Getter private final List<Class<? extends MinionBasic>> minions = new ArrayList<>();

    public MinionArmy(UUID leader) {
        this.leader = leader;
    }

    public void addMinion(Class<? extends MinionBasic> minion) {
        minions.add(minion);
    }

    public boolean isEmpty() {
        return minions.isEmpty();
    }
}