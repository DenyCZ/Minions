package space.devport.minions.commands.menus;

import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;
import space.devport.minions.minions.MinionBasic;
import space.devport.utils.menuutil.Menu;
import space.devport.utils.menuutil.MenuBuilder;
import space.devport.utils.menuutil.MenuItem;
import space.devport.utils.messageutil.MessageBuilder;

public class UpgradeMenu extends Menu {

    @Getter
    private final MinionBasic minion;

    public UpgradeMenu(String name, MinionBasic minion) {
        super(name, new MenuBuilder());
        this.minion = minion;

        // Build
    }

    @Override
    public void onOpen() {
        new MessageBuilder("&cThis menu is not done yet.")
                .send(player);
        close();
    }

    @Override
    public void onClick(InventoryClickEvent clickEvent, MenuItem clickedItem) {

    }
}
