package space.devport.minions.minions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.devport.minions.utils.ActionType;
import space.devport.minions.utils.InventoryType;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class MinionProperties {

    // TODO: Reading/Saving @Wertik

    @Getter @Setter private int radius;
    @Getter @Setter private int level;
    @Getter @Setter private int maxLevel;
    @Getter @Setter private int exp;
    @Getter @Setter private InventoryType inventoryType;
    @Getter @Setter private double money;
    @Getter @Setter private List<ActionType> _action = new ArrayList<>();
    @Getter @Setter private int actionInterval;
    @Getter @Setter private int health = 0;
    @Getter @Setter private boolean isHealthBased;
    @Getter @Setter private int actionsSinceLastHealthDrop;
    @Getter @Setter private int allowedActionsPerHealthUnit;
    @Getter @Setter private boolean workOffline = false;
    @Getter @Setter private boolean canPlaceHead = true;
    @Getter @Setter private boolean commandOnly = false;
}