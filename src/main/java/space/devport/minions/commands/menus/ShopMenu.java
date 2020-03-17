package space.devport.minions.commands.menus;

import org.bukkit.event.inventory.InventoryClickEvent;
import space.devport.utils.menuutil.Menu;
import space.devport.utils.menuutil.MenuBuilder;
import space.devport.utils.menuutil.MenuItem;
import space.devport.utils.messageutil.MessageBuilder;

public class ShopMenu extends Menu {

    public ShopMenu(String name) {
        super(name, new MenuBuilder());

        // Build the GUI
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
