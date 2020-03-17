package space.devport.minions.minions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MinionGroup {

    //TODO: Version 1.0, READ COMMENT BELOW
    /*
        Now it works as adding minion by minion to group until the group is filled.
        When group is full, create new group and repeat process.

        As a next idea we can use levels or by action-interval
        This would allow us to pair animations with doAction().

        But we should think, that animation has constant length, but the action not.
        If we do action faster than the animation length, it can get kinda buggy which we do not need / want

        There might be minimum action delay that equals to animation length which might fix this.

        Animation length is exactly 20 ticks == 1 second which might be actually enough.
        Think about user minions that might want RAPID doAction FIRE mode which is kinda crazy, but might work.

        Or we can launch animation only when doAction delay is >= 20 ticks long
     */

    private final Integer MAXSIZE = 20;

    @Getter
    private final List<MinionBasic> minions = new ArrayList<>();

    public boolean isFull() {
        return minions.size() == MAXSIZE;
    }

    public void addMinion(MinionBasic minion) {
        minions.add(minion);
    }
}