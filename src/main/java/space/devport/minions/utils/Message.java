package space.devport.minions.utils;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import space.devport.minions.MinionsPlugin;
import space.devport.utils.configutil.Configuration;
import space.devport.utils.messageutil.MessageBuilder;
import space.devport.utils.messageutil.ParseFormat;

import java.util.Arrays;

public enum Message {

    NOT_ENOUGH_ARGS("Not-Enough-Args", "&cNot enough arguments.", "&cUsage: &7%usage%"),
    TOO_MANY_ARGS("Too-Many-Args", "&cToo many arguments.", "&cUsage: &7%usage%"),
    INVALID_MINION("Invalid-Minion", "&cInvalid minion ID.", "&cHint: &7/%label% list [player/all]"),
    MINION_SPAWNED("Minion-Spawned", "&aMinion spawned successfully."),
    HAS_TO_BE_NUMBER("Has-To-Be-Number", "&cArgument has to be a number."),
    PLAYER_DOESNT_EXIST("Player-Doesnt-Exist", "&cThat player doesn't exist."),
    NO_MINIONS("No-Minions", "&cThis player has no minions.")
    ;

    @Getter
    private static final ParseFormat globalFormat = new ParseFormat();

    @Getter
    private final String path;

    @Getter
    @Setter
    private String[] value;

    Message(String path, String... value) {
        this.path = path;
        this.value = value;
    }

    public String getColored(boolean... addPrefix) {
        return get(addPrefix).color().toString();
    }

    public String getString(boolean... addPrefix) {
        return get(addPrefix).toString();
    }

    public MessageBuilder get(boolean... addPrefix) {
        MessageBuilder builder = new MessageBuilder();

        if (addPrefix.length > 0 && addPrefix[0])
            builder.addLine(MinionsPlugin.getInstance().getConsoleOutput().getPrefix());

        Arrays.stream(value).forEachOrdered(builder::addLine);
        return builder;
    }

    public static void load() {
        Configuration messages = new Configuration(MinionsPlugin.getInstance(), MinionsPlugin.getInstance().getLanguageFile());

        for (Message msg : values()) {
            String str = messages.getFileConfiguration().getString(msg.getPath());

            if (Strings.isNullOrEmpty(str)) {
                messages.getFileConfiguration().set(msg.getPath(), msg.getValue());
                continue;
            }

            if (msg.getValue().length == 1) {
                messages.getFileConfiguration().set(msg.getPath(), msg.getValue()[0]);
            } else {
                messages.getFileConfiguration().set(msg.getPath(), Arrays.asList(msg.getValue()));
            }
        }

        messages.save();

        // Configure Format
        globalFormat
                .fill("%version%", MinionsPlugin.getInstance().getDescription().getVersion())
                .fill("%prefix%", MinionsPlugin.getInstance().getConsoleOutput().getPrefix());
    }
}