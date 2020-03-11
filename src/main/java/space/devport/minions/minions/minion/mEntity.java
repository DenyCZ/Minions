package space.devport.minions.minions.minion;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;

public class mEntity {
    @Setter @Getter private ArmorStand armorStand;
    @Setter @Getter private Chest attachedChest;

    public mEntity(final ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public boolean hasAttachedChest() {
        if(attachedChest != null && attachedChest.getBlock().getType() == Material.CHEST || attachedChest.getBlock().getType() == Material.TRAPPED_CHEST) {
            return true;
        }
        return false;
    }
}
