package space.devport.minions.template;

import lombok.Getter;
import lombok.Setter;
import space.devport.minions.utils.ActionType;

import java.util.ArrayList;
import java.util.List;

public class MinionLevel {

    @Getter
    @Setter
    private int level;

    // ---- Cost ----
    // TODO Implement DevportUtils RewardPack & ConditionPack once they're done.

    // Vault money
    @Getter
    @Setter
    private double money = 0;

    // Player exp
    @Getter
    @Setter
    private int exp = 0;

    // ---- Properties ----
    // If any of the properties are absent, take them from level 0,
    // if that is absent, try to take from config..
    // if that is absent.. which it should never,.. abort loading - ppl r stupid.

    @Getter
    private List<ActionType> actions = new ArrayList<>();

    @Getter
    @Setter
    private int radius;

    @Getter
    @Setter
    private int actionInterval;

    @Getter
    @Setter
    private int actionAmount;
}