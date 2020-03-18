package space.devport.minions.minions.templates;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import space.devport.minions.minions.MinionBasic;
import space.devport.minions.minions.events.MinionActionEvent;
import space.devport.minions.utils.ActionType;

public class MinerMinion extends MinionBasic {

    @Override
    public String getAuthor() {
        return "DenyCZ";
    }

    @Override
    public String getId() {
        return "Miner";
    }

    @Override
    public void onSpawn() {
        super.onSpawn();
        this.getMinionEntity().getArmorStand().setItemInHand(new ItemStack(Material.DIAMOND_PICKAXE, 1));
    }

    @Override
    public void doAction() {
        super.doAction();

        Location standingLocation = getMinionEntity().getArmorStand().getLocation();
        Block block = standingLocation.subtract(0, 1, 0).getBlock();
        if(block.getType() != Material.AIR) {
            Block blockClone = block;
            Bukkit.getPluginManager().callEvent(new MinionActionEvent(this, ActionType.BREAK));
            block.breakNaturally();
            block.getLocation().getBlock().setBlockData(blockClone.getBlockData());

        }
    }
}
