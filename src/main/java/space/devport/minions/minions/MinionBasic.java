package space.devport.minions.minions;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import space.devport.minions.MinionsPlugin;
import space.devport.minions.minions.minion.mEntity;

import java.util.UUID;

public class MinionBasic {

    // TODO Somehow construct or integrate with a template and level @Denyk

    @Getter @Setter private String author;
    @Getter @Setter private String id;
    @Getter @Setter private UUID uuid;
    @Getter private boolean isInit;

    @Getter @Setter private mEntity minionEntity;
    @Getter @Setter private boolean isSpawning;

    @Getter @Setter private boolean hasVisibleName = false;
    @Getter @Setter private String visibleName;
    @Getter @Setter private boolean isSmall = true;
    @Getter @Setter private boolean isVisible = true;
    @Getter @Setter private boolean isGravity = false;
    @Getter @Setter private int direction;
    @Getter @Setter private boolean invincible = true;

    @Getter private MinionProperties mProperties;

    @Getter @Setter private int itemsCollected;
    @Getter @Setter private int actionsPerformed;

    @Getter @Setter private ItemStack itemInHand;

    @Getter @Setter private Inventory upgradeGui;
    @Getter @Setter private Inventory inventory;

    public MinionBasic() {
        mProperties = new MinionProperties();

        this.isInit = true;
        uuid = UUID.randomUUID();
    }

    public void onLoad() {
        if(!this.isInit || author.isEmpty() || id.isEmpty()) {
            MinionsPlugin.getInstance().getConsoleOutput().debug("Failed to load minion!!");
            return;
        }
    }

    public void onSpawn() {
        if(!this.isInit) {
            return;
        }
    }

    public boolean canDoAction() {
        if(this.isSpawning ||
                (this.mProperties.isHealthBased() && this.mProperties.getHealth() <= 0) ||
                this.getMinionEntity().getArmorStand() == null || this.getMinionEntity().getArmorStand().isDead()) {
            MinionsPlugin.getInstance().getConsoleOutput().debug("Minion can't perform action");
            return false;
        }
        return true;
    }

    public void doAction() {
        if(!canDoAction()) {
            return;
        }

        this.actionsPerformed++;
        if(this.mProperties.isHealthBased()) {
            this.mProperties.setActionsSinceLastHealthDrop(this.mProperties.getActionsSinceLastHealthDrop()+1);
            if(this.mProperties.getActionsSinceLastHealthDrop() >= this.mProperties.getAllowedActionsPerHealthUnit()) {
                this.mProperties.setHealth(this.mProperties.getHealth()-1);
                this.mProperties.setActionsSinceLastHealthDrop(0);
            }
        }

    }

    public void onDespawn() {
    }

    public void onUnload() {

    }
}