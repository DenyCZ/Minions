package space.devport.minions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.devport.minions.MinionsPlugin;
import space.devport.minions.commands.menus.MinionsMenu;
import space.devport.minions.commands.menus.ShopMenu;
import space.devport.utils.messageutil.MessageBuilder;

public class MinionCommands implements CommandExecutor {

    /* Commands:
     * - minions = opens a basic GUI with list of players minions
     * - minions shop = opens a shop
     * - minions list [player/all] = lists players minions, or all minions spawned
     * - minions spawn <template> [level] = spawn a minion for you
     * - minions kill <id> = despawn minion by id
     * - minions templates = list valid minion templates
     * - minions template <template> = display template info
     * - minions minion <id> = display minion info
     * */

    private final MinionsPlugin plugin;

    // TODO Hook to a message system
    private final MessageBuilder notEnoughArgs = new MessageBuilder(MinionsPlugin.getInstance().getConsoleOutput().getPrefix() + "&cNot enough arguments.", "&cUsage: &7%usage%");
    private final MessageBuilder tooManyArgs = new MessageBuilder(MinionsPlugin.getInstance().getConsoleOutput().getPrefix() + "&cToo many arguments.", "&cUsage: &7%usage%");

    private final MessageBuilder invalidMinion = new MessageBuilder(MinionsPlugin.getInstance().getConsoleOutput().getPrefix() + "&cInvalid minion ID.", "&cHint: &7/%label% list [player/all]");

    private final MessageBuilder helpMsg = new MessageBuilder("&8&m--------&c Minions &7v&f" + MinionsPlugin.getInstance().getDescription().getVersion() + " &8&m--------");

    public MinionCommands() {
        this.plugin = MinionsPlugin.getInstance();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player player;

        // Open GUI
        if (args.length == 0) {

            if (checkConsole(sender))
                return true;

            player = (Player) sender;

            new MinionsMenu("minions_menu")
                    .open(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                plugin.reload(sender);
                break;
            case "shop":

                if (checkConsole(sender))
                    return true;

                player = (Player) sender;

                new ShopMenu("minions_shop")
                        .open(player);
                break;
            case "list":
                break;
            case "spawn":
                break;
            case "kill":
                break;
            case "help":
            default:
                help(sender, label);
        }

        return false;
    }

    private boolean checkTemplate(CommandSender s, String label, String name) {
        if (plugin.getTemplateManager().getTemplate(name) != null) {
            new MessageBuilder("&cTemplate is invalid.", "&cHint: &7/" + label + " templates")
                    .send(s);
            return false;
        }

        return true;
    }

    private boolean checkConsole(CommandSender s) {
        if (s instanceof Player)
            return false;
        else {
            new MessageBuilder("&cThis command is only for players.")
                    .send(s);
            return true;
        }
    }

    // TODO Filter subcommands by permissions
    private void help(CommandSender s, String label) {
        helpMsg.addLine("&c/%label% &8- &7Open your minions gui." +
                "\n&c/%label% help &8- &7This." +
                "\n&c/%label% reload &8- &7Reload the plugin." +
                "\n&c/%label% shop &8- &7Open minion shop." +
                "\n&c/%label% list &7[player/all] &8- &7List minions." +
                "\n&c/%label% spawn <template> [level] &8- &7Spawn yourself a minion." +
                "\n&c/%label% kill <id> &8- &7Despawn a minion by id." +
                "\n&c/%label% minions templates &8- &7List valid minion templates.");
        helpMsg.fill("%label%", label)
                .send(s);
    }
}
