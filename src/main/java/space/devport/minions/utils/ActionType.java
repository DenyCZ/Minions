package space.devport.minions.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ActionType {
    PLACE,
    BREAK,
    KILL,
    SPAWN,
    FISH,
    FARM;

    // Get an ActionType from string
    @Nullable
    public static ActionType fromString(@NotNull String string) {
        for (ActionType type : values()) {
            if (type.toString().equalsIgnoreCase(string))
                return type;
        }

        return null;
    }
}
