package space.devport.minions.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.devport.minions.MinionsPlugin;
import space.devport.minions.commands.menus.MinionsMenu;
import space.devport.minions.commands.menus.ShopMenu;
import space.devport.minions.minions.MinionArmy;
import space.devport.minions.minions.MinionBasic;
import space.devport.minions.template.MinionTemplate;
import space.devport.minions.utils.Message;
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
            // TODO List all minions, now lists only owned by the player
            case "list":
                if (args.length < 2 && checkConsole(sender))
                    return true;

                OfflinePlayer target;
                if (args.length > 1)
                    target = Bukkit.getOfflinePlayer(args[1]);
                else if (!checkConsole(sender))
                    target = (Player) sender;
                else return true;

                MinionArmy army = plugin.getMinionManager().getArmy(target.getUniqueId());

                if (army.isEmpty()) {
                    Message.NO_MINIONS.get(true).send(sender);
                    break;
                }

                MessageBuilder builder = new MessageBuilder("&8&m--------&c " + target.getName() + " &8&m--------");

                if (!army.isEmpty())
                    for (MinionBasic minion : army.getMinions()) {
                        // TODO Add more info
                        builder.addLine(minion.getId() + " &8 - " + minion.getMProperties().getHealth());
                    }
                else builder.addLine("&cThis army is empty.");

                builder.send(sender);
                break;
            case "spawn":
                if (checkConsole(sender))
                    return true;

                player = (Player) sender;

                if (!checkTemplate(sender, label, args[1]))
                    return true;

                MinionTemplate template = plugin.getTemplateManager().getTemplate(args[1]);

                int level = template.getMinimalLevel().getLevel();

                if (args.length > 2)
                    try {
                        level = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e) {
                        Message.HAS_TO_BE_NUMBER.get(true).send(player);
                        break;
                    }

                MinionBasic minion = new MinionBasic();

                minion.getMProperties().setLevel(level);

                plugin.getMinionManager().addMinion(player.getUniqueId(), minion);

                Message.MINION_SPAWNED.get(true).send(player);
                break;
            case "kill":
                new MessageBuilder("&cNot done yet.").send(sender);
                break;
            case "help":
            default:
                help(sender, label);
        }

        return false;
    }

    private boolean checkTemplate(CommandSender s, String label, String name) {
        if (plugin.getTemplateManager().getTemplate(name) != null) {
            new MessageBuilder("&cTemplate is invalid. &cHint: &7/" + label + " templates")
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
