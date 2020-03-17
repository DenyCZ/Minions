package space.devport.minions.commands.menus;

import org.bukkit.event.inventory.InventoryClickEvent;
import space.devport.utils.menuutil.Menu;
import space.devport.utils.menuutil.MenuBuilder;
import space.devport.utils.menuutil.MenuItem;
import space.devport.utils.messageutil.MessageBuilder;

public class UpgradeMenu extends Menu {

    public UpgradeMenu(String name) {
        super(name, new MenuBuilder());

        // Build
    }

    @Override
    public void onOpen() {
        new MessageBuilder("&cThis menu is not done yet.")
                .send(player);
    }

    @Override
    public void onClick(InventoryClickEvent clickEvent, MenuItem clickedItem) {

    }
}
