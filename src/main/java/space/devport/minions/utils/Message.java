package space.devport.minions.utils;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import space.devport.minions.MinionsPlugin;
import space.devport.utils.configutil.Configuration;
import space.devport.utils.messageutil.MessageBuilder;
import space.devport.utils.messageutil.StringUtil;

import java.util.Arrays;

public enum Message {

    PREFIX("Prefix", "&cMinions &8>> "),
    NOT_ENOUGH_ARGS("Not-Enough-Args", "&cNot enough arguments.", "&cUsage: &7%usage%"),
    TOO_MANY_ARGS("Too-Many-Args", "&cToo many arguments.", "&cUsage: &7%usage%");

    @Getter
    private final String path;

    @Getter
    @Setter
    private String[] value;

    Message(String path, String... value) {
        this.path = path;
        this.value = value;
    }

    public String getColored() {
        return StringUtil.color(getString());
    }

    public String getString() {
        return String.join("\n", value);
    }

    public MessageBuilder get() {
        return new MessageBuilder(value);
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
    }
}