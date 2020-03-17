package space.devport.minions.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import space.devport.minions.MinionsPlugin;
import space.devport.minions.minions.MinionBasic;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent playerQuitEvent) {

    }

    @EventHandler
    public void onPlace(PlayerInteractEvent playerInteractEvent) {
        if(playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
            final Player player = playerInteractEvent.getPlayer();
            if(player.getInventory().getItemInHand().equals(new ItemStack(Material.LEGACY_SKULL_ITEM))) {
                //TODO: Get data from head (MinionProperties)


                if(true) {
                    MinionBasic minion = new MinionBasic(); //byheadprops

                    final ArmorStand asMinion = (ArmorStand) player.getWorld().spawnEntity(
                            new Location(
                                    player.getWorld(),
                                    player.getLocation().getX() + 0.5,
                                    player.getLocation().getY() + 0.0,
                                    player.getLocation().getZ() + 0.5,
                                    0.0f,
                                    0.0f)
                            , EntityType.ARMOR_STAND);

                    asMinion.setSmall(minion.isSmall());
                    asMinion.setVisible(minion.isVisible());
                    asMinion.setGravity(minion.isGravity());
                    asMinion.setBasePlate(false);
                    asMinion.setArms(true);
                    asMinion.setItemInHand(minion.getItemInHand());
                    asMinion.setCustomNameVisible(minion.isHasVisibleName());
                    asMinion.setCustomName(minion.getVisibleName());

                    minion.getMinionEntity().setArmorStand(asMinion);
                    minion.onLoad();
                    minion.onSpawn();

                    MinionsPlugin.getInstance().getMinionManager().createMinion(player.getUniqueId(), minion);
                }
            }
        }
    }
}
